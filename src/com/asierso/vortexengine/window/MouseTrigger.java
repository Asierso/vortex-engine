/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.window;

import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

/**
 *
 * @author sobblaas
 */
public class MouseTrigger {

    private IntRect rect;
    private final int yBound = 30;
    private Vector2i lastMcoords;
    private IntRect lastRect;
    private final Window target;
    private float cooldownTime;
    private float cooldownDelta;

    /**
     * Creates a new mouse trigger with no bounds. Default bounds are the entire Window target size
     * @param target Window where create mouse trigger
     */
    public MouseTrigger(Window target) {
        this.rect = new IntRect(0,0,target.getSize().width,target.getSize().height);
        this.target = target;
    }

    /**
     * Creates a new mouse trigger bounds in specified window
     *
     * @param target Window where create mouse trigger
     * @param rect MouseTrigger bounds
     */
    public MouseTrigger(Window target, IntRect rect) {
        this.rect = rect;
        this.target = target;
    }

    /**
     * Creates a new mouse trigger bounds in specified window with a custom
     * cooldown time
     *
     * @param target Window where create mouse trigger
     * @param rect MouseTrigger bounds
     * @param cooldownTime New cooldown value
     */
    public MouseTrigger(Window target, IntRect rect, float cooldownTime) {
        this.rect = rect;
        this.target = target;
        this.cooldownTime = cooldownTime;
    }

    /**
     * Detects if cursor is inside bounds. Isn't affected by cooldown
     *
     * @return True/False value
     */
    public boolean isMouseHover() {
        var dx = Mouse.getPosition().x - target.getRender().getPosition().x;
        var dy = Mouse.getPosition().y - target.getRender().getPosition().y - yBound;
        return (rect.contains(dx, dy));
    }

    /**
     * Detects if mouse is clicked inside bounds
     *
     * @param button Button to be checked
     * @return True/False value
     */
    public boolean isMouseClick(Mouse.Button button) {
        var dx = Mouse.getPosition().x - target.getRender().getPosition().x;
        var dy = Mouse.getPosition().y - target.getRender().getPosition().y - yBound;
        if (isMouseHover() && Mouse.isButtonPressed(button) && cooldownDelta <= 0) {
            lastMcoords = new Vector2i(dx, dy);
            lastRect = new IntRect(rect.left, rect.width, rect.top, rect.height);
            cooldownDelta = cooldownTime;
            return true;
        } else {
            if (!Mouse.isButtonPressed(button) || (lastMcoords != null && lastRect != null && (lastMcoords.x != dx
                    || lastMcoords.y != dy
                    || lastRect.left != rect.left
                    || lastRect.top != rect.top
                    || lastRect.width != rect.width
                    || lastRect.height != rect.height))) {
                if (cooldownDelta >= 0) {
                    cooldownDelta -= .1f;
                }
            }
            return false;
        }
    }

    /**
     * Detects if mouse is clicked inside bounds without using cooldown
     *
     * @param button Button to be checked
     * @return True/False value
     */
    public boolean isMouseDown(Mouse.Button button) {
        return isMouseHover() && Mouse.isButtonPressed(button);
    }

    /**
     * Get cooldown value. Cooldown is the constant wait time for any mouse
     * interaction with the mouse trigger
     *
     * @return Cooldown value
     */
    public float getCooldownTime() {
        return cooldownTime;
    }

    /**
     * Set cooldown value. Cooldown is the constant wait time for any mouse
     * interaction with the mouse trigger Default value is 0 (no cooldown)
     *
     * @param cooldownTime New cooldown value
     */
    public void setCooldownTime(float cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    /**
     * Get trigger bounds area
     * @return Bounds rectangle 
     */
    public IntRect getRectArea() {
        return rect;
    }

    /**
     * Set trigger bounds area
     * @param rect Bounds rectangle 
     */
    public void setRectArea(IntRect rect) {
        this.rect = rect;
    }


}
