package com.asierso.vortexengine.miscellaneous;

import org.jsfml.system.Vector2f;

/**
 * Default interface for transformable objects
 * @author Asierso
 */
public interface Transform {

    //Position
    public Vector2f getPosition();

    public void setPosition(Vector2f position);

    public void setPosition(float x, float y);

    //Rotation
    public void setRotation(float rotation);

    public float getRotation();

    //BoxSize
    public void setBoxSize(Vector2f size);

    public void setBoxSize(float x, float y);

    public Vector2f getBoxSize();
}
