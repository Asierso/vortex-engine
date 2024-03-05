package com.asierso.vortexengine.objects;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.miscellaneous.interfaces.Transform;
import com.asierso.vortexengine.window.Window;
import java.util.ArrayList;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

/**
 * The base void GameObject with the basic properties needed in every object in
 * the scene
 *
 * @author Asierso
 */
public abstract class GameObject implements Cloneable, Transform {

    //Transform fields
    private Vector2f position = new Vector2f(0, 0);
    private Vector2f boxSize = new Vector2f(0, 0);
    private float rotation = 0;

    //Components list
    private final ArrayList<Component> components = new ArrayList<>();

    //Others
    private Color baseColor = Color.WHITE;
    private boolean isShowing = true;

    /**
     * Initialize GameObject
     */
    public GameObject() {

    }

    /**
     * Initialize GameObject
     *
     * @param position The position of the object
     */
    public GameObject(Vector2f position) {
        this.position = position;
    }

    /**
     * Initialize GameObject
     *
     * @param position The position of the object
     * @param boxSize The box size of the object
     */
    public GameObject(Vector2f position, Vector2f boxSize) {
        this.position = position;
        this.boxSize = boxSize;
    }
    
    @Override
    public void setPosition(float x, float y) {
        position = new Vector2f(x, y);
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    @Override
    public Vector2f getPosition() {
        return position;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void setBoxSize(Vector2f size) {
        boxSize = size;
    }

    @Override
    public void setBoxSize(float x, float y) {
        boxSize = new Vector2f(x, y);
    }
    
    @Override
    public Vector2f getBoxSize() {
        return boxSize;
    }

    /**
     * Set GameObject visibility A component without visibility can de
     * instantiated to render components
     *
     * @param showing Showing status
     */
    public void setVisible(boolean showing) {
        isShowing = showing;
    }

    /**
     * Get GameObject current visibility
     *
     * @return If GameObject is visible or not
     */
    
    public boolean getVisible() {
        return isShowing;
    }

    /**
     * Set GameObject color
     *
     * @param color The color to assign to GameObject
     */
    
    public void setColor(Color color) {
        baseColor = color;
    }

    /**
     * Get GameObject color
     *
     * @return A color class
     */
    
    public Color getColor() {
        return baseColor;
    }

    /**
     * Get Transform interface of the GameObject
     *
     * @return The transform interface of the object
     */
    
    public final Transform getTransform() {
        return this;
    }

    /**
     * Instantiate the GameObject in selected window. Remember that is not
     * needed to instantiate non-graphic GameObjects All GameObjects with any
     * component type, must be instantiated
     *
     * @param window The window where render the GameObject
     */
    
    public final void instantiate(Window window) {
        //Render components before render object
        for (Component handle : components) {
            handle.run(this);
        }
        //Render object
        if (isShowing) {
            render(window);
        }
    }

    /**
     * Add a new component to components ArrayList
     *
     * @param component The component class to add
     */
    
    public final void addComponent(Component component) {
        if (!existsComponent(component)) {
            components.add(component);
        } else {
            throw new StackOverflowError();
        }
    }

    /**
     * Find components by id. ID represents the element index in components
     * array
     *
     * @param <T> Component class
     * @param id ID of component to get
     * @return Component class found
     */
    @SuppressWarnings({"unchecked"})
    public final <T extends Component> T getComponent(int id) {
        return (T) components.get(id);
    }

    /**
     * Find components by class name. (Only one component at the same class can
     * be handled)
     *
     * @param <T> Component class
     * @param type Component class name
     * @return Component class found
     */
    @SuppressWarnings({"unchecked", "OptionalGetWithoutIsPresent"})
    public final <T extends Component> T getComponent(Class type) {
        return (T) components.stream()
                .filter(obj -> obj.getClass() == type)
                .findFirst().get();
    }

    /**
     * Detects if component class exists based in component class name
     *
     * @param component Component to search
     * @return If component was found or not
     */
    
    private boolean existsComponent(Component component) {
        return components.stream()
                .anyMatch(obj -> obj.getClass() == component.getClass());
    }

    /**
     * Detects if component class exists based in component class name
     *
     * @param name Component class name
     * @return If component was found or not
     */
    
    private boolean existsComponent(Class type) {
        return components.stream()
                .anyMatch(obj -> obj.getClass() == type);
    }

    /**
     * Removes the specified component in GameObject component ArrayList
     *
     * @param type class name of the component to delete
     */

    @SuppressWarnings({"OptionalGetWithoutIsPresent","unused"})
    public final void removeComponent(Class type) {
        if (existsComponent(type)) {
            components.remove(components.stream()
                    .filter(obj -> obj.getClass()==type)
                    .findFirst().get());
        } else {
            throw new NoClassDefFoundError();
        }
    }

    /**
     * Detects if current game object collides with a target gameObject.
     * Consider that it works like a trigger collision (inner collisions are
     * detected too)
     *
     * @param obj Target GameObject to detect
     * @return If collision is produced
     */
    
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

    /**
     * Clone current GameObject class to another new
     *
     * @return The new instance of GameObject
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * The method that render the GameObject "Shape"
     *
     * @param win Window where render the GameObject "Shape"
     */
    protected abstract void render(Window win);
}
