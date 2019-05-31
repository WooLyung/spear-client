package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.RegisterBoard;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginBoard;
import com.example.SpearClient.GameSystem.Scene.Scenes.LoginScene;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class ToLoginBoard extends GameObject {
    private TextRenderer textRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        transform = new GUITransform();
        attachComponent(transform);

        textRenderer = new TextRenderer();
        attachComponent(textRenderer);

        textRenderer.getTextView().setTextColor(Game.instance.getResources().getColor(R.color.loginColor));
        textRenderer.getTextView().setText("로그인");
        textRenderer.getTextView().setTextSize(16);
        transform.position.x = 2.1f;
        transform.position.y = -1.2f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Math.abs(Input.getTouchUIPos(i).x - transform.position.x) <= 300 / 300f
                        && Math.abs(Input.getTouchUIPos(i).y - transform.position.y) <= 60 / 200f) { // 버튼을 클릭했을 경우
                    Game.engine.changeScene(new LoginScene());
                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
