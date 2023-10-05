package com.asierso.vortexengine.components.animator;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.miscellaneous.Startable;
import com.asierso.vortexengine.sceneObjects.GameObject;
import java.util.ArrayList;
import org.jsfml.system.Clock;

/**
 *
 * @author Asierso
 */
public class Animator implements Component, Startable {

    private final ArrayList<KeyFrame> keyFrames = new ArrayList<>();
    private ArrayList<KeyFrame> keyFramesQueue = new ArrayList<>();

    private boolean isActive = false;
    private float delta = 0;
    private final Clock clock = new Clock();

    public enum BlendMode {
        ADDITIVE, STATIC, MULTIPLY, ADDITIVE_INTERPOLATE, MULTIPLY_INTERPOLATE
    }

    public final void addKeyFrame(KeyFrame key) {
        keyFrames.add(key);
    }

    public final int addVoidKeyFrame() {
        keyFrames.add(new KeyFrame());
        return keyFrames.size() - 1;
    }

    public final KeyFrame getKeyFrame(int id) {
        return keyFrames.get(id);
    }

    public final void removeKeyFrame(int id) {
        keyFrames.remove(id);
    }

    public final int getKeyFrameAmount() {
        return keyFrames.size();
    }

    @Override
    public final void run(GameObject target) {

        if (isActive) {
            //Calculate delta
            delta += clock.restart().asSeconds();
            float roundDelta;

            //Check if queue is empty and stop animator if it is
            if (keyFramesQueue.isEmpty()) {
                stop();
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
                //System.out.println("D: " + roundDelta + "F: " + frame.getTime());
                //Execute frame if is time
                if (roundDelta == frame.getTime()) {
                    //Run frame representation by his blend mode
                    switch (frame.getFrameBlend()) {
                        case STATIC -> staticFrameRepresentation(target, frame);
                        case ADDITIVE -> additiveFrameRepresentation(target, frame);
                        case MULTIPLY -> multiplyFrameRepresentation(target, frame);
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
        }
    }

    protected void staticFrameRepresentation(GameObject target, KeyFrame frame) {
        target.setPosition(frame.getPosition());
        target.setBoxSize(frame.getBoxSize());
        target.setRotation(frame.getRotation());
    }

    protected void additiveFrameRepresentation(GameObject target, KeyFrame frame) {
        target.setPosition(target.getPosition().x + frame.getPosition().x, target.getPosition().y + frame.getPosition().y);
        target.setBoxSize(target.getBoxSize().x + frame.getBoxSize().x, target.getBoxSize().y + frame.getBoxSize().y);
        target.setRotation(target.getRotation() + frame.getRotation());
    }

    protected void multiplyFrameRepresentation(GameObject target, KeyFrame frame) {
        target.setPosition(target.getPosition().x * frame.getPosition().x, target.getPosition().y * frame.getPosition().y);
        target.setBoxSize(target.getBoxSize().x * frame.getBoxSize().x, target.getBoxSize().y * frame.getBoxSize().y);
        target.setRotation(target.getRotation() * frame.getRotation());
    }

    @Override
    public void start() {
        keyFramesQueue = keyFrames;
        isActive = true;
    }

    @Override
    public void stop() {
        isActive = false;
        delta = 0;
    }
}
