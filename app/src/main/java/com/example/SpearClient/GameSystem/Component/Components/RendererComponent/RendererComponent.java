package com.example.SpearClient.GameSystem.Component.Components.RendererComponent;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLView;

import javax.microedition.khronos.opengles.GL10;

abstract public class RendererComponent extends Component {

    public int[] image;
    private boolean isVisible = true;
    public float lengthX = 1;
    public float lengthY = 1;
    private int z_index = 0;
    public enum DIRECTION {
        UP, DOWN, RIGHT, LEFT
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void render(GL10 gl) {
        if (object.getTransform().getName() == "transform") {
            // transform 컴포넌트가 일반 transform이라면 상대적인 위치 적용
            gl.glPopMatrix();
            gl.glPushMatrix();

            gl.glTranslatef(object.getTransform().position.x, object.getTransform().position.y, 0);
            if (getIsFlip()) {
                gl.glRotatef(180, 0, 1, 0);
            }
            gl.glScalef(object.getTransform().scale.x, object.getTransform().scale.y, 1);
            gl.glRotatef(object.getTransform().angle, 0, 0, 1);
        }
        else {
            // transform 컴포넌트가 GUI용 transform일 경우 절대적인 위치 사용
            gl.glLoadIdentity();

            gl.glTranslatef(object.getTransform().position.x / (float)GLView.defaultWidth, object.getTransform().position.y / (float)GLView.defaultHeight, getZ_index() / 100.f);
            if (getIsFlip()) {
                gl.glRotatef(180, 0, 1, 0);
            }
            gl.glScalef(object.getTransform().scale.x / (float)GLView.defaultWidth, object.getTransform().scale.y / (float)GLView.defaultHeight, 1);
            gl.glRotatef(object.getTransform().angle, 0, 0, 1);
        }

        gl.glPushMatrix();

        for (GameObject child : object.getChilds()) {
            if (child.getRenderer() != null)
            {
                child.getRenderer().render(gl);
            }
        }

        gl.glPopMatrix();
        gl.glPushMatrix();
    }

    public int getZ_index() {
        return z_index;
    }

    public boolean getIsFlip() {
        return false;
    }

    public void setZ_index(int z_index) {
        this.z_index = z_index;
    }
}
