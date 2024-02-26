/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.ui;

import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.IntRect;
import org.jsfml.window.Mouse;

/**
 *
 * @author sobblaas
 */
public class MouseTrigger {
    private IntRect rect;
    private int yBound = 30;
    private Window target;
    public MouseTrigger(Window target,IntRect rect){
        this.rect = rect;
        this.target = target;
    }
    
    public boolean isMouseHover(){
        var dx = Mouse.getPosition().x - target.getRender().getPosition().x;
        var dy = Mouse.getPosition().y - target.getRender().getPosition().y-yBound;
        return (rect.contains(dx, dy));
    }
    
    public boolean isMouseClick(Mouse.Button button){
        return isMouseHover() && Mouse.isButtonPressed(button);
    }
}
