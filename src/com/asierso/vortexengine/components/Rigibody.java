package com.asierso.vortexengine.components;

import com.asierso.vortexengine.sceneObjects.GameObject;
import java.util.ArrayList;

/**
 *
 * @author sobblaas
 */
public class Rigibody implements Component {

    private float acceleration = 1.1f;
    private float delta = 0f;
    private float mass = 1f;
    private final ArrayList<GameObject> collisionalObjects = new ArrayList<>();

    public enum RigibodyStates {
        STATIC, DYNAMIC, KINEMATIC
    }

    public enum GravityWeighing {
        SOFT_PRECISION, MASS_PRECISION
    }
    private RigibodyStates bodyState = RigibodyStates.DYNAMIC;
    private GravityWeighing weighing = GravityWeighing.SOFT_PRECISION;

    public void run(GameObject target) {
        boolean isTouchingGround = false;
        if (bodyState == RigibodyStates.DYNAMIC) {
            float fixValue = 2f;
            if (weighing != GravityWeighing.SOFT_PRECISION) {
                if (acceleration>= 0) {
                    fixValue = (mass * acceleration * .65f);
                } else {
                    fixValue = 0- (mass * acceleration * .65f);
                }
            }
            for (var handle : collisionalObjects) {
                if (acceleration > 0 && (int) (target.getPosition().y + target.getBoxSize().y) <= (int) handle.getPosition().y + fixValue && (int) (target.getPosition().y + target.getBoxSize().y) >= (int) handle.getPosition().y - fixValue) {
                    for (float i = target.getPosition().x; i < target.getPosition().x + target.getBoxSize().x; i++) {
                        if (i >= handle.getPosition().x && i <= handle.getPosition().x + handle.getBoxSize().x) {
                            isTouchingGround = true;
                            if (delta > 0) {
                                target.setPosition(target.getPosition().x, handle.getPosition().y - target.getBoxSize().y);
                            }
                        }
                    }
                } else if (acceleration < 0 && (int) target.getPosition().y - fixValue <= (int) (handle.getPosition().y + handle.getBoxSize().y) && (int) (handle.getPosition().y + handle.getBoxSize().y) <= (int) target.getPosition().y + fixValue) {
                    for (float i = target.getPosition().x; i < target.getPosition().x + target.getBoxSize().x; i++) {
                        if (i >= handle.getPosition().x && i <= handle.getPosition().x + handle.getBoxSize().x) {
                            isTouchingGround = true;
                            if (delta > 0) {
                                target.setPosition(target.getPosition().x, handle.getPosition().y + handle.getBoxSize().y);
                            }
                        }
                    }
                }
            }
        }
        if (isTouchingGround || bodyState == RigibodyStates.STATIC) {
            delta = 0;
        } else if (bodyState == RigibodyStates.DYNAMIC || bodyState == RigibodyStates.KINEMATIC) {
            target.setPosition(target.getPosition().x, target.getPosition().y + (delta * acceleration) * mass);
            delta += .1f;
        }
    }

    public final void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public final float getAcceleration() {
        return acceleration;
    }

    public final void setMass(float mass) {
        this.mass = mass;
    }

    public final float getMass() {
        return mass;
    }
    
    public final void setWeighing(GravityWeighing weighing){
        this.weighing = weighing;
    }

    public final ArrayList<GameObject> getCollisionalObjectList() {
        return collisionalObjects;
    }

    public final void flushDelta() {
        delta = 0;
    }

    public final float getDelta() {
        return delta;
    }

    public final void setBodyState(RigibodyStates bodyState) {
        this.bodyState = bodyState;
    }
}
