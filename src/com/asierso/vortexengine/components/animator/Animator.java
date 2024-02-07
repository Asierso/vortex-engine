package com.asierso.vortexengine.components.animator;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.miscellaneous.interfaces.Startable;
import com.asierso.vortexengine.objects.GameObject;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.jsfml.system.Vector2f;

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
    private int delta = 0;
    private int maxDelta = 0;
    private boolean isLoop = false;

    //Keyframes blending
    public enum BlendMode {
        ADDITIVE, STATIC, MULTIPLY, ADDITIVE_INTERPOLATE, MULTIPLY_INTERPOLATE, DYNAMIC_INTERPOLATE
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
     * Set animation end time. Set a FrameTime with 0 ticks if animation stops
     * in his last keyframe
     *
     * @param time Max animation treshold time
     */
    public final void setEndTime(FrameTime time) {
        this.maxDelta = time.getTicks();
    }

    /**
     * Get setted animation end time
     *
     * @return Max animation treshold time
     */
    public final FrameTime getEndTime() {
        return new FrameTime(maxDelta);
    }

    /**
     * Gets if animator loops keyframes when reach end
     *
     * @return Loop value
     */
    public final boolean isLoop() {
        return isLoop;
    }

    /**
     * Set if animator have to loop frames
     *
     * @param isLoop Loop value
     */
    public final void setLoop(boolean isLoop) {
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
            delta += 1f;

            //Delete garbage frames
            for (KeyFrame frame : keyFramesQueue.stream().filter(obj -> obj.getTime().getTicks() < delta).toList()) {
                keyFramesQueue.remove(frame);
            }

            //Dynamic interpolate conversion
            if (!keyFramesQueue.stream().filter(obj -> obj.getTime().getTicks() >= delta && obj.getFrameBlend() == BlendMode.DYNAMIC_INTERPOLATE).toList().isEmpty()) {
                for (int i = 0; i < keyFramesQueue.size(); i++) {
                    if (keyFramesQueue.get(i).getFrameBlend() == BlendMode.DYNAMIC_INTERPOLATE && i != 0) {
                        keyFramesQueue.set(i, frameInterpolator(keyFramesQueue.get(i), keyFramesQueue.get(i - 1)));
                    }
                }
            }

            //Check keyframes
            for (KeyFrame frame : keyFramesQueue.stream().filter(obj -> obj.getTime().getTicks() >= delta).toList()) {
                loadStopConditions();
                //Execute frame if is time
                if (delta >= frame.getTime().getTicks()) {
                    //Run frame representation by his blend mode
                    switch (frame.getFrameBlend()) {
                        case STATIC ->
                            staticFrameRepresentation(target, frame);
                        case ADDITIVE ->
                            additiveFrameRepresentation(target, frame);
                        case MULTIPLY ->
                            multiplyFrameRepresentation(target, frame);
                    }
                    keyFramesQueue.remove(frame);
                } else if (keyFramesQueue.stream().filter(obj -> obj.getTime().getTicks() > delta).toList().get(0) == frame) {
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
            loadStopConditions();
        }
    }

    /**
     * Detects if current animation is at max time treshold or doesn't have more
     * keyframes to render. Method returns true only if animation has to be
     * looped
     */
    private void loadStopConditions() {
        if (isLoop) { //Looping
            if (maxDelta > 0 && delta >= maxDelta) { //Delta treshold surpased
                delta = 0;
                keyFramesQueue = (ArrayList) keyFrames.clone();
            } else if (maxDelta <= 0 && keyFramesQueue.isEmpty()) { //No more keyframes to render
                delta = 0;
                keyFramesQueue = (ArrayList) keyFrames.clone();
            }
        } else { //Not looping
            if (maxDelta > 0 && delta >= maxDelta) { //Delta treshold surpased
                keyFramesQueue = null;
                stop();
            } else if (maxDelta <= 0 && keyFramesQueue.isEmpty()) { //No more keyframes to render
                stop();
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
     * @param target GameObject target
     * @param frame Current rendering frame
     */
    protected void multiplyFrameRepresentation(GameObject target, KeyFrame frame) {
        target.setPosition(target.getPosition().x * frame.getPosition().x, target.getPosition().y * frame.getPosition().y);
        target.setBoxSize(target.getBoxSize().x * frame.getBoxSize().x, target.getBoxSize().y * frame.getBoxSize().y);
        target.setRotation(target.getRotation() * frame.getRotation());
    }

    /**
     * Calculate dynamic interpolation between two frames. The result is an
     * additive interpolation keyframe
     *
     * @param a Interpolation start frame
     * @param b Interpolation end frame
     * @return Additive interpolation keyframe
     */
    protected KeyFrame frameInterpolator(KeyFrame a, KeyFrame b) {
        KeyFrame handle = new KeyFrame();
        handle.setFrameBlend(BlendMode.ADDITIVE_INTERPOLATE);
        handle.setPosition(new Vector2f(
                (b.getPosition().x - a.getPosition().x) / (b.getTime().getTicks() - a.getTime().getTicks()),
                (b.getPosition().y - a.getPosition().y) / (b.getTime().getTicks() - a.getTime().getTicks())));
        handle.setBoxSize(new Vector2f(
                (b.getBoxSize().x - a.getBoxSize().x) / (b.getTime().getTicks() - a.getTime().getTicks()),
                (b.getBoxSize().y - a.getBoxSize().y) / (b.getTime().getTicks() - a.getTime().getTicks())));
        handle.setRotation(
                (b.getRotation() - a.getRotation()) / (b.getTime().getTicks() - a.getTime().getTicks()));
        return handle;
    }

    /**
     * Start animation
     */
    @Override
    public void start() {
        keyFramesQueue = (ArrayList) keyFrames.clone();
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
