package com.asierso.vortexengine.miscellaneous;

import org.jsfml.system.Vector2f;

/**
 * Default interface for transformable objects
 * @author Asierso
 */
public interface Transform {

    //Position
    Vector2f getPosition();

    void setPosition(Vector2f position);

    void setPosition(float x, float y);

    //Rotation
    void setRotation(float rotation);

    float getRotation();

    //BoxSize
    void setBoxSize(Vector2f size);

    void setBoxSize(float x, float y);

    Vector2f getBoxSize();
}
