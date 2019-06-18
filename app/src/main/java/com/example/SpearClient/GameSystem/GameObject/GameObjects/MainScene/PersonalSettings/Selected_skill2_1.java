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
import com.example.SpearClient.SocketIO.SocketIOBuilder;
import com.example.SpearClient.Types.Vector;

import org.json.JSONObject;

public class Selected_skill2_1 extends GameObject {
    SpriteRenderer spriteRenderer;

    @Override
    public void start() {
        setName("skill_slot1");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("skill_none"));
        spriteRenderer.setZ_index(-4);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f + 0.3f;
        transform.position.x = (float) GLView.nowWidth - 5.5f - 2.2f - 1.1f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Vector.distanceDouble(Input.getTouchWorldPos(i), transform.position) <= 140/147f * 140/147f) { // 버튼을 클릭했을 경우
                    if (ActionManager.selectedSkill != -1)
                        ActionManager.skill2[0] = ActionManager.selectedSkill;
                    ActionManager.selectedSkill = -1;

                    try {
                        SocketIOBuilder.getInstance().setSkill(new JSONObject("{" + "\"username\":" + SocketIOBuilder.id + "," +
                                "\"skill1Array\":[\n" +
                                "\t" + ActionManager.skill1[0] + ", " + ActionManager.skill1[1] + ", " + ActionManager.skill1[2] + ", " + ActionManager.skill1[3] + "\n" +
                                "], \"skill2Array\":[\n" +
                                "\t" + ActionManager.skill2[0] + ", " + ActionManager.skill2[1] + ", " + ActionManager.skill2[2] + ", " + ActionManager.skill2[3] + "\n" +
                                "]}"));

                        ((MainScene) Game.engine.nowScene).updateImages();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void updateImage() {
        switch (ActionManager.skill2[0]) {
            case 0:
                spriteRenderer.bindingImage(GLRenderer.findImage("skill_shallow_stab"));
                break;
            case 1:
                spriteRenderer.bindingImage(GLRenderer.findImage("skill_deep_stab"));
                break;
            case 2:
                spriteRenderer.bindingImage(GLRenderer.findImage("skill_rush_stab"));
                break;
            case 3:
                spriteRenderer.bindingImage(GLRenderer.findImage("skill_rush"));
                break;
            case 4:
                spriteRenderer.bindingImage(GLRenderer.findImage("skill_avoid"));
                break;
            case 5:
                spriteRenderer.bindingImage(GLRenderer.findImage("skill_skim"));
                break;
        }
    }
}