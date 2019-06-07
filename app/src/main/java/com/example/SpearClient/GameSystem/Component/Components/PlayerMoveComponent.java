package com.example.SpearClient.GameSystem.Component.Components;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
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
    private SpriteRenderer spriteRenderer;
    private PlayerStateComponent playerStateComponent;
    private float time = 0;

    @Override
    public void start() {
        setName("playerMoveComponent");
    }

    @Override
    public void update() {
        time += Game.deltaTime;

        if (animationComponent == null) {
            animationComponent = (AnimationComponent) object.getComponent("animationComponent");
            spriteRenderer = (SpriteRenderer) object.getComponent("spriteRenderer");
            playerStateComponent = (PlayerStateComponent) object.getComponent("playerStateComponent");
        }

        if (state == STATE.IDLE) {
            if (animationComponent.getNowAnim() != 2) {
                animationComponent.play(2);
            }

            if (playerStateComponent.action == PlayerStateComponent.ACTION.RUN
                || playerStateComponent.action == PlayerStateComponent.ACTION.WALK)
                playerStateComponent.changeState(PlayerStateComponent.ACTION.DEFAULT);
        }
        else if (state == STATE.WALK) {
            if (animationComponent.getNowAnim() != 0) {
                animationComponent.play(0);
            }

            if (time >= 0.7f) {
                setState(STATE.RUN);
            }

            playerStateComponent.changeState(PlayerStateComponent.ACTION.WALK);
            object.getTransform().position.x += Game.deltaTime * 3f * ((dir == DIR.RIGHT) ? 1 : -1);
        }
        else if (state == STATE.RUN) {
            if (animationComponent.getNowAnim() != 1) {
                animationComponent.play(1);
            }

            playerStateComponent.changeState(PlayerStateComponent.ACTION.RUN);

            float speed = 0.5f + ((time > 1.7f) ? 1.7f : time);
            object.getTransform().position.x += Game.deltaTime * 6f * ((dir == DIR.RIGHT) ? 1 : -1) * speed;
        }
    }

    @Override
    public void finish() {

    }

    public void setDir(DIR dir) {
        if (this.dir != dir) {
            if (dir == DIR.LEFT) {
                spriteRenderer.setIsFlip(true);
                this.dir = dir;
            }
            else {
                spriteRenderer.setIsFlip(false);
                this.dir = dir;
            }
        }
    }

    public void setState(STATE state) {
        if (state == STATE.IDLE
            && this.state != STATE.IDLE) {
            this.state = STATE.IDLE;
            time = 0;
        }
        else if (state == STATE.WALK
            && this.state != STATE.WALK) {
            this.state = STATE.WALK;
            time = 0;
        }
        else if (state == STATE.RUN
            && this.state != STATE.RUN) {
            this.state = STATE.RUN;
            time = 0;
        }
    }
}
