package com.asierso.vortexengine.tests;

import com.asierso.vortexengine.components.events.Action;
import com.asierso.vortexengine.components.events.callback.EventPredicate;
import com.asierso.vortexengine.miscellaneous.TransformBuilder;
import com.asierso.vortexengine.objects.GameObject;
import com.asierso.vortexengine.objects.ui.UIGameObject;
import com.asierso.vortexengine.testobjects.TestWindow;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.window.event.Event;
import org.junit.Assert;
import org.junit.Test;

public class TestUI {
    @Test
    public void testUIObject(){
        TestWindow t = new TestWindow("UI test",new Scene() {
            private UIGameObject uig;
            @Override
            public void start() {
                uig = new UIGameObject() {
                    @Override
                    protected void uiRender(Window context) {
                        RectangleShape shape = new RectangleShape();
                        shape.setSize(getBoxSize());
                        shape.setRotation(getRotation());
                        shape.setPosition(getPosition());
                        if(getMouseTrigger().isMouseHover()) {
                            shape.setFillColor(getColor());
                        }
                        context.getRender().draw(shape);
                    }
                };
                uig.setTransform(new TransformBuilder().inPosition(20,20).withSize(20,20).getTransform());
                uig.setColor(Color.RED);
            }

            @Override
            public void update(Window context, Iterable<Event> events) {
                uig.instantiate(context);
            }

            @Override
            public void close() {

            }
        },20000);

        t.run();
        Assert.assertTrue(true);

    }
}
