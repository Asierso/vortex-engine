/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.components.animator;

import com.asierso.vortexengine.miscellaneous.Transform;
import com.asierso.vortexengine.sceneObjects.GameObject;
import org.jsfml.system.Vector2f;

/**
 *
 * @author asier
 */
public class KeyFrame implements Transform {

    private Vector2f position;
    private Vector2f boxSize;
    private float rotation;
    private float time = 0;
    private Animator.BlendMode frameBlend = Animator.BlendMode.STATIC;
    
    public KeyFrame(){
        //Void keyframe
    }
    
    public KeyFrame(GameObject reference){
        position = reference.getPosition();
        boxSize = reference.getBoxSize();
        rotation = reference.getRotation();
    }

    public Animator.BlendMode getFrameBlend() {
        return frameBlend;
    }

    public void setFrameBlend(Animator.BlendMode frameBlend) {
        this.frameBlend = frameBlend;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getTime() {
        return time;
    }

    @Override
    public Vector2f getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    @Override
    public void setPosition(float x, float y) {
        this.position = new Vector2f(x, y);
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void setBoxSize(Vector2f size) {
        this.boxSize = size;
    }

    @Override
    public void setBoxSize(float x, float y) {
        this.boxSize = new Vector2f(x, y);
    }

    @Override
    public Vector2f getBoxSize() {
        return boxSize;
    }

}
