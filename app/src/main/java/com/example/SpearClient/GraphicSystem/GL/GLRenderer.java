package com.example.SpearClient.GraphicSystem.GL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.Animation;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.SpearClient.GameSystem.Other.AnimationManager;
import com.example.SpearClient.GraphicSystem.ImageData;
import com.example.SpearClient.GraphicSystem.RenderTarget;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer {

    public static ArrayList<RenderTarget> renderTargets;
    public static ArrayList<ImageData> imageDatas;
    public static int[] imageCode = new int[200];
    private Context context;

    // 이미지에 대한 정보를 저장
    public static void addImage(int image, String name) {
        ImageData imgData = new ImageData();
        imgData.setName(name);
        imgData.setImgCode(image);
        imageDatas.add(imgData);
    }

    // 이미지 정보 목록에서 이름에 해당하는 이미지의 인덱스값을 반환
    public static int findImage(String name) {
        int index = 0;

        for (ImageData imgData : imageDatas)
        {
            if (imgData.getName().equals(name))
                return index;
            index++;
        }
        return -1;
    }

    public GLRenderer(Context context) {
        // 변수 초기화
        imageDatas = new ArrayList<>();
        renderTargets = new ArrayList<>();
        this.context = context;

        // 이미지 정보 목록에 각 이미지들을 미리 추가함

        // 인트로씬 이미지
        addImage(R.drawable.loading_image1, "loading_image1");
        addImage(R.drawable.loading_image2, "loading_image2");
        addImage(R.drawable.intro_image, "intro_image");

        // 버튼들
        addImage(R.drawable.button_test, "button_test");
        addImage(R.drawable.button_login, "button_login");
        addImage(R.drawable.button_fastgame, "button_fastgame");
        addImage(R.drawable.button_tutorial, "button_tutorial");
        addImage(R.drawable.button_settings, "button_settings");
        addImage(R.drawable.button_register, "button_register");
        addImage(R.drawable.button_gamestart, "button_gamestart");
        addImage(R.drawable.button_move_left, "button_move_left");
        addImage(R.drawable.button_move_right, "button_move_right");
        addImage(R.drawable.button_skill1, "button_skill1");
        addImage(R.drawable.button_skill2, "button_skill2");
        addImage(R.drawable.button_regame, "button_regame");
        addImage(R.drawable.button_home, "button_home");

        // 배경들
        addImage(R.drawable.background_ground_small, "background_ground_small");
        addImage(R.drawable.background_ground, "background_ground");
        addImage(R.drawable.background_sky, "background_sky");
        addImage(R.drawable.background_cloud, "background_cloud");
        addImage(R.drawable.background_soil, "background_soil");
        addImage(R.drawable.background_under, "background_under");
        addImage(R.drawable.background_black, "background_black");
        addImage(R.drawable.background_maching, "background_maching");

        // 기사 스킨들
        addImage(R.drawable.knight_purple, "knight_purple");
        addImage(R.drawable.knight_purple_default, "knight_purple_default");

        addImage(R.drawable.knight_purple_shallow_stab_00 , "knight_purple_shallow_stab_00");
        addImage(R.drawable.knight_purple_shallow_stab_01 , "knight_purple_shallow_stab_01");
        addImage(R.drawable.knight_purple_shallow_stab_02 , "knight_purple_shallow_stab_02");
        addImage(R.drawable.knight_purple_shallow_stab_03 , "knight_purple_shallow_stab_03");
        addImage(R.drawable.knight_purple_shallow_stab_04 , "knight_purple_shallow_stab_04");
        addImage(R.drawable.knight_purple_shallow_stab_05 , "knight_purple_shallow_stab_05");
        addImage(R.drawable.knight_purple_shallow_stab_06 , "knight_purple_shallow_stab_06");
        addImage(R.drawable.knight_purple_shallow_stab_07 , "knight_purple_shallow_stab_07");
        addImage(R.drawable.knight_purple_shallow_stab_08 , "knight_purple_shallow_stab_08");
        addImage(R.drawable.knight_purple_shallow_stab_09 , "knight_purple_shallow_stab_09");
        addImage(R.drawable.knight_purple_shallow_stab_10 , "knight_purple_shallow_stab_10");
        addImage(R.drawable.knight_purple_shallow_stab_11 , "knight_purple_shallow_stab_11");
        addImage(R.drawable.knight_purple_shallow_stab_12 , "knight_purple_shallow_stab_12");
        addImage(R.drawable.knight_purple_shallow_stab_13 , "knight_purple_shallow_stab_13");
        addImage(R.drawable.knight_purple_shallow_stab_14 , "knight_purple_shallow_stab_14");
        addImage(R.drawable.knight_purple_shallow_stab_15 , "knight_purple_shallow_stab_15");

        addImage(R.drawable.knight_purple_fall_00 , "knight_purple_fall_00");
        addImage(R.drawable.knight_purple_fall_01 , "knight_purple_fall_01");
        addImage(R.drawable.knight_purple_fall_02 , "knight_purple_fall_02");

        addImage(R.drawable.knight_purple_rush_00 , "knight_purple_rush_00");
        addImage(R.drawable.knight_purple_rush_01 , "knight_purple_rush_01");
        addImage(R.drawable.knight_purple_rush_02 , "knight_purple_rush_02");

        addImage(R.drawable.knight_purple_rush_stab_00 , "knight_purple_rush_stab_00");
        addImage(R.drawable.knight_purple_rush_stab_01 , "knight_purple_rush_stab_01");
        addImage(R.drawable.knight_purple_rush_stab_02 , "knight_purple_rush_stab_02");
        addImage(R.drawable.knight_purple_rush_stab_03 , "knight_purple_rush_stab_03");
        addImage(R.drawable.knight_purple_rush_stab_04 , "knight_purple_rush_stab_04");
        addImage(R.drawable.knight_purple_rush_stab_05 , "knight_purple_rush_stab_05");
        addImage(R.drawable.knight_purple_rush_stab_06 , "knight_purple_rush_stab_06");
        addImage(R.drawable.knight_purple_rush_stab_07 , "knight_purple_rush_stab_07");

        addImage(R.drawable.knight_purple_deep_stab_00 , "knight_purple_deep_stab_00");
        addImage(R.drawable.knight_purple_deep_stab_01 , "knight_purple_deep_stab_01");
        addImage(R.drawable.knight_purple_deep_stab_02 , "knight_purple_deep_stab_02");
        addImage(R.drawable.knight_purple_deep_stab_03 , "knight_purple_deep_stab_03");
        addImage(R.drawable.knight_purple_deep_stab_04 , "knight_purple_deep_stab_04");
        addImage(R.drawable.knight_purple_deep_stab_05 , "knight_purple_deep_stab_05");
        addImage(R.drawable.knight_purple_deep_stab_06 , "knight_purple_deep_stab_06");
        addImage(R.drawable.knight_purple_deep_stab_07 , "knight_purple_deep_stab_07");
        addImage(R.drawable.knight_purple_deep_stab_08 , "knight_purple_deep_stab_08");
        addImage(R.drawable.knight_purple_deep_stab_09 , "knight_purple_deep_stab_09");
        addImage(R.drawable.knight_purple_deep_stab_10 , "knight_purple_deep_stab_10");
        addImage(R.drawable.knight_purple_deep_stab_11 , "knight_purple_deep_stab_11");
        addImage(R.drawable.knight_purple_deep_stab_12 , "knight_purple_deep_stab_12");
        addImage(R.drawable.knight_purple_deep_stab_13 , "knight_purple_deep_stab_13");
        addImage(R.drawable.knight_purple_deep_stab_14 , "knight_purple_deep_stab_14");

        addImage(R.drawable.knight_purple_skim_00 , "knight_purple_skim_00");
        addImage(R.drawable.knight_purple_skim_01 , "knight_purple_skim_01");
        addImage(R.drawable.knight_purple_skim_02 , "knight_purple_skim_02");
        addImage(R.drawable.knight_purple_skim_03 , "knight_purple_skim_03");
        addImage(R.drawable.knight_purple_skim_04 , "knight_purple_skim_04");
        addImage(R.drawable.knight_purple_skim_05 , "knight_purple_skim_05");
        addImage(R.drawable.knight_purple_skim_06 , "knight_purple_skim_06");
        addImage(R.drawable.knight_purple_skim_07 , "knight_purple_skim_07");
        addImage(R.drawable.knight_purple_skim_08 , "knight_purple_skim_08");
        addImage(R.drawable.knight_purple_skim_09 , "knight_purple_skim_09");
        addImage(R.drawable.knight_purple_skim_10 , "knight_purple_skim_10");
        addImage(R.drawable.knight_purple_skim_11 , "knight_purple_skim_11");
        addImage(R.drawable.knight_purple_skim_12 , "knight_purple_skim_12");
        addImage(R.drawable.knight_purple_skim_13 , "knight_purple_skim_13");
        addImage(R.drawable.knight_purple_skim_14 , "knight_purple_skim_14");
        addImage(R.drawable.knight_purple_skim_15 , "knight_purple_skim_15");
        addImage(R.drawable.knight_purple_skim_16 , "knight_purple_skim_16");

        addImage(R.drawable.knight_purple_defenceless_00, "knight_purple_defenceless_00");
        addImage(R.drawable.knight_purple_defenceless_01, "knight_purple_defenceless_01");
        addImage(R.drawable.knight_purple_defenceless_02, "knight_purple_defenceless_02");
        addImage(R.drawable.knight_purple_defenceless_03, "knight_purple_defenceless_03");
        addImage(R.drawable.knight_purple_defenceless_04, "knight_purple_defenceless_04");
        addImage(R.drawable.knight_purple_defenceless_05, "knight_purple_defenceless_05");
        addImage(R.drawable.knight_purple_defenceless_06, "knight_purple_defenceless_06");
        addImage(R.drawable.knight_purple_defenceless_07, "knight_purple_defenceless_07");
        addImage(R.drawable.knight_purple_defenceless_08, "knight_purple_defenceless_08");
        addImage(R.drawable.knight_purple_defenceless_09, "knight_purple_defenceless_09");
        addImage(R.drawable.knight_purple_defenceless_10, "knight_purple_defenceless_10");
        addImage(R.drawable.knight_purple_defenceless_11, "knight_purple_defenceless_11");
        addImage(R.drawable.knight_purple_defenceless_12, "knight_purple_defenceless_12");
        addImage(R.drawable.knight_purple_defenceless_13, "knight_purple_defenceless_13");
        addImage(R.drawable.knight_purple_defenceless_14, "knight_purple_defenceless_14");
        addImage(R.drawable.knight_purple_defenceless_15, "knight_purple_defenceless_15");

        // 말 파츠
        addImage(R.drawable.horse_body, "horse_body");
        addImage(R.drawable.horse_head, "horse_head");
        addImage(R.drawable.horse_neck, "horse_neck");
        addImage(R.drawable.horse_leg_right, "horse_leg_right");
        addImage(R.drawable.horse_leg_left, "horse_leg_left");

       // 보드들
        addImage(R.drawable.board, "board");
        addImage(R.drawable.board_skills, "board_skills");
        addImage(R.drawable.board_skin, "board_skin");
        addImage(R.drawable.board_gray, "board_gray");

        // UI
        addImage(R.drawable.hpbar_back, "hpbar_back");
        addImage(R.drawable.hpbar_front, "hpbar_front");
        addImage(R.drawable.hpbar_smooth, "hpbar_smooth");
        addImage(R.drawable.line, "line");
        addImage(R.drawable.pointer_enemy, "pointer_enemy");
        addImage(R.drawable.pointer_player, "pointer_player");

        // 스킬
        addImage(R.drawable.skill_none, "skill_none");
        addImage(R.drawable.skill_avoid, "skill_avoid");
        addImage(R.drawable.skill_rush, "skill_rush");
        addImage(R.drawable.skill_skim, "skill_skim");
        addImage(R.drawable.skill_shallow_stab, "skill_shallow_stab");
        addImage(R.drawable.skill_deep_stab, "skill_deep_stab");
        addImage(R.drawable.skill_rush_stab, "skill_rush_stab");
        addImage(R.drawable.skill_avoid_black, "skill_avoid_black");
        addImage(R.drawable.skill_rush_black, "skill_rush_black");
        addImage(R.drawable.skill_skim_black, "skill_skim_black");
        addImage(R.drawable.skill_shallow_stab_black, "skill_shallow_stab_black");
        addImage(R.drawable.skill_deep_stab_black, "skill_deep_stab_black");
        addImage(R.drawable.skill_rush_stab_black, "skill_rush_stab_black");

        // 파티클
        addImage(R.drawable.particle_dirt, "particle_dirt");

        // 기타
        addImage(R.drawable.left_bricks, "left_bricks");
        addImage(R.drawable.right_bricks, "right_bricks");
        addImage(R.drawable.place, "place");
        addImage(R.drawable.empty, "empty");
        addImage(R.drawable.blood, "blood");

        AnimationManager.playerAnims.add(new ArrayList<int[]>());
        ArrayList<int[]> anim = AnimationManager.playerAnims.get(0);

        int[] anim_default = { findImage("knight_purple_default") };
        anim.add(anim_default);

        int[] anim_shallow_stab = new int[16];
        for (int i = 0; i < 16; i++) {
            if (i >= 10)
                anim_shallow_stab[i] = findImage("knight_purple_shallow_stab_" + i);
            else
                anim_shallow_stab[i] =  findImage("knight_purple_shallow_stab_0" + i);
        }
        anim.add(anim_shallow_stab);

        int[] anim_deep_stab = new int[15];
        for (int i = 0; i < 15; i++) {
            if (i >= 10)
                anim_deep_stab[i] = findImage("knight_purple_deep_stab_" + i);
            else
                anim_deep_stab[i] =  findImage("knight_purple_deep_stab_0" + i);
        }
        anim.add(anim_deep_stab);

        int[] anim_rush_stab = new int[30];
        anim_rush_stab[0] = findImage("knight_purple_rush_stab_00");
        anim_rush_stab[1] = findImage("knight_purple_rush_stab_01");
        anim_rush_stab[2] = findImage("knight_purple_rush_stab_02");
        anim_rush_stab[3] = findImage("knight_purple_rush_stab_03");
        for (int i = 4; i <= 26; i++) {
            anim_rush_stab[i] = findImage("knight_purple_rush_stab_04");
        }
        anim_rush_stab[27] = findImage("knight_purple_rush_stab_05");
        anim_rush_stab[28] = findImage("knight_purple_rush_stab_06");
        anim_rush_stab[29] = findImage("knight_purple_rush_stab_07");


        anim.add(anim_rush_stab);

        int[] anim_rush = new int[3];
        for (int i = 0; i < 3; i++) {
            if (i >= 10)
                anim_rush[i] = findImage("knight_purple_rush_" + i);
            else
                anim_rush[i] =  findImage("knight_purple_rush_0" + i);
        }
        anim.add(anim_rush);

        int[] anim_skim = new int[17];
        for (int i = 0; i < 17; i++) {
            if (i >= 10)
                anim_skim[i] = findImage("knight_purple_skim_" + i);
            else
                anim_skim[i] =  findImage("knight_purple_skim_0" + i);
        }
        anim.add(anim_skim);

        int[] anim_fall = new int[3];
        for (int i = 0; i < 3; i++) {
            if (i >= 10)
                anim_fall[i] = findImage("knight_purple_fall_" + i);
            else
                anim_fall[i] =  findImage("knight_purple_fall_0" + i);
        }
        anim.add(anim_fall);

        int[] anim_defenceless = new int[16];
        for (int i = 0; i < 16; i++) {
            if (i >= 10)
                anim_defenceless[i] = findImage("knight_purple_defenceless_" + i);
            else
                anim_defenceless[i] =  findImage("knight_purple_defenceless_0" + i);
        }
        anim.add(anim_defenceless);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 렌더 버퍼를 지움
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // 카메라의 정보를 행렬 스택에 담음
        gl.glLoadIdentity();
        gl.glScalef(Game.engine.nowScene.camera.getZoomX() / (float)GLView.defaultWidth, Game.engine.nowScene.camera.getZoomY() / (float)GLView.defaultHeight, 1);
        gl.glRotatef(Game.engine.nowScene.camera.getAngle(), 0, 0, 1);
        gl.glTranslatef(-Game.engine.nowScene.camera.getPosition().x, -Game.engine.nowScene.camera.getPosition().y, 0);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        // 렌더 타겟에 렌더 대상들을 담음
        renderTargets.clear();
        Game.engine.render(gl);

        // 행렬 스택 초기화
        gl.glPopMatrix();
        gl.glLoadIdentity();

        // 렌더 타겟을 z-index에 따라 정렬
        Collections.sort(renderTargets, new Comparator<RenderTarget>() {
            @Override
            public int compare(RenderTarget t1, RenderTarget t2) {
                return t1.z_index - t2.z_index;
            }
        });

        // 렌더링
        for (RenderTarget renderTarget : renderTargets) {
            // 행렬 초기화
            gl.glLoadIdentity();

            // 블렌드 허용
            gl.glEnable(GL10.GL_BLEND);

            // 버텍스, 텍스쳐, 컬러, 인덱스 버퍼를 적용시킴
            if (renderTarget.fill == 1 && renderTarget.anchor.x == 0.5f && renderTarget.anchor.y == 0.5f) { // fill과 앵커에 따라 버텍스 조절
                gl.glVertexPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(renderTarget.imageCode).getVertexBuffer());
                gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(renderTarget.imageCode).getTextureBuffer());
            }
            else {
                float[] vertex = new float[8], texture = new float[8];

                if (renderTarget.dir == RendererComponent.DIRECTION.RIGHT) {
                    vertex = new float[]{
                            renderTarget.fill * -2 + 1, 1,
                            1, 1,
                            1, -1,
                            renderTarget.fill * -2 + 1, -1
                    };
                    texture = new float[]{
                            1 - renderTarget.fill, 0,
                            1, 0,
                            1, 1,
                            1 - renderTarget.fill, 1
                    };
                }
                else if (renderTarget.dir == RendererComponent.DIRECTION.LEFT) {
                    vertex = new float[]{
                            -1, 1,
                            renderTarget.fill * 2 - 1, 1,
                            renderTarget.fill * 2 - 1, -1,
                            -1, -1
                    };
                    texture = new float[]{
                            0, 0,
                            renderTarget.fill, 0,
                            renderTarget.fill, 1,
                            0, 1
                    };
                }
                else if (renderTarget.dir == RendererComponent.DIRECTION.UP) {
                    vertex = new float[]{
                            -1, 1,
                            1, 1,
                            1, renderTarget.fill * -2 + 1,
                            -1, renderTarget.fill * -2 + 1
                    };
                    texture = new float[]{
                            0, 0,
                            1, 0,
                            1, renderTarget.fill,
                            0, renderTarget.fill
                    };
                }
                else if (renderTarget.dir == RendererComponent.DIRECTION.DOWN) {
                    vertex = new float[]{
                            -1, renderTarget.fill * 2 - 1,
                            1, renderTarget.fill * 2 - 1,
                            1, -1,
                            -1, -1
                    };
                    texture = new float[]{
                            0, 1 - renderTarget.fill,
                            1, 1 - renderTarget.fill,
                            1, 1,
                            0, 1
                    };
                }

                for (int i = 0; i < 8; i++) {
                    vertex[i] += ((i % 2 == 1) ? (renderTarget.anchor.y - 0.5f) : (renderTarget.anchor.x - 0.5f)) * 2;
                    vertex[i] *= (i % 2 == 1) ? GLRenderer.imageDatas.get(renderTarget.imageCode).getHeight() : GLRenderer.imageDatas.get(renderTarget.imageCode).getWidth();
                    vertex[i] /= 100f;
                }

                // 버퍼 설정
                ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertex.length * 4);
                byteBuf.order(ByteOrder.nativeOrder());
                FloatBuffer vertexBuffer = byteBuf.asFloatBuffer();
                vertexBuffer.put(vertex);
                vertexBuffer.position(0);

                byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
                byteBuf.order(ByteOrder.nativeOrder());
                FloatBuffer textureBuffer = byteBuf.asFloatBuffer();
                textureBuffer.put(texture);
                textureBuffer.position(0);

                // 버퍼 적용
                gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
                gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
            }
            gl.glBindTexture(GL10.GL_TEXTURE_2D, GLRenderer.imageCode[renderTarget.imageCode]);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, GLRenderer.imageDatas.get(renderTarget.imageCode).getColorBuffer());

            // 행렬을 불러옴
            gl.glMultMatrixf(renderTarget.matrix);

            // 버퍼들을 허용
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

            // 알파 블렌드
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            // 실제 렌더링
            gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, GLRenderer.imageDatas.get(renderTarget.imageCode).getIndex().length, GL10.GL_UNSIGNED_SHORT, GLRenderer.imageDatas.get(renderTarget.imageCode).getIndexBuffer());

            // 허용된 것을 다시 비허용
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl.glDisable(GL10.GL_BLEND);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
        // 렌더러의 여러 값들을 초기화
        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glGenTextures(200, imageCode, 0);

        Bitmap bitmap;

        for (int i = 0; i < imageDatas.size(); i++) {
            // 이미지 정보 목록에 실제 이미지를 비트맵으로 저장
            gl.glBindTexture(GL10.GL_TEXTURE_2D, imageCode[i]);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            bitmap = BitmapFactory.decodeResource(context.getResources(), imageDatas.get(i).getImgCode());
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

            // 이미지 크기를 저장
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), imageDatas.get(i).getImgCode(), options);
            imageDatas.get(i).setWidth(options.outWidth);
            imageDatas.get(i).setHeight(options.outHeight);

            bitmap.recycle();

            // 이미지 정보의 버텍스 버퍼를 화면 크기와 이미지 크기에 맞도록 조절
            float[] vertices = imageDatas.get(i).getVertices();
            for (int j = 0; j < vertices.length; j++) {
                if (j % 2 == 0) {
                    vertices[j] *= imageDatas.get(i).getWidth() / 100f;
                }
                else {
                    vertices[j] *= imageDatas.get(i).getHeight() / 100f;
                }
            }
            imageDatas.get(i).setVertices(vertices);
        }
    }
}