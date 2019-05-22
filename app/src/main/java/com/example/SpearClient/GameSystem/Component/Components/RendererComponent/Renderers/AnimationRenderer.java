package com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.RenderTarget;
import com.example.SpearClient.Main.Game;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class AnimationRenderer extends RendererComponent {

    private float interval = 1;
    private int nowFrame = 0;
    private float lapse = 0;
    private float fill = 1;
    private DIRECTION dir = DIRECTION.RIGHT;
    private boolean isFlip = false;
    private boolean loop = true;

    @Override
    public void render(GL10 gl) {
        super.render(gl);

        // 렌더 타겟을 추가
        RenderTarget renderTarget = new RenderTarget();
        renderTarget.imageCode = image[nowFrame];
        renderTarget.matrix = FloatBuffer.allocate(4 * 9);
        ((GL11)gl).glGetFloatv(GL11.GL_MODELVIEW_MATRIX, renderTarget.matrix);
        renderTarget.z_index = getZ_index();
        renderTarget.fill = getFill();
        renderTarget.dir = getDir();
        renderTarget.anchor = object.getTransform().anchor;
        GLRenderer.renderTargets.add(renderTarget);

        // 렌더 타겟 추가 마무리
        gl.glPopMatrix();
        gl.glLoadIdentity();
    }

    @Override
    public void start() {
        setName("animationRenderer");
    }

    @Override
    public void update() {
        // 이미지 프레임 변경
        lapse += Game.deltaTime;
        if (lapse >= interval)
        {
            nowFrame = (nowFrame + 1);
            if (loop) nowFrame %= image.length;
            lapse -= interval;
        }
    }

    @Override
    public void finish() {

    }

    public void bindingImage(int[] image) {
        this.image = image;
        nowFrame = 0;
        lapse = 0;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public float getInterval() {
        return interval;
    }

    public void setNowFrame(int nowFrame) {
        this.nowFrame = nowFrame;
    }

    public int getNowFrame() {
        return nowFrame;
    }

    public float getFill() {
        return fill;
    }

    public void setFill(float fill) {
        this.fill = fill;
    }

    public void setDir(DIRECTION dir) {
        this.dir = dir;
    }

    public void setIsFlip(boolean isFlip) {
        this.isFlip = isFlip;
    }

    public boolean getIsFlip() {
        return isFlip;
    }

    public boolean getLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public DIRECTION getDir() {
        return dir;
    }
}
