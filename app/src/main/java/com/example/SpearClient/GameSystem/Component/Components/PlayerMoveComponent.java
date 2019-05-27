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

    @Override
    public void start() {
        setName("playerMoveComponent");
    }

    @Override
    public void update() {
        if (animationComponent == null) {
            animationComponent = (AnimationComponent) object.getComponent("animationComponent");
            spriteRenderer = (SpriteRenderer) object.getComponent("spriteRenderer");
        }

        if (state == STATE.IDLE) {
            if (animationComponent.getNowAnim() != 2) {
                animationComponent.play(2);
            }
        }
        else if (state == STATE.WALK) {
            if (animationComponent.getNowAnim() != 0) {
                animationComponent.play(0);
            }

            object.getTransform().position.x += Game.deltaTime * 3f * ((dir == DIR.RIGHT) ? 1 : -1);
        }
        else if (state == STATE.RUN) {
            if (animationComponent.getNowAnim() != 1) {
                animationComponent.play(1);
            }

            object.getTransform().position.x += Game.deltaTime * 6f * ((dir == DIR.RIGHT) ? 1 : -1);
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
        if (state == STATE.IDLE) {
            this.state = STATE.IDLE;
        }
        else if (state == STATE.WALK) {
            this.state = STATE.WALK;
        }
        else if (state == STATE.RUN) {
            this.state = STATE.RUN;
        }
    }
}
