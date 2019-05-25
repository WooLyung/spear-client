package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.RegisterBoard;

import android.util.Log;
import android.widget.Toast;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginBoard;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;
import com.example.SpearClient.Types.Vector;

import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class RegisterButton extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_apply"));

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
                    String name = ((EditTextRenderer) Game.engine.nowScene.findObjectByName("input_name").getComponent("editTextRenderer")).getEditText().getText().toString();

                    register(id, password, name);
                }
            }
        }
    }

    private void register(String id, String password, String name) {
        try {
            Socket socket = SocketIOBuilder.getSocket();
            socket.on("registerCallback", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    Game.instance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(args[0].toString());
                                String message = jsonObject.getString("message");
                                if (message.equals("register failed")) {
                                    Toast.makeText(Game.instance, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else if (message.equals("register complete")) {
                                    Toast.makeText(Game.instance, "계정이 성공적으로 생성되었습니다.", Toast.LENGTH_SHORT).show();
                                    getParent().destroy();
                                    Game.engine.nowScene.objs.add(new LoginBoard());
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            });
            socket.emit("register", new JSONObject("{username: \""+id+"\", password: \""+password+"\", nickname: \""+name+"\"}"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
