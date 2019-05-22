package com.example.SpearClient.GraphicSystem.GL;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLView extends GLSurfaceView
{
    public static double defaultWidth;
    public static double defaultHeight;
    public static double nowWidth;
    public static double nowHeight;

    public GLView(Context context, Renderer renderer)
    {
        super(context);
        setRenderer(renderer);
        setRenderMode(RENDERMODE_CONTINUOUSLY);
        setFocusableInTouchMode(true);
    }
}