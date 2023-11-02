package com.asierso.vortexengine.components.physics;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.objects.GameObject;

public class Centrifuge implements Component {

    //Basic rigibody component information
    private float acceleration = 1f;
    private float mass = 1f;

    //Similar to delta. Used to acumulate added forces
    private float forceBuffer = 0;

    /**
     * Render GameObject rotation based in Centrifugation. Effect like a washing
     * machine
     *
     * @param target Handled GameObject
     */
    @Override
    public void run(GameObject target) {
        if (forceBuffer > 0.0f) {
            double rotation = target.getRotation() + (forceBuffer * acceleration);
            if (rotation > 360) {
                rotation = rotation - (360 * rotation / 360);
            }
            target.setRotation((float) rotation);
            forceBuffer -= (double) mass / 10;
        }
    }

    /**
     * Get acceleration value
     * @return The current acceleration value
     */
    public final float getAcceleration() {
        return acceleration;
    }

    /**
     * Set the acceleration force of the component to apply
     * @param acceleration Acceleration force number
     */
    public final void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Get the current component mass of the game object
     * @return 
     */
    public final float getMass() {
        return mass;
    }

    /**
     * Set the component mass
     * @param mass Mass number
     */
    public final void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * Add more centrifuge force to forceBuffer. This variable is used to calculate component rotation speed
     * @param force Force number to add
     */
    public void addForce(float force) {
        this.forceBuffer += force;
    }

    /**
     * Set the centrifuge force of the forceBuffer to a constant value. This variable is used to calculate component rotation speed
     * @param force Force numer to set
     */
    public void setForce(float force) {
        this.forceBuffer = force;
    }
    
    /**
     * Get the processing force buffer value
     * @return Force number
     */
    public float getForce(){
        return forceBuffer;
    }
}
