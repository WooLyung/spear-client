package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.widget.Toast;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class MachingScene extends Scene {
    @Override
    public void start() {
        mph = SoundPlayer.playBackgroundSound(Game.instance, R.raw.maching, true);

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
        objs.add(new GameObject() {
            @Override
            public void start() {
                final TextRenderer textRenderer = new TextRenderer();
                attachComponent(textRenderer);
                textRenderer.setHorizontal(2);
                textRenderer.setText("Track : Come, Ye Children of Borealis!\n" +
                        "Music by 브금대통령 \n" +
                        "Music provided by 브금대통령\n" +
                        "Watch : https://youtu.be/LFyKRMSKiOQ");
                Game.instance.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textRenderer.getTextView().setTextColor(Game.instance.getResources().getColor(R.color.white));
                        textRenderer.getTextView().setTextSize(7);
                    }
                });

                GUITransform transform = new GUITransform();
                attachComponent(transform);
                transform.position.x = (float)GLView.defaultWidth - 0.5f;
                transform.position.y = -(float)GLView.defaultHeight + 0.5f;
            }
        });

        gamestart();
    }

    private MediaPlayerHolder mph;

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

    @Override
    public void finish() {
        super.finish();

        MediaPlayerHelper.getInstance().delMedia(mph);
    }
}
