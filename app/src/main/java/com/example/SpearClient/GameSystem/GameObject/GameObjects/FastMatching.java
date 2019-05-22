package com.example.SpearClient.GameSystem.GameObject.GameObjects;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Types.Vector;

public class FastMatching extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("test_button"));

        transform = new GUITransform();
        attachComponent(transform);
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN
                || Input.getTouchState(i) == Input.TOUCH_STATE.STAY) {
                Log.i("fast matching", "distance : " + Vector.distanceDouble(Input.getTouchUIPos(i), transform.position));

                if (Vector.distanceDouble(Input.getTouchUIPos(i), transform.position) <= 140/100f * 140/100f) { // 버튼을 클릭했을 경우
                    Log.i("fast matching", "clicked!");
                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
