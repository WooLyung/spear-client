package com.example.SpearClient.GameSystem.Component.Components.AnimationComponent;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.Animation;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.AnimationData;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.AnimationDataElement;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.AnimationState;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.TransformComponent;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.AnimationStateElement;
import com.example.SpearClient.Main.Game;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnimationComponent extends Component {

    private ArrayList<Animation> animations = new ArrayList<>();
    private int nowAnim = -1;

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    public int getNowAnim() {
        return nowAnim;
    }

    public void setNowAnim(int nowAnim) {
        this.nowAnim = nowAnim;
    }

    @Override
    public void start() {
        setName("animationComponent");
    }

    @Override
    public void update() {
        if (nowAnim != -1) {
            Animation nowAnimation = animations.get(nowAnim);
            boolean isEnd = true;

            for (int i = 0; i < nowAnimation.getAnimationDatas().size(); i++) {
                AnimationData animationData = nowAnimation.getAnimationDatas().get(i);
                AnimationState animationState = nowAnimation.getAnimationStates().get(i);
                TransformComponent transform = animationData.getObj().getTransform();

                if (animationState.getRot() != null) { // 각도 변경
                    if (animationState.getRot().getNowMotion() != -1) { // 이미 끝난 상태가 아닐 경우
                        animationState.getRot().setTime(animationState.getRot().getTime() + Game.deltaTime);
                        float value = animationData.getRot().get(animationState.getRot().getNowMotion()).getStart()
                                + (animationData.getRot().get(animationState.getRot().getNowMotion()).getEnd() - animationData.getRot().get(animationState.getRot().getNowMotion()).getStart())
                                / animationData.getRot().get(animationState.getRot().getNowMotion()).getTime() * animationState.getRot().getTime();
                        transform.angle = value; // 값 수정

                        if (animationState.getRot().getTime() > animationData.getRot().get(animationState.getRot().getNowMotion()).getTime()) { // 애니메이션이 끝났을 경우
                            transform.angle = animationData.getRot().get(animationState.getRot().getNowMotion()).getEnd(); // 값을 마지막 값으로 변경
                            animationState.getRot().setNowMotion(animationState.getRot().getNowMotion() + 1); // 다음 동작으로 넘어감
                            animationState.getRot().setTime(0);
                            if (animationState.getRot().getNowMotion() == animationData.getRot().size()) { // 마지막 동작일 경우 해당 애니메이션 종료
                                animationState.getRot().setNowMotion(-1);
                            }
                            else {
                                isEnd = false;
                            }
                        }
                        else {
                            isEnd = false;
                        }
                    }
                }
                if (animationState.getPosX() != null) { // 위치 x 변경
                    if (animationState.getPosX().getNowMotion() != -1) { // 이미 끝난 상태가 아닐 경우
                        animationState.getPosX().setTime(animationState.getPosX().getTime() + Game.deltaTime);
                        float value = animationData.getPosX().get(animationState.getPosX().getNowMotion()).getStart()
                                + (animationData.getPosX().get(animationState.getPosX().getNowMotion()).getEnd() - animationData.getPosX().get(animationState.getPosX().getNowMotion()).getStart())
                                / animationData.getPosX().get(animationState.getPosX().getNowMotion()).getTime() * animationState.getPosX().getTime();
                        transform.position.x = value; // 값 수정

                        if (animationState.getPosX().getTime() > animationData.getPosX().get(animationState.getPosX().getNowMotion()).getTime()) { // 애니메이션이 끝났을 경우
                            transform.position.x = animationData.getPosX().get(animationState.getPosX().getNowMotion()).getEnd(); // 값을 마지막 값으로 변경
                            animationState.getPosX().setNowMotion(animationState.getPosX().getNowMotion() + 1); // 다음 동작으로 넘어감
                            animationState.getPosX().setTime(0);
                            if (animationState.getPosX().getNowMotion() == animationData.getPosX().size()) { // 마지막 동작일 경우 해당 애니메이션 종료
                                animationState.getPosX().setNowMotion(-1);
                            }
                            else {
                                isEnd = false;
                            }
                        }
                        else {
                            isEnd = false;
                        }
                    }
                }
                if (animationState.getPosY() != null) { // 위치 y 변경
                    if (animationState.getPosY().getNowMotion() != -1) { // 이미 끝난 상태가 아닐 경우
                        animationState.getPosY().setTime(animationState.getPosY().getTime() + Game.deltaTime);
                        float value = animationData.getPosY().get(animationState.getPosY().getNowMotion()).getStart()
                                + (animationData.getPosY().get(animationState.getPosY().getNowMotion()).getEnd() - animationData.getPosY().get(animationState.getPosY().getNowMotion()).getStart())
                                / animationData.getPosY().get(animationState.getPosY().getNowMotion()).getTime() * animationState.getPosY().getTime();
                        transform.position.y = value; // 값 수정

                        if (animationState.getPosY().getTime() > animationData.getPosY().get(animationState.getPosY().getNowMotion()).getTime()) { // 애니메이션이 끝났을 경우
                            transform.position.y = animationData.getPosY().get(animationState.getPosY().getNowMotion()).getEnd(); // 값을 마지막 값으로 변경
                            animationState.getPosY().setNowMotion(animationState.getPosY().getNowMotion() + 1); // 다음 동작으로 넘어감
                            animationState.getPosY().setTime(0);
                            if (animationState.getPosY().getNowMotion() == animationData.getPosY().size()) { // 마지막 동작일 경우 해당 애니메이션 종료
                                animationState.getPosY().setNowMotion(-1);
                            }
                            else {
                                isEnd = false;
                            }
                        }
                        else {
                            isEnd = false;
                        }
                    }
                }
                if (animationState.getScaleX() != null) { // 크기 x 변경
                    if (animationState.getScaleX().getNowMotion() != -1) { // 이미 끝난 상태가 아닐 경우
                        animationState.getScaleX().setTime(animationState.getScaleX().getTime() + Game.deltaTime);
                        float value = animationData.getScaleX().get(animationState.getScaleX().getNowMotion()).getStart()
                                + (animationData.getScaleX().get(animationState.getScaleX().getNowMotion()).getEnd() - animationData.getScaleX().get(animationState.getScaleX().getNowMotion()).getStart())
                                / animationData.getScaleX().get(animationState.getScaleX().getNowMotion()).getTime() * animationState.getScaleX().getTime();
                        transform.scale.x = value; // 값 수정

                        if (animationState.getScaleX().getTime() > animationData.getScaleX().get(animationState.getScaleX().getNowMotion()).getTime()) { // 애니메이션이 끝났을 경우
                            transform.scale.x = animationData.getScaleX().get(animationState.getScaleX().getNowMotion()).getEnd(); // 값을 마지막 값으로 변경
                            animationState.getScaleX().setNowMotion(animationState.getScaleX().getNowMotion() + 1); // 다음 동작으로 넘어감
                            animationState.getScaleX().setTime(0);
                            if (animationState.getScaleX().getNowMotion() == animationData.getScaleX().size()) { // 마지막 동작일 경우 해당 애니메이션 종료
                                animationState.getScaleX().setNowMotion(-1);
                            }
                            else {
                                isEnd = false;
                            }
                        }
                        else {
                            isEnd = false;
                        }
                    }
                }
                if (animationState.getScaleY() != null) { // 크기 y 변경
                    if (animationState.getScaleY().getNowMotion() != -1) { // 이미 끝난 상태가 아닐 경우
                        animationState.getScaleY().setTime(animationState.getScaleY().getTime() + Game.deltaTime);
                        float value = animationData.getScaleY().get(animationState.getScaleY().getNowMotion()).getStart()
                                + (animationData.getScaleY().get(animationState.getScaleY().getNowMotion()).getEnd() - animationData.getScaleY().get(animationState.getScaleY().getNowMotion()).getStart())
                                / animationData.getScaleY().get(animationState.getScaleY().getNowMotion()).getTime() * animationState.getScaleY().getTime();
                        transform.scale.y = value; // 값 수정

                        if (animationState.getScaleY().getTime() > animationData.getScaleY().get(animationState.getScaleY().getNowMotion()).getTime()) { // 애니메이션이 끝났을 경우
                            transform.scale.y = animationData.getScaleY().get(animationState.getScaleY().getNowMotion()).getEnd(); // 값을 마지막 값으로 변경
                            animationState.getScaleY().setNowMotion(animationState.getScaleY().getNowMotion() + 1); // 다음 동작으로 넘어감
                            animationState.getScaleY().setTime(0);
                            if (animationState.getScaleY().getNowMotion() == animationData.getScaleY().size()) { // 마지막 동작일 경우 해당 애니메이션 종료
                                animationState.getScaleY().setNowMotion(-1);
                            }
                            else {
                                isEnd = false;
                            }
                        }
                        else {
                            isEnd = false;
                        }
                    }
                }
            }

            if (isEnd) {
                nowAnim = -1;
            }
        }
    }

    @Override
    public void finish() {
    }

    public void addAnimation(String json) {
        try {
            Animation newAnimation = new Animation(); // 모든 파츠 (JSON)
            newAnimation.setAnimationDatas(new ArrayList<AnimationData>());
            newAnimation.setAnimationStates(new ArrayList<AnimationState>());
            animations.add(newAnimation);
            // animations : 모든 애니메이션들을 포함함

            JSONArray allAnims = new JSONArray(json); // 모든 파츠 (JSON)

            for (int i = 0; i < allAnims.length(); i++) {
                AnimationData animationData = new AnimationData(); // 파츠 하나 (각도, 위치, 크기)
                AnimationState animationState = new AnimationState(); // 파츠 하나의 상태 (각도, 위치 크기)

                newAnimation.getAnimationDatas().add(animationData);
                newAnimation.getAnimationStates().add(animationState);

                JSONObject animation = allAnims.getJSONObject(i);
                animationData.setObj(object.findOfName(animation.getString("name")));

                if (!animation.isNull("rot")) { // 파츠의 각도가 존재
                    animationData.setRot(new ArrayList<AnimationDataElement>());
                    animationState.setRot(new AnimationStateElement());

                    JSONArray rot = animation.getJSONArray("rot");
                    for (int j = 0; j < rot.length(); j++) {
                        AnimationDataElement element = new AnimationDataElement();
                        element.setStart((float)rot.getJSONObject(j).getDouble("start"));
                        element.setEnd((float)rot.getJSONObject(j).getDouble("end"));
                        element.setTime((float)rot.getJSONObject(j).getDouble("time"));

                        animationData.getRot().add(element);
                    }
                }
                if (!animation.isNull("posX")) { // 파츠의 위치 x 가 존재
                    animationData.setPosX(new ArrayList<AnimationDataElement>());
                    animationState.setPosX(new AnimationStateElement());

                    JSONArray posX = animation.getJSONArray("posX");
                    for (int j = 0; j < posX.length(); j++) {
                        AnimationDataElement element = new AnimationDataElement();
                        element.setStart((float)posX.getJSONObject(j).getDouble("start"));
                        element.setEnd((float)posX.getJSONObject(j).getDouble("end"));
                        element.setTime((float)posX.getJSONObject(j).getDouble("time"));

                        animationData.getPosX().add(element);
                    }
                }
                if (!animation.isNull("posY")) { // 파츠의 위치 y 가 존재
                    animationData.setPosY(new ArrayList<AnimationDataElement>());
                    animationState.setPosY(new AnimationStateElement());

                    JSONArray posY = animation.getJSONArray("posY");
                    for (int j = 0; j < posY.length(); j++) {
                        AnimationDataElement element = new AnimationDataElement();
                        element.setStart((float)posY.getJSONObject(j).getDouble("start"));
                        element.setEnd((float)posY.getJSONObject(j).getDouble("end"));
                        element.setTime((float)posY.getJSONObject(j).getDouble("time"));

                        animationData.getPosY().add(element);
                    }
                }
                if (!animation.isNull("scaleX")) { // 파츠의 크기 x 가 존재
                    animationData.setScaleX(new ArrayList<AnimationDataElement>());
                    animationState.setScaleX(new AnimationStateElement());

                    JSONArray scaleX = animation.getJSONArray("scaleX");
                    for (int j = 0; j < scaleX.length(); j++) {
                        AnimationDataElement element = new AnimationDataElement();
                        element.setStart((float)scaleX.getJSONObject(j).getDouble("start"));
                        element.setEnd((float)scaleX.getJSONObject(j).getDouble("end"));
                        element.setTime((float)scaleX.getJSONObject(j).getDouble("time"));

                        animationData.getScaleX().add(element);
                    }
                }
                if (!animation.isNull("scaleY")) { // 파츠의 크기 y 가 존재
                    animationData.setScaleY(new ArrayList<AnimationDataElement>());
                    animationState.setScaleY(new AnimationStateElement());

                    JSONArray scaleY = animation.getJSONArray("scaleY");
                    for (int j = 0; j < scaleY.length(); j++) {
                        AnimationDataElement element = new AnimationDataElement();
                        element.setStart((float)scaleY.getJSONObject(j).getDouble("start"));
                        element.setEnd((float)scaleY.getJSONObject(j).getDouble("end"));
                        element.setTime((float)scaleY.getJSONObject(j).getDouble("time"));

                        animationData.getScaleY().add(element);
                    }
                }
            }
        }
        catch (Exception e) {
            Log.e("Animation", "error in addAnimation.");
        }
    }

    public void play(int index) {
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).stop();
        }
        animations.get(index).play();
        nowAnim = index;
    }

    public void stop() {
        if (nowAnim != -1) {
            Animation nowAnimation = animations.get(nowAnim);

            for (int i = 0; i < nowAnimation.getAnimationDatas().size(); i++) {
                AnimationData animationData = nowAnimation.getAnimationDatas().get(i);

                if (animationData.getRot() != null) {
                    animationData.getObj().getTransform().angle = animationData.getRot().get(0).getStart();
                }
                if (animationData.getPosX() != null) {
                    animationData.getObj().getTransform().position.x = animationData.getPosX().get(0).getStart();
                }
                if (animationData.getPosY() != null) {
                    animationData.getObj().getTransform().position.y = animationData.getPosY().get(0).getStart();
                }
                if (animationData.getScaleX() != null) {
                    animationData.getObj().getTransform().scale.x = animationData.getScaleX().get(0).getStart();
                }
                if (animationData.getScaleY() != null) {
                    animationData.getObj().getTransform().scale.y = animationData.getScaleY().get(0).getStart();
                }
            }

            for (int i = 0; i < animations.size(); i++) {
                animations.get(i).stop();
            }
            nowAnim = -1;
        }
    }
}
