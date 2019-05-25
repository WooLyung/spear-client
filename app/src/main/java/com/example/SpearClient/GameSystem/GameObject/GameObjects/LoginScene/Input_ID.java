package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene;

import android.graphics.Color;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class Input_ID extends GameObject {
    private EditTextRenderer editTextRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        setName("input_id");

        transform = new GUITransform();
        attachComponent(transform);

        editTextRenderer = new EditTextRenderer();
        attachComponent(editTextRenderer);

        editTextRenderer.getEditText().setHint("ID");
        editTextRenderer.getEditText().setTextColor(Game.instance.getResources().getColor(R.color.loginColor));
        editTextRenderer.getEditText().setHintTextColor(Game.instance.getResources().getColor(R.color.loginColorHint));
        editTextRenderer.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        editTextRenderer.getEditText().setText("");
        float d = Game.instance.getResources().getDisplayMetrics().density;
        editTextRenderer.getEditText().setMaxWidth((int)(220 * d));
        editTextRenderer.getEditText().setWidth((int)(220 * d));
        editTextRenderer.getEditText().setTextSize(23);

        transform.position.x = 0;
        transform.position.y = 1;
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
