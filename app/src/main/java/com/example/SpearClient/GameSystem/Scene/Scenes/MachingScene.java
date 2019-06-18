package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.widget.Toast;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class MachingScene extends Scene {
    @Override
    public void start() {
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                spriteRenderer.bindingImage(GLRenderer.findImage("background_maching"));
                attachComponent(spriteRenderer);

                GUITransform guiTransform = new GUITransform();
                attachComponent(guiTransform);
                transform.scale.x = 0.5f;
                transform.scale.y = 0.5f;
            }
        });

        gamestart();
    }

    private void gamestart() {
        SocketIOBuilder.getInstance().gamestart(new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Game.instance.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Game.engine.changeScene(new InGameScene());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}
