package com.asierso.vortexengine.components.animator;

import com.asierso.vortexengine.miscellaneous.interfaces.Transform;
import com.asierso.vortexengine.objects.GameObject;
import org.jsfml.system.Vector2f;

/**
 * Basic Keyframe contains Transform definitions to adjust when Animator is running and in specified time
 * @author Asierso
 */
public class KeyFrame implements Transform {
    //Transform private fields
    private Vector2f position = new Vector2f(0,0);
    private Vector2f boxSize = new Vector2f(0,0);
    private float rotation = 0;
    
    //Keyframe control
    private float time = 0;
    private Animator.BlendMode frameBlend = Animator.BlendMode.STATIC;
    
    /**
     * Creates a void keyframe
     */
    public KeyFrame(){
        //Void keyframe
    }
    
    /**
     * Creates a keyframe basing his vaues in a current GameObject
     * @param reference Referenced GameObject 
     */
    public KeyFrame(GameObject reference){
        position = reference.getPosition();
        boxSize = reference.getBoxSize();
        rotation = reference.getRotation();
    }

    /**
     * Gets the Keyframe blending mode
     * @return Keyframe blend mode
     */
    public Animator.BlendMode getFrameBlend() {
        return frameBlend;
    }

    /**
     * Sets the Keyframe blending mode
     * @param frameBlend Keyframe blend mode
     */
    public void setFrameBlend(Animator.BlendMode frameBlend) {
        this.frameBlend = frameBlend;
    }

    /**
     * Set Keyframe render time
     * @param time Time when frame will be renderer
     */
    public void setTime(float time) {
        this.time = time;
    }

    /**
     * Get keyframe render time
     * @return Time when frame will be renderer
     */
    public float getTime() {
        return time;
    }

    //Transform interface components
    
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
