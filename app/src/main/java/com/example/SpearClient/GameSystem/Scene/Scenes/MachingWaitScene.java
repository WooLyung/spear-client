package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.widget.Toast;

import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.Main.Game;
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
            time += Game.getNoneDeltaTime();

            if (time > 1f) {
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
