package com.asierso.vortexengine.components.animator;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.miscellaneous.interfaces.Startable;
import com.asierso.vortexengine.objects.GameObject;
import java.util.ArrayList;
import org.jsfml.system.Clock;

/**
 * Component Animator allows to create animations and execute it in GameObject
 *
 * @author Asierso
 */
public class Animator implements Component, Startable {

    //List of total keyframes and keyframes waiting to render
    private final ArrayList<KeyFrame> keyFrames = new ArrayList<>();
    private ArrayList<KeyFrame> keyFramesQueue = new ArrayList<>();

    //Animation control buffers
    private boolean isActive = false;
    private float delta = 0;
    private float maxDelta = -1;
    private boolean isLoop = false;

    //Second timer
    private final Clock clock = new Clock();

    //Keyframes blending
    public enum BlendMode {
        ADDITIVE, STATIC, MULTIPLY, ADDITIVE_INTERPOLATE, MULTIPLY_INTERPOLATE
    }

    /**
     * Add a new keyframe to keyframes list
     *
     * @param key Keyframe to add
     */
    public final void addKeyFrame(KeyFrame key) {
        keyFrames.add(key);
    }

    /**
     * Creates a void keyframe and add it to keyframes list
     *
     * @return Keyframe list position
     */
    public final int addVoidKeyFrame() {
        keyFrames.add(new KeyFrame());
        return keyFrames.size() - 1;
    }

    /**
     * Get specific keyframe using his id
     *
     * @param id Keyframe index in list
     * @return Keyframe
     */
    public final KeyFrame getKeyFrame(int id) {
        return keyFrames.get(id);
    }

    /**
     * Remove keyframe from keyframe list using his id
     *
     * @param id Keyframe index in list
     */
    public final void removeKeyFrame(int id) {
        keyFrames.remove(id);
    }

    /**
     * Get amount of keyframe list
     *
     * @return Number of Keyframes
     */
    public final int getKeyFrameAmount() {
        return keyFrames.size();
    }

    /**
     * Set animation end time. Set -1 if animation never stops (his default value)
     * @param maxDelta Max animation treshold time
     */
    public final void setEndTime(float maxDelta) {
        this.maxDelta = maxDelta;
    }

    /**
     * Get animation end time
     * @return Max animation treshold time
     */
    public final float getEndTime() {
        return maxDelta;
    }
    
    public final boolean isLoop(){
        return isLoop;
    }
    
    public final void setLoop(boolean isLoop){
        this.isLoop = isLoop;
    }

    /**
     * Execute animation in component target
     *
     * @param target GameObject target
     */
    @Override
    public final void run(GameObject target) {

        if (isActive) {
            //Calculate delta
            delta += clock.restart().asSeconds();
            float roundDelta = 0;

            //Check if queue is empty and stop animator if it is
            if (keyFramesQueue.isEmpty() && !isLoop && maxDelta == -1) {
                stop();
                return;
            }
            else if(keyFramesQueue.isEmpty() && maxDelta == -1){ //Loop ends (restart animation)
                stop();
                start();
                return;
            }

            //Check keyframes
            for (KeyFrame frame : keyFramesQueue.stream().filter(obj -> obj.getTime() > delta).toList()) {
                //Frame time rounding
                if (frame.getTime() % 1 == 0.0f) {
                    roundDelta = Math.round(delta);
                } else {
                    roundDelta = Math.round(delta * 10.0f) / 10.0f;
                }
                
                //Execute frame if is time
                if (roundDelta == frame.getTime()) {
                    //Run frame representation by his blend mode
                    switch (frame.getFrameBlend()) {
                        case STATIC ->
                            staticFrameRepresentation(target, frame);
                        case ADDITIVE ->
                            additiveFrameRepresentation(target, frame);
                        case MULTIPLY ->
                            multiplyFrameRepresentation(target, frame);
                    }
                    //Delete animation
                    keyFramesQueue.remove(frame);
                } else {
                    //Detect with type of interpolation uses the frame (only one)
                    switch (frame.getFrameBlend()) {
                        case ADDITIVE_INTERPOLATE -> {
                            additiveFrameRepresentation(target, frame);
                            return;
                        }
                        case MULTIPLY_INTERPOLATE -> {
                            multiplyFrameRepresentation(target, frame);
                            return;
                        }
                    }
                }
            }
            
            //Check if frameTime surpases max threshold and stop it
            if (maxDelta != -1 && roundDelta >= maxDelta && !isLoop) {
                keyFramesQueue = null;
                stop();
            }
            else if (maxDelta != -1 && roundDelta >= maxDelta){ //Loop ends at treshold value (restart animation)
                keyFramesQueue = null;
                stop();
                start();
            }
        }
    }

    /**
     * Render Keyframe values setting it in GameObject transform interface
     *
     * @param target GameObject target
     * @param frame Current rendering frame
     */
    protected void staticFrameRepresentation(GameObject target, KeyFrame frame) {
        target.setPosition(frame.getPosition());
        target.setBoxSize(frame.getBoxSize());
        target.setRotation(frame.getRotation());
    }

    /**
     * Render Keyframe values adding it to the current gameObject transform
     * interface
     *
     * @param target GameObject target
     * @param frame Current rendering frame
     */
    protected void additiveFrameRepresentation(GameObject target, KeyFrame frame) {
        target.setPosition(target.getPosition().x + frame.getPosition().x, target.getPosition().y + frame.getPosition().y);
        target.setBoxSize(target.getBoxSize().x + frame.getBoxSize().x, target.getBoxSize().y + frame.getBoxSize().y);
        target.setRotation(target.getRotation() + frame.getRotation());
    }

    /**
     * Render Keyframe values multiplying it to the current gameObject transform
     * interface
     *
     * @param target
     * @param frame
     */
    protected void multiplyFrameRepresentation(GameObject target, KeyFrame frame) {
        target.setPosition(target.getPosition().x * frame.getPosition().x, target.getPosition().y * frame.getPosition().y);
        target.setBoxSize(target.getBoxSize().x * frame.getBoxSize().x, target.getBoxSize().y * frame.getBoxSize().y);
        target.setRotation(target.getRotation() * frame.getRotation());
    }

    /**
     * Start animation
     */
    @Override
    public void start() {
        keyFramesQueue = keyFrames;
        isActive = true;
    }

    /**
     * Stop animation
     */
    @Override
    public void stop() {
        isActive = false;
        delta = 0;
    }

    /**
     * Check if animation is actually running
     *
     * @return Animation running
     */
    public boolean isRunning() {
        return isActive;
    }
}
