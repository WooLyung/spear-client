package com.example.SpearClient.GameIO;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;

public class SoundPlayer {
    public static void playSound (Context context, int resource, final float speed) {
        SoundPool soundPool = new SoundPool
                .Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .build()
                )
                .build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sampleId, 1f, 1f, 0, 0, speed);
            }
        });

        soundPool.load(context, resource, 1);
    }

    public static MediaPlayerHolder playBackgroundSound (Context context, int resource, boolean isFade) {
        MediaPlayerHolder findMPH = MediaPlayerHelper.getInstance().findMediaPlayerHolder(resource);

        if (findMPH != null) {
            return findMPH;
        }
        else {
            MediaPlayer mp = MediaPlayer.create(context, resource);
            mp.setLooping(true);
            mp.start();

            return MediaPlayerHelper.getInstance().addMedia(mp, resource, isFade);
        }
    }
}