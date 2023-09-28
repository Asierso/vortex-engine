/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.gameComponents;

import com.asierso.vortexengine.SampleCube;
import java.util.ArrayList;

/**
 *
 * @author sobblaas
 */
public class Rigibody implements IComponent {

    private float acceleration = 1.1f;
    private float delta = 0f;
    private float mass = 1f;
    private ArrayList<GameObject> collisionalObjects = new ArrayList<GameObject>();

    public enum RigibodyStates {
        STATIC, DYNAMIC, KINEMATIC
    };
    private RigibodyStates bodyState = RigibodyStates.DYNAMIC;

    public void run(GameObject target) {

        boolean isTouchingGround = false;
        if (bodyState == RigibodyStates.DYNAMIC) {
            for (var handle : collisionalObjects) {
                if (acceleration > 0 && (int) (target.getPosition().y + target.getBoxSize().y) <= (int) handle.getPosition().y +2 && (int) (target.getPosition().y + target.getBoxSize().y) >= (int) handle.getPosition().y -2) {
                    for (float i = target.getPosition().x; i < target.getPosition().x + target.getBoxSize().x; i++) {
                        if (i >= handle.getPosition().x && i <= handle.getPosition().x + handle.getBoxSize().x) {
                            isTouchingGround = true;
                        }
                    }
                }
                else if (acceleration < 0 && (int) target.getPosition().y-2 <= (int) (handle.getPosition().y + handle.getBoxSize().y) && (int) (handle.getPosition().y + handle.getBoxSize().y)  <= (int) target.getPosition().y +2) {
                    for (float i = target.getPosition().x; i < target.getPosition().x + target.getBoxSize().x; i++) {
                        if (i >= handle.getPosition().x && i <= handle.getPosition().x + handle.getBoxSize().x) {
                            isTouchingGround = true;
                        }
                    }
                }
            }
        }
        if (isTouchingGround || bodyState == RigibodyStates.STATIC) {
            delta = 0;
        } else if (bodyState == RigibodyStates.DYNAMIC || bodyState == RigibodyStates.KINEMATIC) {
            target.setPosition(target.getPosition().x, target.getPosition().y + (delta * acceleration) * mass);
            delta += .1f;
        }
    }

    public final void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public final float getAcceleration() {
        return acceleration;
    }

    public final void setMass(float mass) {
        this.mass = mass;
    }

    public final float getMass() {
        return mass;
    }

    public final ArrayList<GameObject> getCollisionalObjectList() {
        return collisionalObjects;
    }
    
    public final void flushDelta(){
        delta = 0;
    }
    
    public final float getDelta(){
        return delta;
    }

    public final void setBodyState(RigibodyStates bodyState) {
        this.bodyState = bodyState;
    }
}
