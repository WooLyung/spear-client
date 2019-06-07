package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard;

import android.widget.Toast;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class LoginButton extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_login"));

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.x = 0;
        transform.position.y = -2.6f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Math.abs(Input.getTouchUIPos(i).x - transform.position.x) <= 300 / 100f
                    && Math.abs(Input.getTouchUIPos(i).y - transform.position.y) <= 60 / 100f) { // 버튼을 클릭했을 경우
                    String id = ((EditTextRenderer) Game.engine.nowScene.findObjectByName("input_id").getComponent("editTextRenderer")).getEditText().getText().toString();
                    String password = ((EditTextRenderer) Game.engine.nowScene.findObjectByName("input_password").getComponent("editTextRenderer")).getEditText().getText().toString();

                    Engine.id = id;
                    login(id, password);
                }
            }
        }
    }

    private void login(final String id, String password) {
        try {
            SocketIOBuilder.getInstance().login(id, password, new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    Game.instance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(args[0].toString());
                                String message = jsonObject.getString("message");
                                if (message.equals("login failed")) {
                                    Toast.makeText(Game.instance, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else if (message.equals("login complete")) {
                                    SocketIOBuilder.id = id;
                                    Game.engine.changeScene(new MainScene());
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
