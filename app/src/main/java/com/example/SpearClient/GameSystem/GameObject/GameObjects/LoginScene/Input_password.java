package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene;

import android.graphics.Color;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;

public class Input_password extends GameObject {
    private EditTextRenderer editTextRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        setName("input_password");

        transform = new GUITransform();
        attachComponent(transform);

        editTextRenderer = new EditTextRenderer();
        attachComponent(editTextRenderer);

        editTextRenderer.getEditText().setHint("비밀번호를 입력하세요.");
        editTextRenderer.getEditText().setTextColor(Color.WHITE);
        editTextRenderer.getEditText().setHintTextColor(Color.WHITE);
        transform.position.x = 0;
        transform.position.y = 0;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
