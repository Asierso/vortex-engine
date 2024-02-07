package com.asierso.vortexengine.miscellaneous.interfaces;

import org.jsfml.system.Vector2f;

/**
 * Default interface for transformable objects
 * @author Asierso
 */
public interface Transform {

    //Position
    /**
     * Get the object position
     *
     * @return The vector of the object position
     */
    Vector2f getPosition();

    /**
     * Set the object position
     *
     * @param position Represents the Vector position of the object
     */
    void setPosition(Vector2f position);

    /**
     * Set the object position
     *
     * @param x Represents the x position of the object
     * @param y Represents the y position of the object
     */
    void setPosition(float x, float y);

    //Rotation
    /**
     * Set the object rotation in a specific angle
     *
     * @param rotation A number of the object rotation angle
     */
    void setRotation(float rotation);

    /**
     * Get object rotation in degrees
     *
     * @return A number of the object rotation angle
     */
    float getRotation();

    //BoxSize
    /**
     * Set object box size. This is mostly used to define object size
     * and hit-box
     *
     * @param size Represents the Vector scale of the object
     */
    void setBoxSize(Vector2f size);

    /**
     * Set object box size. This is mostly used to define object size
     * and hit-box
     *
     * @param x Represents the scale in x of the object
     * @param y Represents the scale in y of the object
     */
    void setBoxSize(float x, float y);

    /**
     * Get object box size
     *
     * @return The box size of the object
     */
    Vector2f getBoxSize();
}
