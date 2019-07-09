package com.example.SpearClient.GameIO.MediaPlayers;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.SpearClient.Main.Game;

import java.util.ArrayList;

public class MediaPlayerHelper {
    private ArrayList<MediaPlayerHolder> mediaPlayerHolders = new ArrayList<>();
    private static MediaPlayerHelper instance;
    public MediaPlayerHolder mphHolder;

    public MediaPlayerHelper() {
        instance = this;
    }

    public MediaPlayerHolder findMediaPlayerHolder(int resource) {
        for (MediaPlayerHolder mph : mediaPlayerHolders) {
            if (mph.resource == resource)
                return mph;
        }

        return null;
    }

    public MediaPlayerHolder addMedia(MediaPlayer mp, int resource, boolean isFade) {
        MediaPlayerHolder mph = new MediaPlayerHolder(mp, resource, isFade);
        mediaPlayerHolders.add(mph);

        return mph;
    }

    public void delMedia(MediaPlayerHolder mph) {
        mph.isDelete = true;
        mph.time = 0;
    }

    public void update() {
        ArrayList<MediaPlayerHolder> deleteMphs = new ArrayList<>();

        for (MediaPlayerHolder mph : mediaPlayerHolders) {
            mph.time += Game.getNoneDeltaTime();

            if (mph.isDelete) {
                if (mph.time >= 1 || !mph.isFade) {
                    mph.mp.stop();
                    mph.mp.release();

                    deleteMphs.add(mph);
                }
                else {
                    mph.mp.setVolume(1 - mph.time, 1 - mph.time);
                }
            }
            else if (mph.time >= 1 || !mph.isFade){
                mph.mp.setVolume(1, 1);
            }
            else {
                mph.mp.setVolume(mph.time, mph.time);
            }

        }

        for (MediaPlayerHolder mph : deleteMphs) {
            mediaPlayerHolders.remove(mph);
        }
    }

    public static MediaPlayerHelper getInstance() {
        return instance;
    }
}
