/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.asierso.vortexengine.miscellaneous;

import org.jsfml.system.Vector2f;

/**
 *
 * @author asier
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
