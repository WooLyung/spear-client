package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard;

import android.widget.Toast;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Scene.Scenes.InGameScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MachingScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class RegameButton extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_regame"));

        transform = new GUITransform();
        attachComponent(transform);
        spriteRenderer.setZ_index(61);
        transform.position.x = -2.6f;
        transform.position.y = -2.6f;
        transform.scale.x = 0.45f;
        transform.scale.y = 0.45f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Math.abs(Input.getTouchUIPos(i).x - transform.position.x) <= 150 / 100f
                    && Math.abs(Input.getTouchUIPos(i).y - transform.position.y) <= 60 / 100f) { // 버튼을 클릭했을 경우

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
