package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.widget.Toast;

import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class MachingScene extends Scene {
    @Override
    public void start() {
        gamestart();
    }

    private void gamestart() {
        SocketIOBuilder.getInstance().gamestart(new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Game.instance.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Game.engine.changeScene(new InGameScene());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}
