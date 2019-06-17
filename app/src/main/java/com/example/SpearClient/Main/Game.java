package com.example.SpearClient.Main;

import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.example.SpearClient.GameSystem.Other.AnimationManager;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.R;
import com.example.SpearClient.Types.Vector;

import java.security.Key;

import io.socket.client.Socket;

public class Game extends AppCompatActivity {

    Socket socket;
    GLRenderer renderer;
    Thread thread;
    String TAG = "MainActivity";
    GLView view;

    public ConstraintLayout mainView;
    public static long preTime;

    public static Game instance;
    public static Engine engine;
    public static AnimationManager animationManager;
    public static int screenWidth;
    public static int screenHeight;
    public static double screenDiagonal;

    private static float deltaTime;
    public static float slowTime = 0;

    public Game() {
        instance = this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // 시간 초기화
        preTime = System.currentTimeMillis();

        super.onCreate(savedInstanceState); // 조명 항상켜기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        saveScreenSize(); // 화면의 크기 값을 저장
        inputInit(); // 입력 변수 초기화

        // 엔진의 베이스를 이루는 객체를 생성
        renderer = new GLRenderer(this); // 엔진의 모든 렌더링을 담당
        view = new GLView(this, renderer); // 렌더가 된 이미지를 출력하는 뷰
        thread = new Thread(new MainLoop()); // 게임이 돌아가는 스레드
        engine = new Engine(); // 게임의 전반적인 부분을 총괄하는 엔진
        animationManager = new AnimationManager(); // 애니메이션에 대한 데이터들을 저장

        // 뷰 설정
        setContentView(R.layout.activity_main);
        mainView = findViewById(R.id.main);
        mainView.addView(view);

        thread.start(); // 게임 스레드 시작
    }

    private void inputInit() {
        for (int i = 0; i < 4; i++) {
            Input.multiTouchState[i] = Input.TOUCH_STATE.NONE;
            Input.multiIsDown[i] = false;
            Input.multiTouchPos[i] = new Vector(0, 0);
        }
    }

    private void saveScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenWidth = point.x;
        screenHeight = point.y;
        screenDiagonal = Math.sqrt(point.x * point.x + point.y * point.y);
        GLView.defaultHeight = 10 * screenHeight / screenDiagonal;
        GLView.defaultWidth = 10 * screenWidth / screenDiagonal;
        GLView.nowHeight = 10 * screenHeight / screenDiagonal;
        GLView.nowWidth = 10 * screenWidth / screenDiagonal;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == 4) {
            Input.isBackkeyDown = true;
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int state = event.getAction() & MotionEvent.ACTION_MASK;

        if (state == MotionEvent.ACTION_UP) {
            Input.isDown = false;
        }
        else if (state == MotionEvent.ACTION_DOWN) {
            Input.isDown = true;
            Input.touchPos.x = event.getX();
            Input.touchPos.y = event.getY();
        }
        else if (state == MotionEvent.ACTION_MOVE) {
            if (event.getPointerCount() > 1) {
                Input.multiTouchPos[event.getPointerCount() - 2].x = event.getX(event.getPointerCount() - 1);
                Input.multiTouchPos[event.getPointerCount() - 2].y = event.getY(event.getPointerCount() - 1);
            }
            else {
                Input.touchPos.x = event.getX();
                Input.touchPos.y = event.getY();
            }
        }
        else if (state == MotionEvent.ACTION_POINTER_DOWN) {
            if (event.getPointerCount() < 6) {
                Input.multiIsDown[event.getPointerCount() - 2] = true;
                Input.multiTouchPos[event.getPointerCount() - 2].x = event.getX(event.getPointerCount() - 1);
                Input.multiTouchPos[event.getPointerCount() - 2].y = event.getY(event.getPointerCount() - 1);

                Input.touchCount = event.getPointerCount();
            }
        }
        else if (state == MotionEvent.ACTION_POINTER_UP) {
            if (event.getPointerCount() < 6) {
                Input.multiIsDown[event.getPointerCount() - 2] = false;
                Input.multiTouchPos[event.getPointerCount() - 2].x = event.getX(event.getPointerCount() - 1);
                Input.multiTouchPos[event.getPointerCount() - 2].y = event.getY(event.getPointerCount() - 1);

                Input.touchCount = event.getPointerCount();
            }
        }

        return super.onTouchEvent(event);
    }

    public static void setDeltaTime(float deltaTime_) {
        deltaTime = deltaTime_;
    }

    public static float getDeltaTime() {
        return deltaTime * ((slowTime != 0) ? 0.6f : 1);
    }

    public static float getNoneDeltaTime() {
        return deltaTime;
    }
}