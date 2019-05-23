package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class RegisterButton extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("test_button"));

        transform = new GUITransform();
        attachComponent(transform);

        transform.position.x = 2;
        transform.position.y = -3;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Vector.distanceDouble(Input.getTouchUIPos(i), transform.position) <= 140/100f * 140/100f) { // 버튼을 클릭했을 경우
                    String id = ((EditTextRenderer) Game.engine.nowScene.findObjectByName("input_id").getComponent("editTextRenderer")).getEditText().getText().toString();
                    String password = ((EditTextRenderer) Game.engine.nowScene.findObjectByName("input_password").getComponent("editTextRenderer")).getEditText().getText().toString();
                    String name = ((EditTextRenderer) Game.engine.nowScene.findObjectByName("input_name").getComponent("editTextRenderer")).getEditText().getText().toString();

                    Log.i("register (id)", id);
                    Log.i("register (password)", password);
                    Log.i("register (name)", name);


                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
