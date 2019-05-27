package com.example.SpearClient.GameSystem.Component.Components;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.Main.Game;

public class PlayerMoveComponent extends Component {
    public enum DIR {
        LEFT, RIGHT
    }

    public enum STATE {
        IDLE, WALK, RUN
    }

    public DIR dir = DIR.RIGHT;
    public STATE state = STATE.IDLE;
    private AnimationComponent animationComponent;

    @Override
    public void start() {
        setName("playerMoveComponent");

        animationComponent = (AnimationComponent) object.getComponent("animationComponent");
    }

    @Override
    public void update() {
        if (state == STATE.IDLE) {
            if (animationComponent.getNowAnim() != 2) {
                animationComponent.play(2);
            }
        }
        else if (state == STATE.WALK) {
            if (animationComponent.getNowAnim() != 0) {
                animationComponent.play(0);
                object.getTransform().position.x -= Game.deltaTime * 3f;
            }
        }
        else if (state == STATE.RUN) {
            if (animationComponent.getNowAnim() != 1) {
                animationComponent.play(1);
                object.getTransform().position.x -= Game.deltaTime * 6f;
            }
        }
    }

    @Override
    public void finish() {

    }
}
