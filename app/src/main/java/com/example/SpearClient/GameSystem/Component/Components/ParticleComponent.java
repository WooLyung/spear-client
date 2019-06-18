package com.example.SpearClient.GameSystem.Component.Components;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.Main.Game;

public class ParticleComponent extends Component {
    public float lifeTime = 0;
    public float speed = 0;
    public float angle = 0;
    public float airRegistance = 0;

    private Transform transform;

    @Override
    public void start() {
        setName("particleComponent");
    }


    @Override
    public void update() {
        if (transform == null) {
            transform = (Transform) object.getComponent("transform");
        }
        else {
            lifeTime -= Game.getDeltaTime();
            if (lifeTime < 0) {
                object.destroy();
            }

            transform.position.x += speed * Math.cos(Math.PI * angle / 180);
            transform.position.y += speed * Math.sin(Math.PI * angle / 180);

            speed -= airRegistance;
            if (speed < 0) {
                speed = 0;
            }
        }
    }

    @Override
    public void finish() {

    }
}
