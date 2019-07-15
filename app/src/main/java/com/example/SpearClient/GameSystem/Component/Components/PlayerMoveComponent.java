package com.example.SpearClient.GameSystem.Component.Components;

import android.util.Log;

import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class PlayerMoveComponent extends Component {
    public enum DIR {
        LEFT, RIGHT
    }

    public enum STATE {
        IDLE, WALK, RUN, RUSH, RUSH_STAB
    }

    public DIR dir = DIR.RIGHT;
    public STATE state = STATE.IDLE;
    private AnimationComponent animationComponent;
    private SpriteRenderer spriteRenderer;
    private PlayerStateComponent playerStateComponent;
    private float time = 0;
    private float particleTime = 0;
    private Player player;

    @Override
    public void start() {
        setName("playerMoveComponent");
    }

    @Override
    public void update() {
        time += Game.getDeltaTime();

        if (animationComponent == null) {
            animationComponent = (AnimationComponent) object.getComponent("animationComponent");
            spriteRenderer = (SpriteRenderer) object.getComponent("spriteRenderer");
            playerStateComponent = (PlayerStateComponent) object.getComponent("playerStateComponent");
        }

        if (player == null) {
            player = (Player)object;
        }

        if (state == STATE.IDLE) {
            if (animationComponent.getNowAnim() != 2) {
                animationComponent.play(2);
            }

            if (playerStateComponent.action == PlayerStateComponent.ACTION.RUN
                || playerStateComponent.action == PlayerStateComponent.ACTION.WALK)
                playerStateComponent.changeState(PlayerStateComponent.ACTION.DEFAULT);

            if (player.playerEffect_rush.effectComponent.getColors()[3] != 0) {
                float alpha = player.playerEffect_rush.effectComponent.getColors()[3] - Game.getDeltaTime() * 2;
                if (alpha < 0) alpha = 0;

                if (!Player.pref.getBoolean("setting2", true)) {
                    alpha = 0;
                }

                player.playerEffect_rush.effectComponent.setColors(new float[] {
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha
                });
            }

            if (player.sp_run != null) {
                player.sp_run.release();
                player.sp_run = null;
            }
        }
        else if (state == STATE.WALK) {
            if (animationComponent.getNowAnim() != 0) {
                animationComponent.play(0);
            }

            if (time >= 0.7f) {
                setState(STATE.RUN);
            }

            playerStateComponent.changeState(PlayerStateComponent.ACTION.WALK);
            object.getTransform().position.x += Game.getDeltaTime() * 3f * ((dir == DIR.RIGHT) ? 1 : -1);

            if (player.playerEffect_rush.effectComponent.getColors()[3] != 0) {
                float alpha = player.playerEffect_rush.effectComponent.getColors()[3] - Game.getDeltaTime() * 4;
                if (alpha < 0) alpha = 0;

                if (!Player.pref.getBoolean("setting2", true)) {
                    alpha = 0;
                }

                player.playerEffect_rush.effectComponent.setColors(new float[] {
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha
                });
            }

            if (player.playerEffect_rushStab.effectComponent.getColors()[3] != 0) {
                float alpha = player.playerEffect_rushStab.effectComponent.getColors()[3] - Game.getDeltaTime() * 4;
                if (alpha < 0) alpha = 0;

                if (!Player.pref.getBoolean("setting2", true)) {
                    alpha = 0;
                }

                player.playerEffect_rushStab.effectComponent.setColors(new float[] {
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha
                });
            }

            if (player.sp_run != null) {
                player.sp_run.release();
                player.sp_run = null;
            }
        }
        else if (state == STATE.RUN) {
            particleTime += Game.getDeltaTime();
            if (particleTime > 0.2f) {
                particleTime = 0;
                ((Player)object).summonDirtParticle();
            }

            if (animationComponent.getNowAnim() != 1) {
                animationComponent.play(1);
            }

            playerStateComponent.changeState(PlayerStateComponent.ACTION.RUN);

            float speed = 0.5f + ((time > 1.7f) ? 1.7f : time);
            object.getTransform().position.x += Game.getDeltaTime() * 6f * ((dir == DIR.RIGHT) ? 1 : -1) * speed;

            if (player.playerEffect_rush.effectComponent.getColors()[3] != 0) {
                float alpha = player.playerEffect_rush.effectComponent.getColors()[3] - Game.getDeltaTime() * 4;
                if (alpha < 0) alpha = 0;

                if (!Player.pref.getBoolean("setting2", true)) {
                    alpha = 0;
                }

                player.playerEffect_rush.effectComponent.setColors(new float[] {
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha
                });
            }

            if (player.playerEffect_rushStab.effectComponent.getColors()[3] != 0) {
                float alpha = player.playerEffect_rushStab.effectComponent.getColors()[3] - Game.getDeltaTime() * 4;
                if (alpha < 0) alpha = 0;

                if (!Player.pref.getBoolean("setting2", true)) {
                    alpha = 0;
                }

                player.playerEffect_rushStab.effectComponent.setColors(new float[] {
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha,
                        1, 1, 1, alpha
                });
            }

            if (player.sp_run == null && time > 0.3f) {
                player.sp_run = SoundPlayer.playSound(Game.instance, R.raw.run, -1, 1, 3);
            }
        }
        else if (state == STATE.RUSH) {
            if (animationComponent.getNowAnim() != 1) {
                animationComponent.play(1);
            }

            playerStateComponent.changeState(PlayerStateComponent.ACTION.RUN);

            float speed = 1.5f + ((time > 0.7f) ? 0.7f : time);
            object.getTransform().position.x += Game.getDeltaTime() * 10f * ((dir == DIR.RIGHT) ? 1 : -1) * speed;

            float alpha = ((time > 0.25f) ? 1 : time * 4);

            if (!Player.pref.getBoolean("setting2", true)) {
                alpha = 0;
            }

            player.playerEffect_rush.effectComponent.setColors(new float[] {
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha
            });

            if (player.sp_run == null && time > 0.3f) {
                player.sp_run = SoundPlayer.playSound(Game.instance, R.raw.run, -1, 1, 3);
            }
        }
        else if (state == STATE.RUSH_STAB) {
            if (animationComponent.getNowAnim() != 1) {
                animationComponent.play(1);
            }

            playerStateComponent.changeState(PlayerStateComponent.ACTION.RUN);

            float speed = 1.5f + ((time > 0.7f) ? 0.7f : time);
            object.getTransform().position.x += Game.getDeltaTime() * 9f * ((dir == DIR.RIGHT) ? 1 : -1) * speed;

            float alpha = ((time > 0.25f) ? 1 : time * 4);

            if (!Player.pref.getBoolean("setting2", true)) {
                alpha = 0;
            }

            player.playerEffect_rush.effectComponent.setColors(new float[]{
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha
            });
            player.playerEffect_rushStab.effectComponent.setColors(new float[]{
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha
            });

            if (player.sp_run == null && time > 0.3f) {
                player.sp_run = SoundPlayer.playSound(Game.instance, R.raw.run, -1, 1, 3);
            }
        }
    }

    @Override
    public void finish() {

    }

    public void setDir(DIR dir) {
        if (this.dir != dir && (
                playerStateComponent.action == PlayerStateComponent.ACTION.RUN
            || playerStateComponent.action == PlayerStateComponent.ACTION.WALK
            || playerStateComponent.action == PlayerStateComponent.ACTION.DEFAULT
            || playerStateComponent.action == PlayerStateComponent.ACTION.REST)) {
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

    public void setCompulsionState(STATE state) {
        this.state = state;
        time = 0;
        particleTime = 0;
    }

    public void setState(STATE state) {
        if (state == STATE.RUSH
                && this.state != STATE.RUSH) {
            this.state = STATE.RUSH;
            time = 0;
            particleTime = 0;
        }
        else if (state == STATE.RUSH_STAB
                && this.state != STATE.RUSH_STAB) {
            this.state = STATE.RUSH_STAB;
            time = 0;
            particleTime = 0;
        }
        else if (playerStateComponent.action == PlayerStateComponent.ACTION.RUN
            || playerStateComponent.action == PlayerStateComponent.ACTION.WALK
            || playerStateComponent.action == PlayerStateComponent.ACTION.DEFAULT
            || playerStateComponent.action == PlayerStateComponent.ACTION.REST) {
            if (state == STATE.IDLE
                    && this.state != STATE.IDLE) {
                this.state = STATE.IDLE;
                time = 0;
                particleTime = 0;
            }
            else if (state == STATE.WALK
                    && this.state != STATE.WALK) {
                this.state = STATE.WALK;
                time = 0;
                particleTime = 0;
            }
            else if (state == STATE.RUN
                    && this.state != STATE.RUN) {
                this.state = STATE.RUN;
                time = 0;
                particleTime = 0;
            }
        }
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getTime() {
        return time;
    }
}
