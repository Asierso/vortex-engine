package com.asierso.vortexengine.components.physics;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.objects.GameObject;
import java.util.ArrayList;

/**
 * Basic gravity physics component Allows to a GameObject to fall using MRUA
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
    /**
     * Gravity states that a GameObject with this component can handle
     */
    public enum RigibodyStates {

        /**
         * Object isn't affected by gravity but can interact with collisionable
         * objects
         */
        STATIC,
        /**
         * Object is affected by gravity and physics and can interact with
         * collisionable objects
         */
        DYNAMIC,
        /**
         * Object is affected by gravity and physics but is inmutable by
         * collisonable objects
         */
        KINEMATIC
    }

    /**
     * Sets the method of Rigibody MRUA algoritm to fix gravity speed and final
     * position, based in math position weighing. This could avoid collision
     * errors between dynamic GameObjects
     */
    public enum GravityWeighing {

        /**
         * Adapted for soft objects with low mass values
         */
        SOFT_PRECISION,
        /**
         * Adapted for heavy objects with high mass values
         */
        MASS_PRECISION
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

    /**
     * Set acceleration constant to GameObject
     *
     * @param acceleration Acceleration value
     */
    public final void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Gets the current acceleration value in the time when function is called
     *
     * @return Current acceleration value
     */
    public final float getAcceleration() {
        return acceleration;
    }

    /**
     * Set the mass of the GameObject to a constant value
     *
     * @param mass Mass of GameObject
     */
    public final void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * Gets the current mass of the GameObject
     *
     * @return Current mass of GameObject
     */
    public final float getMass() {
        return mass;
    }

    /**
     * Set the weighing method to fix gravity isues at calculate physics.
     * Weighings are useful to manage how Rigibody works with object colisions
     * with different mass and acceleration
     *
     * @param weighing Weighing method
     */
    public final void setWeighing(GravityWeighing weighing) {
        this.weighing = weighing;
    }

    /**
     * Returns an array of all GameObjects that handled GameObject can collision
     * with
     *
     * @return COllisionable GameObject list
     */
    public final ArrayList<GameObject> getCollisionalObjectList() {
        return collisionalObjects;
    }

    /**
     * Reset delta value. Delta is a tickcount that starts when object starts to
     * move (like time un mrua)
     */
    public final void flushDelta() {
        delta = 0;
    }

    /**
     * Get delta value. Delta is a tickcount that starts when object starts to
     * move (like time un mrua)
     *
     * @return
     */
    public final float getDelta() {
        return delta;
    }

    /**
     * Define the current body state. Body states are used to specify if object
     * can be affected by gravity and can collision with another GameObjects
     *
     * @param bodyState
     */
    public final void setBodyState(RigibodyStates bodyState) {
        this.bodyState = bodyState;
    }
}
