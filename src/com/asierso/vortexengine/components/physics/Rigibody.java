package com.asierso.vortexengine.components.physics;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.sceneObjects.GameObject;
import java.util.ArrayList;

/**
 * Basic gravity physics component. Allows to a GameObject to fall. Using MRUA
 *
 * @author Asierso
 */
public class Rigibody implements Component {

    //Basic rigibody component information
    private float acceleration = 1.1f;
    private float delta = 0f;
    private float mass = 1f;
    //Objects that handled object can collide
    private final ArrayList<GameObject> collisionalObjects = new ArrayList<>();

    //Rigibody states and weighing (final position fixer) modification
    public enum RigibodyStates {
        STATIC, DYNAMIC, KINEMATIC
    }

    public enum GravityWeighing {
        SOFT_PRECISION, MASS_PRECISION
    }
    private RigibodyStates bodyState = RigibodyStates.DYNAMIC;
    private GravityWeighing weighing = GravityWeighing.SOFT_PRECISION;

    /**
     * Render and calculate GameObject position based in Rigibody. Simulates the
     * object fall and detects collisions
     *
     * @param target Handled GameObject
     */
    @Override
    public void run(GameObject target) {
        //Storages if handled target is touching a collision in Y axis (reset delta and calculate Weighing)
        boolean isTouchingGround = false;
        //Object can fall and have collisions
        if (bodyState == RigibodyStates.DYNAMIC) {
            //Weighing value calculation
            float fixValue = 2f;
            if (weighing != GravityWeighing.SOFT_PRECISION) {
                if (acceleration >= 0) {
                    fixValue = (mass * acceleration * .65f);
                } else {
                    fixValue = 0 - (mass * acceleration * .65f);
                }
            }
            //Iterate array of collisoinal objects and detects collision with handled GameObejct
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
        if (isTouchingGround || bodyState == RigibodyStates.STATIC) { //Object can't fall but have collisions
            delta = 0;
        } else if (bodyState == RigibodyStates.DYNAMIC || bodyState == RigibodyStates.KINEMATIC) { //Object can fall but have collision inheritance
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

    public final void setWeighing(GravityWeighing weighing) {
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
