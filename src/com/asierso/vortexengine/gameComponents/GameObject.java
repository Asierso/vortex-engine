/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.gameComponents;

import com.asierso.vortexengine.window.Window;
import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 *
 * @author sobblaas
 */
public class GameObject {

    private Vector2f position = new Vector2f(0, 0);
    private Vector2f boxSize = new Vector2f(0, 0);
    private ArrayList<IComponent> components = new ArrayList<IComponent>();
    private boolean isShowing = true;

    public GameObject() {

    }

    public GameObject(Vector2f position) {
        this.position = position;
    }

    public GameObject(Vector2f position, Vector2f boxSize) {
        this.position = position;
        this.boxSize = boxSize;
    }

    public final void setPosition(float x, float y) {
        position = new Vector2f(x, y);
    }
    
    public final void setPosition(Vector2f position) {
        this.position = position;
    }

    public final Vector2f getPosition() {
        return position;
    }

    public final void setBoxSize(float x, float y) {
        boxSize = new Vector2f(x, y);
    }

    public final Vector2f getBoxSize() {
        return boxSize;
    }

    public final void setVisible(boolean showing) {
        isShowing = showing;
    }

    public final boolean getVisible() {
        return isShowing;
    }

    public final void instantiate(Window window) {
        for (IComponent handle : components) {
            handle.run(this);
        }
        if (isShowing) {
            render(window.getRender());
        }

    }

    public final void addComponent(IComponent component) {
        components.add(component);
    }
    
    public final <T extends IComponent> T getComponentById(int id){
        return (T)components.get(id);
    }
    
    public final void removeComponent(IComponent component) {
        components.remove(component);
    }

    public final boolean collisionWith(GameObject obj) {
        boolean col = false;
        for (float i = position.x; i < position.x + boxSize.x; i++) {
            for (float j = position.y; j < position.y + boxSize.y; j++) {
                if (i >= obj.position.x && i <= obj.position.x + obj.boxSize.x) {
                    if (j >= obj.position.y && j <= obj.position.y + obj.boxSize.y) {
                        col = true;
                    }
                }
            }
        }
        return col;
    }

    public void render(RenderWindow render) {

    }
}
