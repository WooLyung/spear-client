package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.util.Log;
import android.widget.Toast;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class MachingWaitScene extends Scene {
    float time = 0;

    @Override
    public void start() {
    }

    @Override
    public void update() {
        super.update();

        if (time != -1) {
            time += Game.getDeltaTime();

            if (time > 0.5f) {
                time = -1;

                enter();
            }
        }
    }

    private void enter() {
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject("{\"isRank\":" + MainScene.selectedGame.equals("rank") + "}");

            SocketIOBuilder.getInstance().enter(jsonObject, new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    Game.instance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(args[0].toString());
                                String message = jsonObject.getString("message");
                                if (message.equals("enter failed")) {
                                    Toast.makeText(Game.instance, "빠른 매칭에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else if (message.equals("enter complete")) {
                                    if (jsonObject.getBoolean("startGame")) {
                                        Game.engine.changeScene(new InGameScene());
                                    }
                                    else {
                                        Game.engine.changeScene(new MachingScene());
                                    }
                                }
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
