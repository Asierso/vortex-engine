/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.components.events.Action;
import com.asierso.vortexengine.components.events.callback.ActionPerform;
import com.asierso.vortexengine.components.events.EventListener;
import com.asierso.vortexengine.components.events.callback.EventPredicate;
import com.asierso.vortexengine.components.physics.Rigibody;
import com.asierso.vortexengine.objects.GameObject;
import com.asierso.vortexengine.window.Layer;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

/**
 *
 * @author sobblaas
 */
public class SampleLayerScene implements Scene {
    private Layer l = new Layer();
    @Override
    public void start() {
        var scube = new SampleCube();
        scube.setPosition(10,10);
        scube.setBoxSize(20,20);
        scube.setColor(Color.BLUE);
        var liste = new EventListener();
        
        liste.getActionsList().add(new Action(new EventPredicate(){
            @Override
            public boolean run() {
                return (Keyboard.isKeyPressed(Key.E));
            }
        }, new ActionPerform() {
            @Override
            public void run(GameObject handle) {
                System.out.println("AA");
            }
            
        }));
        
        scube.addComponent(liste);
        l.add(scube);
    }

    @Override
    public void update(Window window, Iterable<Event> events) {
        l.instantiate(window);
    }

    @Override
    public void close() {
        
    }
    
}
