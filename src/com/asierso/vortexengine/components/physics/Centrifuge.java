package com.asierso.vortexengine.components.physics;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.sceneObjects.GameObject;

public class Centrifuge implements Component {

    private float acceleration = 1f;
    private float mass = 1f;
    private float forceBuffer = 0;

    @Override
    public void run(GameObject target) {
        if (forceBuffer > 0.0f) {
            float rotation = target.getRotation() + (forceBuffer * acceleration);
            if (rotation > 360) {
                rotation = rotation - (360 * rotation / 360);
            }
            target.setRotation(rotation);
            forceBuffer -= (double)mass/10;
        }
    }

    public final float getAcceleration() {
        return acceleration;
    }

    public final void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public final float getMass() {
        return mass;
    }

    public final void setMass(float mass) {
        this.mass = mass;
    }

    public void addForce(float force) {
        this.forceBuffer = force;
    }
}
