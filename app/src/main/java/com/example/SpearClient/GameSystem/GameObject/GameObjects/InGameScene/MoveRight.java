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

public class MoveRight extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Player player;
    private PlayerMoveComponent playerMoveComponent;
    private float timeUp = 0;
    private float time = 0;
    private int lastClickIndex = -1;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_move_right"));
        spriteRenderer.setZ_index(50);

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

        time += Game.deltaTime;

        if (player == null) {
            player = (Player)Game.engine.nowScene.findObjectByName("player");
            playerMoveComponent = (PlayerMoveComponent)player.getComponent("playerMoveComponent");
        }

        for (int i = 0; i < 5; i++) {
            if (Vector.distanceDouble(Input.getTouchUIPos(i), transform.position) <= 150/147f * 150/147f) { // 버튼을 클릭했을 경우
                lastClickIndex = i;

                if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                    playerMoveComponent.setDir(PlayerMoveComponent.DIR.RIGHT);

                    if (time - timeUp <= 0.3f)
                        playerMoveComponent.setState(PlayerMoveComponent.STATE.RUN);
                    else
                        playerMoveComponent.setState(PlayerMoveComponent.STATE.WALK);
                }
                else if (Input.getTouchState(i) == Input.TOUCH_STATE.STAY) {
                    playerMoveComponent.setDir(PlayerMoveComponent.DIR.RIGHT);

                    if (playerMoveComponent.state == PlayerMoveComponent.STATE.RUN)
                        playerMoveComponent.setState(PlayerMoveComponent.STATE.RUN);
                    else
                        playerMoveComponent.setState(PlayerMoveComponent.STATE.WALK);
                }
                else if (Input.getTouchState(i) == Input.TOUCH_STATE.UP) {
                    playerMoveComponent.setState(PlayerMoveComponent.STATE.IDLE);
                    timeUp = time;
                }
            }
            else if (i == lastClickIndex) {
                timeUp = 0;
                playerMoveComponent.setState(PlayerMoveComponent.STATE.IDLE);
                lastClickIndex = -1;
            }
        }
    }
}
