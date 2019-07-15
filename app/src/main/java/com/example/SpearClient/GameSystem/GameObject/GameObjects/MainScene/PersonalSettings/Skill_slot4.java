package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings;

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

public class Skill_slot4 extends GameObject {
    public SpriteRenderer selected;

    @Override
    public void start() {
        setName("skill_slot1");

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("skill_rush"));
        spriteRenderer.setZ_index(-4);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f - 2.7f + 1 + 0.4f;
        transform.position.x = -(float) GLView.nowWidth + 6f + 2.2f + 1.1f;
        transform.scale.x = 1000/1470f * 0.9f;
        transform.scale.y = 1000/1470f * 0.9f;

        appendChild(new GameObject() {
            @Override
            public void start() {
                selected = new SpriteRenderer();
                attachComponent(selected);
                selected.bindingImage(GLRenderer.findImage("selected_skill"));
                selected.setZ_index(-4);
                selected.setIsVisible(false);

                transform = new Transform();
                attachComponent(transform);
                transform.scale.x = 1.07f;
                transform.scale.y = 1.07f;
            }
        });
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Vector.distanceDouble(Input.getTouchWorldPos(i), transform.position) <= 170/147f * 170/147f) { // 버튼을 클릭했을 경우
                    ActionManager.selectedSkill = 3;

                    ((MainScene) Game.engine.nowScene).skill_slot1.selected.setIsVisible(false);
                    ((MainScene)Game.engine.nowScene).skill_slot2.selected.setIsVisible(false);
                    ((MainScene)Game.engine.nowScene).skill_slot3.selected.setIsVisible(false);
                    ((MainScene)Game.engine.nowScene).skill_slot4.selected.setIsVisible(true);
                    ((MainScene)Game.engine.nowScene).skill_slot5.selected.setIsVisible(false);
                    ((MainScene)Game.engine.nowScene).skill_slot6.selected.setIsVisible(false);
                    ((MainScene)Game.engine.nowScene).skillCard.spriteRenderer.bindingImage(GLRenderer.findImage("skillcard_rush"));
                }
            }
        }
    }
}