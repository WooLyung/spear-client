package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class MoveRight extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Player player;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_move_right"));
        spriteRenderer.setZ_index(10);

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.x = -(float)GLView.defaultWidth + 4f;
        transform.position.y = -(float)GLView.defaultHeight + 1.5f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
    }

    @Override
    public void update() {
        super.update();

        if (player == null)
            player = (Player)Game.engine.nowScene.findObjectByName("player");

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN
                || Input.getTouchState(i) == Input.TOUCH_STATE.STAY) {
                if (Vector.distanceDouble(Input.getTouchUIPos(i), transform.position) <= 150/147f * 150/147f) { // 버튼을 클릭했을 경우
                    player.getTransform().position.x += Game.deltaTime * 4;
                    ((SpriteRenderer)player.getRenderer()).setIsFlip(false);
                }
            }
        }
    }
}
