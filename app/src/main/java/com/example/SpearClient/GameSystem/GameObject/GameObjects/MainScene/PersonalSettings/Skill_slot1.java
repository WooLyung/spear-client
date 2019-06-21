package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Other.ActionManager;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class Skill_slot1 extends GameObject {
    @Override
    public void start() {
        setName("skill_slot1");

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("skill_shallow_stab"));
        spriteRenderer.setZ_index(-4);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f - 2.7f + 1;
        transform.position.x = (float) GLView.nowWidth - 5.5f - 2.3f - 1.15f;
        transform.scale.x = 1000/1470f * 0.9f;
        transform.scale.y = 1000/1470f * 0.9f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Vector.distanceDouble(Input.getTouchWorldPos(i), transform.position) <= 170/147f * 170/147f) { // 버튼을 클릭했을 경우
                    ActionManager.selectedSkill = 0;
                }
            }
        }
    }
}