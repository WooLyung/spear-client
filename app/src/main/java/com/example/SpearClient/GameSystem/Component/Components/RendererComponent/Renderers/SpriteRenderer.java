package com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.RenderTarget;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class SpriteRenderer extends RendererComponent {

    private boolean isFlip = false;
    private float fill = 1;
    private DIRECTION dir = DIRECTION.RIGHT;

    @Override
    public void render(GL10 gl) {
        super.render(gl);

        // 렌더 타겟을 추가
        RenderTarget renderTarget = new RenderTarget();
        renderTarget.imageCode = image[0];
        renderTarget.matrix = FloatBuffer.allocate(4 * 9);
        ((GL11)gl).glGetFloatv(GL11.GL_MODELVIEW_MATRIX, renderTarget.matrix);
        renderTarget.z_index = getZ_index();
        renderTarget.fill = getFill();
        renderTarget.dir = getDir();
        renderTarget.anchor = object.getTransform().anchor;
        renderTarget.lengthX = this.lengthX;
        renderTarget.lengthY = this.lengthY;
        GLRenderer.renderTargets.add(renderTarget);

        // 렌더 타겟 추가 마무리
        gl.glPopMatrix();
        gl.glLoadIdentity();
    }

    @Override
    public void start() {
        setName("spriteRenderer");
        image = new int[1];
    }

    @Override
    public void update() {
    }

    @Override
    public void finish() {
    }

    public void bindingImage(int image) {
        this.image[0] = image;
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

    public DIRECTION getDir() {
        return dir;
    }
}
