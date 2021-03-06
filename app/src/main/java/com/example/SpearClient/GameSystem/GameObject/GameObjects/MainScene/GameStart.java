package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene;

import android.util.Log;
import android.widget.Toast;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GameSystem.Scene.Scenes.InGameScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MachingScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;
import com.example.SpearClient.Types.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class GameStart extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Transform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_gamestart"));
        spriteRenderer.setZ_index(-9);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f - (float) GLView.nowHeight + 1f;
        transform.position.x = (float) GLView.nowWidth - 1f;
        transform.anchor.x = 0;
        transform.scale.x = 0.55f;
        transform.scale.y = 0.55f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Math.abs(Input.getTouchWorldPos(i).x - transform.position.x) <= 300 / 100f
                        && Math.abs(Input.getTouchWorldPos(i).y - transform.position.y) <= 60 / 100f) { // 버튼을 클릭했을 경우
                    //Game.engine.changeScene(new InGameScene());
                    enter();
                }
            }
        }
    }

    private void enter() {
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject("{\"isRank\":" + MainScene.selectedGame.equals("rank") + "}");

            SocketIOBuilder.getInstance().enter(jsonObject, new Emitter.Listener() {
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
                                    if (jsonObject.getBoolean("startGame")) {

                                        if (MainScene.selectedGame.equals("rank")) {
                                            GameManager.ratings[0] = jsonObject.getJSONObject("rate").getInt("one");
                                            GameManager.ratings[1] = jsonObject.getJSONObject("rate").getInt("two");
                                        }

                                        Game.engine.changeScene(new InGameScene());
                                    }
                                    else {
                                        Game.engine.changeScene(new MachingScene());
                                    }
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
