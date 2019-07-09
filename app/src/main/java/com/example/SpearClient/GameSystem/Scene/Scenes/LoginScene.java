package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.media.MediaPlayer;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard.ResultBoard;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginBoard;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class LoginScene extends Scene {
    MediaPlayerHolder mph;

    @Override
    public void start() {
        objs.add(new Background());
        objs.add(new LoginBoard());
        objs.add(new Cloud());

        mph = SoundPlayer.playBackgroundSound(Game.instance, R.raw.main, true);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {
        super.finish();

        MediaPlayerHelper.getInstance().mphHolder = mph;
    }
}
