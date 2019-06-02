package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class Skill1 extends GameObject {
    private SpriteRenderer spriteRenderer;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_skill1"));
        spriteRenderer.setZ_index(50);

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.x = (float)GLView.defaultWidth - 4f;
        transform.position.y = -(float)GLView.defaultHeight + 1.5f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Vector.distanceDouble(Input.getTouchUIPos(i), transform.position) <= 150/147f * 150/147f) { // 버튼을 클릭했을 경우
                Log.i("skill1", "clicked");
            }
        }
    }
}
