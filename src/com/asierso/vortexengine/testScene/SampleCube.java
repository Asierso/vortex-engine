package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.sceneObjects.GameObject;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

/**
 *
 * @author Asierso
 */
public class SampleCube extends GameObject {

    @Override
    protected void render(Window win) {
        var rect = new RectangleShape();
        rect.setPosition(this.getPosition());
        rect.setSize(new Vector2f(this.getBoxSize().x, this.getBoxSize().y));
        rect.setRotation(this.getRotation());
        rect.setFillColor(this.getColor());
        win.getRender().draw(rect);
    }
}
