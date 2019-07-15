package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene;

import android.util.Log;
import android.widget.Toast;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Scene.Scenes.InGameScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MachingScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.PracticeScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;
import com.example.SpearClient.Types.Vector;

import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Practice extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Transform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("button_practice"));

        transform = new Transform();
        attachComponent(transform);
        transform.scale.x = 0.6f;
        transform.scale.y = 0.6f;
        transform.position.y = -2.1f;
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < 5; i++) {
            if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                if (Math.abs(Input.getTouchWorldPos(i).x - transform.position.x) <= 230 / 100f
                        && Math.abs(Input.getTouchWorldPos(i).y - transform.position.y) <= 80 / 100f) { // 버튼을 클릭했을 경우
                    if (Game.engine.nowScene.findObjectByName("setting") == null) {
                        Game.engine.changeScene(new PracticeScene());
                    }
                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
