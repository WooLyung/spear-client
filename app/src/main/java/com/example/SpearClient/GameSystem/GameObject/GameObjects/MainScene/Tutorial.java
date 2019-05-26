package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene;

import android.util.Log;
import android.widget.Toast;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;
import com.example.SpearClient.Types.Vector;

import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Tutorial extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Transform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_tutorial"));

        transform = new Transform();
        attachComponent(transform);
        transform.position.x = -5.2f;
        transform.position.y = 0.5f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Vector.distanceDouble(Input.getTouchWorldPos(i), transform.position) <= 350/147f * 350/147f) { // 버튼을 클릭했을 경우

                    Log.i("main", "button_tutorial");
                }
            }
        }
    }

    private void enter() {
        try {
            Socket socket = SocketIOBuilder.getSocket();
            socket.on("enterCallback", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    Game.instance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(args[0].toString());
                                String message = jsonObject.getString("message");
                                if (message.equals("enter failed")) {
                                    Toast.makeText(Game.instance, "빠른 매칭에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else if (message.equals("enter complete")) {
                                    Toast.makeText(Game.instance, "빠른 매칭 성공, 룸아이디 : " + jsonObject.getInt("roomid"), Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            });
            socket.emit("enter");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
