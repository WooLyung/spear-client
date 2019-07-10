package com.example.SpearClient.GraphicSystem;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.Types.Vector;

import java.nio.FloatBuffer;

public class RenderTarget {

    public FloatBuffer matrix;
    public FloatBuffer color;
    public float fill;
    public int z_index;
    public int imageCode;
    public float lengthX = 1;
    public float lengthY = 1;
    public Vector anchor;
    public RendererComponent.DIRECTION dir;
}
