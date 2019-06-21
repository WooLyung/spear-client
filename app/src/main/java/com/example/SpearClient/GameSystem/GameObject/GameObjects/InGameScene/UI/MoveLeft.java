package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class MoveLeft extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Player player;
    private PlayerMoveComponent playerMoveComponent;
    private int lastClickIndex = -1;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_move_left"));
        spriteRenderer.setZ_index(50);

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.x = -(float)GLView.defaultWidth + 1.5f;
        transform.position.y = -(float)GLView.defaultHeight + 1.5f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
    }

    @Override
    public void update() {
        super.update();

        if (player == null) {
            player = (Player)Game.engine.nowScene.findObjectByName("player");
            playerMoveComponent = (PlayerMoveComponent)player.getComponent("playerMoveComponent");
        }

        for (int i = 0; i < 5; i++) {
            if (Vector.distanceDouble(Input.getTouchUIPos(i), transform.position) <= 150/147f * 150/147f
                    && GameManager.getInstance().state == GameManager.STATE.GAMING) { // 버튼을 클릭했을 경우
                lastClickIndex = i;

                if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN
                    || Input.getTouchState(i) == Input.TOUCH_STATE.STAY) {
                    playerMoveComponent.setDir(PlayerMoveComponent.DIR.LEFT);

                    if (playerMoveComponent.state == PlayerMoveComponent.STATE.WALK
                        || playerMoveComponent.state == PlayerMoveComponent.STATE.IDLE)
                        playerMoveComponent.setState(PlayerMoveComponent.STATE.WALK);
                    else
                        playerMoveComponent.setState(PlayerMoveComponent.STATE.RUN);
                }
                else if (Input.getTouchState(i) == Input.TOUCH_STATE.UP) {
                    playerMoveComponent.setState(PlayerMoveComponent.STATE.IDLE);
                }
            }
            else if (i == lastClickIndex) {
                playerMoveComponent.setState(PlayerMoveComponent.STATE.IDLE);
                lastClickIndex = -1;
            }
        }
    }
}
