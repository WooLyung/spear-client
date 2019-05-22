package com.example.SpearClient.GraphicSystem;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class ImageData {

    private String name;
    private int imgCode;
    private int width;
    private int height;

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;
    private FloatBuffer textureBuffer;
    private FloatBuffer colorBuffer;

    private float[] vertices = {
            -1, 1,
            1, 1,
            1, -1,
            -1, -1};
    private float[] colors = {
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1 };
    private short[] index = { 0, 1, 2, 2, 3, 0 };
    private float[] texture = {
            0, 0,
            1, 0,
            1, 1,
            0, 1};

    public ImageData() {
        // 버텍스 버퍼, 이미지의 모양에 대한 정보
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // 컬러 버퍼, 이미지의 색에 대한 정보
        byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        colorBuffer = byteBuf.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        // 텍스쳐 버퍼, 이미지에 입힐 텍스쳐에 대한 정보
        byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuf.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);

        // 인덱스 버퍼, 삼각형 2개를 사각형 하나로 합치는 용도
        byteBuf = ByteBuffer.allocateDirect(index.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        indexBuffer = byteBuf.asShortBuffer();
        indexBuffer.put(index);
        indexBuffer.position(0);
    }

    public FloatBuffer getColorBuffer() {
        return colorBuffer;
    }

    public FloatBuffer getTextureBuffer() {
        return textureBuffer;
    }

    public FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public ShortBuffer getIndexBuffer() {
        return indexBuffer;
    }

    public void setColors(float[] colors) {
        this.colors = colors;

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        colorBuffer = byteBuf.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    public void setIndex(short[] index) {
        this.index = index;

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(index.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        indexBuffer = byteBuf.asShortBuffer();
        indexBuffer.put(index);
        indexBuffer.position(0);
    }

    public void setTexture(float[] texture) {
        this.texture = texture;

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuf.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public float[] getColors() {
        return colors;
    }

    public float[] getTexture() {
        return texture;
    }

    public float[] getVertices() {
        return vertices;
    }

    public short[] getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgCode() {
        return imgCode;
    }

    public void setImgCode(int imgCode) {
        this.imgCode = imgCode;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
