package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.media.MediaPlayer;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard.ResultBoard;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginBoard;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class LoginScene extends Scene {
    MediaPlayerHolder mph;

    @Override
    public void start() {
        objs.add(new Background());
        objs.add(new LoginBoard());
        objs.add(new Cloud());

        mph = SoundPlayer.playBackgroundSound(Game.instance, R.raw.main, true);
        createBackgrounds();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {
        super.finish();

        MediaPlayerHelper.getInstance().mphHolder = mph;
    }

    private void createBackgrounds() {
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_wall"));
                spriteRenderer.setZ_index(-8);

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.8f;
                transform.scale.y = 0.8f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_tunnel"));
                spriteRenderer.setZ_index(-7);

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.8f;
                transform.scale.y = 0.8f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_people"));
                spriteRenderer.setZ_index(-6);

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.8f;
                transform.scale.y = 0.8f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_front_wall"));
                spriteRenderer.setZ_index(-5);

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.8f;
                transform.scale.y = 0.8f;
                transform.position.y = -2f;
            }
        });
    }
}
