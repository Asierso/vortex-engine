package com.asierso.vortexengine.objects.ui;

import com.asierso.vortexengine.components.events.Action;
import com.asierso.vortexengine.components.events.EventListener;
import com.asierso.vortexengine.miscellaneous.interfaces.Transform;
import com.asierso.vortexengine.objects.GameObject;
import com.asierso.vortexengine.window.MouseEvents;
import com.asierso.vortexengine.window.MouseTrigger;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import java.text.NumberFormat;

/**
 * The base void GameObject to make UI elements. Contains the basic
 * properties of a UI element
 *
 * @author Asierso
 */
public abstract class UIGameObject extends GameObject implements MouseEvents {
    //UI needs
    private MouseTrigger mouseTrigger;
    private IntRect mouseTrgRect;
    private EventListener evt;

    /**
     * Initialize UIGameObject
     */
    public UIGameObject(){
        super();
        evt = new EventListener();
    }

    /**
     * Initialize UIGameObject
     * @param position Position of the UI Object
     */
    public UIGameObject(Vector2f position){
        super(position);
    }

    /**
     * Initialize UIGameObject
     * @param position Position of the UI Object
     * @param size Size of the UI Object
     */
    public UIGameObject(Vector2f position, Vector2f size){
        super(position,size);
    }

    /**
     * Initialize UIGameObject
     * @param transform Transform of the UI Object
     */
    public UIGameObject(Transform transform){
        super(transform);
    }

    @Override
    public void setPosition(Vector2f position) {
        super.setPosition(position);
        updateTrigger();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateTrigger();
    }

    @Override
    public void setBoxSize(Vector2f size) {
        super.setBoxSize(size);
        updateTrigger();
    }

    @Override
    public void setBoxSize(float x, float y) {
        super.setBoxSize(x, y);
        updateTrigger();
    }

    @Override
    public void setRotation(float rotation) {
        //UIGameObjects can't be rotated because MouseTrigger cannot work with angles
    }

    @Override
    public float getRotation() {
        //UIGameObjects can't be rotated because MouseTrigger cannot work with angles. Rotation angle always be 0
        return 0;
    }

    @Override
    protected void render(Window context) {
        if(getVisible()) { //Render trigger and execute ui-render if is visible. Evt only available if object is visible
            mouseTrigger = new MouseTrigger(context, mouseTrgRect, 1);
            evt.run(this);
            uiRender(context);
        }
    }

    /**
     * The method that render the UI Object "shape". It works like "render" method
     * in normal GameObjects
     *
     * @param context Window where render the GameObject "Shape"
     */
    protected abstract void uiRender(Window context);

    /**
     * Updates MouseTrigger shape with the base UI Object transform properties
     */
    private void updateTrigger(){
        NumberFormat nf = NumberFormat.getIntegerInstance();
        int dx = Integer.parseInt(nf.format(this.getPosition().x));
        int dy = Integer.parseInt(nf.format(this.getPosition().y));
        int dw = Integer.parseInt(nf.format(this.getPosition().x + this.getBoxSize().x));
        int dh = Integer.parseInt(nf.format(this.getPosition().y + this.getBoxSize().y));
        mouseTrgRect = new IntRect(dx,dy,dw,dh);
    }

    /**
     * Returns the event listener of the object. This is instantiated
     * at the creation of the UI Object
     *
     * @return UI Object event listener
     */
    public EventListener getEventListener(){
        return evt;
    }

    /**
     * Add action to the object event listener. Actions are executed once
     * every object update (only if is instantiated in the scene and visible)
     *
     * @param e Action to add
     */
    public void addAction(Action e){
        evt.getActionsList().add(e);
    }

    /**
     * Return true or false if the mouse is in the UI Object area and makes click
     *
     * @param button Mouse pressed button
     * @return Mouse click
     */
    @Override
    public boolean isMouseClick(Mouse.Button button) {
        return mouseTrigger.isMouseClick(button);
    }

    /**
     * Return true or false if the mouse is inside the UI Object area
     *
     * @return Mouse inside area condition status
     */
    @Override
    public boolean isMouseHover() {
        return mouseTrigger.isMouseHover();
    }

    /**
     * Return true or false if the mouse is into the area and is pressed down
     *
     * @param button Mouse pressed button
     * @return Mouse button pressed status
     */
    @Override
    public boolean isMouseDown(Mouse.Button button) {
        return mouseTrigger.isMouseDown(button);
    }
}
