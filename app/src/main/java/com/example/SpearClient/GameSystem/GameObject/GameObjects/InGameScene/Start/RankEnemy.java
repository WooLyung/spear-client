package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class RankEnemy extends GameObject {
    TextRenderer textRenderer;
    GUITransform transform;
    float time;

    @Override
    public void start() {
        textRenderer = new TextRenderer();
        attachComponent(textRenderer);
        textRenderer.setText("브론즈");
        textRenderer.setHorizontal(0);
        Game.instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textRenderer.getTextView().setTextColor(Game.instance.getResources().getColor(R.color.bronze));
                textRenderer.getTextView().setTextSize(22);
            }
        });

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.y = -0.35f;
        transform.position.x = (float)GLView.defaultWidth;
    }

    @Override
    public void update() {
        super.update();

        time += Game.getDeltaTime();
        if (time > 5)
            destroy();
        else {
            if (time < 1) {

            }
            else if (time < 2.5f) {
                float time2 = (2.5f - time) / 1.5f;
                time2 = 2 * time2 - time2 * time2;
                transform.position.x = (float)GLView.defaultWidth * time2 + 2.5f;
            }
            else if (time > 3.5f) {
                float time2 = (time - 3.5f) / 1.5f;
                time2 = 2 * time2 - time2 * time2;
                transform.position.x = (float)GLView.defaultWidth * time2 + 2.5f;
            }
        }
    }
}
