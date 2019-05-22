package com.example.SpearClient.GameSystem.GameObject.GameObjects;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class Stick extends GameObject {
    @Override
    public void start() {
        setName("part1");

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("stick"));

        Transform transform = new Transform();
        attachComponent(transform);
        transform.anchor.x = 0;
        transform.anchor.y = 0;

        AnimationComponent animationComponent = new AnimationComponent();
        attachComponent(animationComponent);
        animationComponent.addAnimation(Game.instance.getString(R.string.testAnim));

        setIsActive(false);
    }

    @Override
    public void update() {
        super.update();

        if (Input.getTouchState() == Input.TOUCH_STATE.DOWN) {
            ((AnimationComponent)getComponent("animationComponent")).play(0);
        }
        else if (Input.getTouchState(1) == Input.TOUCH_STATE.DOWN) {
            ((AnimationComponent)getComponent("animationComponent")).stop();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
