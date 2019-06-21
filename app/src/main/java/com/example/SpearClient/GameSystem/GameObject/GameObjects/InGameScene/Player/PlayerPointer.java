package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.Enemy;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;

public class PlayerPointer extends GameObject {
    SpriteRenderer spriteRenderer;
    Transform transform;

    Player player;

    @Override
    public void start() {
        setName("playerPointer");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("pointer_player"));
        spriteRenderer.setZ_index(50);

        transform = new Transform();
        attachComponent(transform);
        transform.scale.x = 0.5f;
        transform.scale.y = 0.5f;
        transform.position.y = 3;
    }

    @Override
    public void update() {
        super.update();

        if (player == null) {
            if (Game.engine.nowScene.findObjectByName("enemy") != null) {
                player = (Player)Game.engine.nowScene.findObjectByName("player");
            }
        }
        else {
            transform.position.x = player.getTransform().position.x;
        }
    }
}
