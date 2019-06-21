package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;

public class EnemyPointer extends GameObject {
    SpriteRenderer spriteRenderer;
    Transform transform;
    Enemy enemy;

    @Override
    public void start() {
        setName("enemyPointer");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("pointer_enemy"));
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

        if (enemy == null) {
            if (Game.engine.nowScene.findObjectByName("enemy") != null) {
                enemy = (Enemy)Game.engine.nowScene.findObjectByName("enemy");
            }
        }
        else {
            if (Math.abs(Game.engine.nowScene.camera.getPosition().x - enemy.getTransform().position.x - 2) <= GLView.nowWidth) {
                transform.position.x = enemy.getTransform().position.x;
                transform.position.y = 3;
                transform.angle = 0;
            }
            else {
                transform.position.y = 1;

                if (Game.engine.nowScene.camera.getPosition().x - enemy.getTransform().position.x - 2 >= GLView.nowWidth) {
                    transform.position.x = Game.engine.nowScene.camera.getPosition().x - (float)GLView.nowWidth + 2;
                    transform.angle = 270;
                }
                else {
                    transform.position.x = Game.engine.nowScene.camera.getPosition().x + (float)GLView.nowWidth - 2;
                    transform.angle = 90;
                }
            }
        }
    }
}
