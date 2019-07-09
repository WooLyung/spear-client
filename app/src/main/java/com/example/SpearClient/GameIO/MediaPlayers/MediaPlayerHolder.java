package com.example.SpearClient.GameIO.MediaPlayers;

import android.media.MediaPlayer;

public class MediaPlayerHolder {
    public MediaPlayer mp;
    public float time;
    public boolean isFade;
    public boolean isDelete;
    public int resource;

    public MediaPlayerHolder(MediaPlayer mp, int resource, boolean isFade) {
        this.mp = mp;
        this.isFade = isFade;
        this.resource = resource;
        this.isDelete = false;
    }
}