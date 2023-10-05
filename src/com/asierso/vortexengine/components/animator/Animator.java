/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.components.animator;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.miscellaneous.Startable;
import com.asierso.vortexengine.miscellaneous.Transform;
import com.asierso.vortexengine.sceneObjects.GameObject;
import java.util.ArrayList;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

/**
 *
 * @author sobblaas
 */
public class Animator implements Component, Startable {

    private final ArrayList<KeyFrame> keyFrames = new ArrayList<>();
    private boolean isActive = false;
    private float delta = 0;

    public enum BlendMode {
        ADDITIVE, STATIC, MULTIPLY
    };

    public final void addKeyFrame(KeyFrame key) {
        keyFrames.add(key);
    }

    public final int createVoidKeyFrame() {
        keyFrames.add(new KeyFrame());
        return keyFrames.size() - 1;
    }

    public final KeyFrame getKeyFrame(int id) {
        return keyFrames.get(id);
    }

    public final void removeKeyFrame(int id) {
        keyFrames.remove(id);
    }

    @Override
    public void run(GameObject target) {
        if (isActive) {
            Clock clock = new Clock();
            for (KeyFrame frame : keyFrames) {
                switch (frame.getFrameBlend()) {
                    case STATIC -> {
                        if (delta == frame.getTime()) {
                            target.setPosition(frame.getPosition());
                            target.setBoxSize(frame.getBoxSize());
                            target.setRotation(frame.getRotation());
                        }
                    }
                }
            }
            delta += clock.restart().asSeconds();
        }
    }

    @Override
    public void start() {
        isActive = true;
    }

    @Override
    public void stop() {
        isActive = false;
    }
}
