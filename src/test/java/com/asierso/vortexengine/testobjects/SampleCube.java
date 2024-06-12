package com.asierso.vortexengine.testobjects;

import com.asierso.vortexengine.objects.GameObject;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.RectangleShape;

public class SampleCube extends GameObject {

    @Override
    protected void render(Window win) {
        RectangleShape shape = new RectangleShape();
        shape.setSize(getBoxSize());
        shape.setRotation(getRotation());
        shape.setPosition(getPosition());
        shape.setFillColor(getColor());
        win.getRender().draw(shape);
    }
}
