package com.example.SpearClient.GameIO.MediaPlayers;

import android.media.MediaPlayer;

public class MediaPlayerHolder {
    public MediaPlayer mp;
    public float time;
    public boolean isFade;
    public boolean isDelete;
    public int resource;
    public float volume;

    public MediaPlayerHolder(MediaPlayer mp, int resource, boolean isFade, float volume) {
        this.mp = mp;
        this.isFade = isFade;
        this.resource = resource;
        this.isDelete = false;
        this.volume = volume;
    }

    public MediaPlayerHolder(MediaPlayer mp, int resource, boolean isFade) {
        this.mp = mp;
        this.isFade = isFade;
        this.resource = resource;
        this.isDelete = false;
        this.volume = 0.3f;
    }
}