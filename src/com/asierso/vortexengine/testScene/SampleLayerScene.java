/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.components.physics.Rigibody;
import com.asierso.vortexengine.window.Layer;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
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
        var rb = new Rigibody();
        rb.setAcceleration(2);
        scube.addComponent(rb);
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
