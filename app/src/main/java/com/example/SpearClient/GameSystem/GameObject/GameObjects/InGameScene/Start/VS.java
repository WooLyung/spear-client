package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start;

import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerStateComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Other.AnimationManager;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

public class VS extends GameObject {
    SpriteRenderer spriteRenderer;
    GUITransform transform;
    int imageCode;

    @Override
    public void start() {
        imageCode = GLRenderer.findImage("vs");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(imageCode);
        spriteRenderer.setZ_index(60);

        transform = new GUITransform();
        attachComponent(transform);
        transform.scale.x = 0.5f;
        transform.scale.y = 0.5f;
    }

    public void updateImage(float time, boolean isAppear) {
        if (isAppear) { // 0 ~ 3
            if (time <= 0.5f) time = 0;
            else if (time >= 1.5f) time = 1;
            else time = time - 0.5f;

            float[] color = {
                    1, 1, 1, time,
                    1, 1, 1, time,
                    1, 1, 1, time,
                    1, 1, 1, time
            };
            GLRenderer.imageDatas.get(imageCode).setColors(color);
        }
        else {
            float[] color = {
                    1, 1, 1, 1 - time / 2,
                    1, 1, 1, 1 - time / 2,
                    1, 1, 1, 1 - time / 2,
                    1, 1, 1, 1 - time / 2
            };
            GLRenderer.imageDatas.get(imageCode).setColors(color);
        }
    }
}
