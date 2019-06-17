package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerStateComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Other.ActionManager;
import com.example.SpearClient.GameSystem.Other.AnimationManager;
import com.example.SpearClient.GameSystem.Other.Camera;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GameSystem.Scene.Scenes.InGameScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class Skill1 extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Player player;
    private PlayerStateComponent playerStateComponent;

    private int[] front_images;
    private int[] back_images;
    private GameObject back;
    private SpriteRenderer back_sr;

    @Override
    public void start() {
        front_images = new int[]{ GLRenderer.findImage("skill_shallow_stab"),
                GLRenderer.findImage("skill_deep_stab"),
                GLRenderer.findImage("skill_rush_stab"),
                GLRenderer.findImage("skill_rush"),
                GLRenderer.findImage("skill_avoid"),
                GLRenderer.findImage("skill_skim")};
        back_images = new int[]{ GLRenderer.findImage("skill_shallow_stab_black"),
                GLRenderer.findImage("skill_deep_stab_black"),
                GLRenderer.findImage("skill_rush_stab_black"),
                GLRenderer.findImage("skill_rush_black"),
                GLRenderer.findImage("skill_avoid_black"),
                GLRenderer.findImage("skill_skim_black")};

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(front_images[ActionManager.skill1[0]]);
        spriteRenderer.setZ_index(50);
        spriteRenderer.setDir(RendererComponent.DIRECTION.LEFT);

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.x = (float)GLView.defaultWidth - 4f;
        transform.position.y = -(float)GLView.defaultHeight + 1.5f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        back = new GameObject() {
            @Override
            public void start() {
                back_sr = new SpriteRenderer();
                attachComponent(back_sr);
                back_sr.bindingImage(back_images[ActionManager.skill1[0]]);
                back_sr.setZ_index(49);

                GUITransform transform = new GUITransform();
                attachComponent(transform);
                transform.position.x = (float)GLView.defaultWidth - 4f;
                transform.position.y = -(float)GLView.defaultHeight + 1.5f;
                transform.scale.x = 1000/1470f;
                transform.scale.y = 1000/1470f;
            }
        };
        appendChild(back);

        ActionManager.setCool1(0);
    }

    @Override
    public void update() {
        super.update();

        if (ActionManager.getNowAction1() != 0)
            ActionManager.setCool1(ActionManager.getCool1() + Game.getDeltaTime());
        spriteRenderer.setFill((3.5f - ActionManager.getCool1()) / 3.5f);
        if (ActionManager.getCool1() > 3.5f) {
            ActionManager.setCool1(0);
            ActionManager.setNowAction1(0);
            spriteRenderer.bindingImage(front_images[ActionManager.skill1[ActionManager.getNowAction1()]]);
            back_sr.bindingImage(back_images[ActionManager.skill1[ActionManager.getNowAction1()]]);
        }

        if (player == null && Game.engine.nowScene.findObjectByName("player") != null)
            player = (Player)Game.engine.nowScene.findObjectByName("player");

        if (playerStateComponent == null && player != null && player.getComponent("playerStateComponent") != null)
            playerStateComponent = (PlayerStateComponent)player.getComponent("playerStateComponent");

        for (int i = 0; i < 5; i++) {
            if (Vector.distanceDouble(Input.getTouchUIPos(i), transform.position) <= 150/147f * 150/147f
                    && GameManager.getInstance().state == GameManager.STATE.GAMING) { // 버튼을 클릭했을 경우
                if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                    boolean isComplete = false;

                    if (ActionManager.skill1[ActionManager.getNowAction1()] == 0) {
                        isComplete = playerStateComponent.changeState(PlayerStateComponent.ACTION.SHALLOW_STAB);
                    }
                    else if (ActionManager.skill1[ActionManager.getNowAction1()] == 1) {
                        isComplete = playerStateComponent.changeState(PlayerStateComponent.ACTION.DEEP_STAB);
                    }
                    else if (ActionManager.skill1[ActionManager.getNowAction1()] == 2) {
                        isComplete = playerStateComponent.changeState(PlayerStateComponent.ACTION.RUSH_STAB);
                    }
                    else if (ActionManager.skill1[ActionManager.getNowAction1()] == 3) {
                        isComplete = playerStateComponent.changeState(PlayerStateComponent.ACTION.RUSH);
                    }
                    else if (ActionManager.skill1[ActionManager.getNowAction1()] == 4) {
                        isComplete = playerStateComponent.changeState(PlayerStateComponent.ACTION.AVOID);
                    }
                    else if (ActionManager.skill1[ActionManager.getNowAction1()] == 5) {
                        isComplete = playerStateComponent.changeState(PlayerStateComponent.ACTION.SKIM);
                    }

                    if (isComplete) {
                        ActionManager.setCool1(0);
                        ActionManager.setNowAction1((ActionManager.getNowAction1() + 1) % 4);
                        spriteRenderer.bindingImage(front_images[ActionManager.skill1[ActionManager.getNowAction1()]]);
                        back_sr.bindingImage(back_images[ActionManager.skill1[ActionManager.getNowAction1()]]);

                        ((InGameScene)Game.engine.nowScene).bloodTime = 1;
                        Game.engine.nowScene.camera.vibrateMiddle();
                    }
                }
            }
        }
    }
}
