package com.example.SpearClient.GameIO;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.Main.Game;

public class SoundPlayer {
    public static SharedPreferences pref = null;

    public static SoundPool playSound (Context context, int resource, final int loop, final float speed) {
        if (pref == null) {
            pref =  PreferenceManager.getDefaultSharedPreferences(Game.instance);
        }

        SoundPool soundPool = new SoundPool
                .Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .build()
                )
                .build();

        if (pref.getBoolean("setting3", true)) {
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundPool.play(sampleId, 1f, 1f, 0, loop, speed);
                }
            });

            soundPool.load(context, resource, 1);
        }

        return soundPool;
    }

    public static SoundPool playSound (Context context, int resource, final int loop, final float speed, final float volume) {
        if (pref == null) {
            pref =  PreferenceManager.getDefaultSharedPreferences(Game.instance);
        }

        SoundPool soundPool = new SoundPool
                .Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .build()
                )
                .build();

        if (pref.getBoolean("setting3", true)) {
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundPool.play(sampleId, volume, volume, 0, loop, speed);
                }
            });

            soundPool.load(context, resource, 1);
        }

        return soundPool;
    }

    public static MediaPlayerHolder playBackgroundSound (Context context, int resource, boolean isFade) {
        if (pref == null) {
            pref =  PreferenceManager.getDefaultSharedPreferences(Game.instance);
        }

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

    public static MediaPlayerHolder playBackgroundSound (Context context, int resource, boolean isFade, float volume) {
        if (pref == null) {
            pref =  PreferenceManager.getDefaultSharedPreferences(Game.instance);
        }

        MediaPlayerHolder findMPH = MediaPlayerHelper.getInstance().findMediaPlayerHolder(resource);

        if (findMPH != null) {
            return findMPH;
        }
        else {
            MediaPlayer mp = MediaPlayer.create(context, resource);
            mp.setLooping(true);
            mp.start();

            return MediaPlayerHelper.getInstance().addMedia(mp, resource, isFade, volume);
        }
    }
}