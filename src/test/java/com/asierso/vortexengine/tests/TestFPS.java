package com.asierso.vortexengine.tests;

import com.asierso.vortexengine.objects.TextMesh;
import com.asierso.vortexengine.testobjects.TestWindow;
import com.asierso.vortexengine.window.FrameRate;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.window.event.Event;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class TestFPS {
    @Test
    public void reach30FPSProfileLimit(){
        ArrayList<Float> fpsPerFrame = new ArrayList<>();
        TestWindow t = new TestWindow("FPS30 Profile",new Scene() {
            private TextMesh txm;
            @Override
            public void start() {
                txm = new TextMesh();
                txm.setPosition(20,20);
                txm.setFontSize(20);
                txm.setColor(Color.RED);
                try {
                    txm.getFont().loadFromStream(this.getClass().getResourceAsStream("/fonts/sans-mono.ttf"));
                } catch (IOException e) {
                    Assert.fail();
                }
            }

            @Override
            public void update(Window context, Iterable<Event> events) {
                txm.setText(NumberFormat.getIntegerInstance().format(context.getFramesPerSecond()) + " FPS");
                txm.instantiate(context);
                fpsPerFrame.add(context.getFramesPerSecond());
            }

            @Override
            public void close() {

            }
        }, FrameRate.FPS30,5000);

        t.run();

        float med = 0;
        for(double val : fpsPerFrame)
            med += val;

        med = med / fpsPerFrame.size();
        Assert.assertTrue(med >= 25);
    }

    @Test
    public void reach60FPSProfileLimit(){
        ArrayList<Float> fpsPerFrame = new ArrayList<>();
        TestWindow t = new TestWindow("FPS60 Profile",new Scene() {
            private TextMesh txm;
            @Override
            public void start() {
                txm = new TextMesh();
                txm.setPosition(20,20);
                txm.setFontSize(20);
                txm.setColor(Color.RED);
                try {
                    txm.getFont().loadFromStream(this.getClass().getResourceAsStream("/fonts/sans-mono.ttf"));
                } catch (IOException e) {
                    Assert.fail();
                }
            }

            @Override
            public void update(Window context, Iterable<Event> events) {
                txm.setText(NumberFormat.getIntegerInstance().format(context.getFramesPerSecond()) + " FPS");
                txm.instantiate(context);
                fpsPerFrame.add(context.getFramesPerSecond());
            }

            @Override
            public void close() {

            }
        }, FrameRate.FPS60,5000);

        t.run();

        float med = 0;
        for(double val : fpsPerFrame)
            med += val;

        med = med / fpsPerFrame.size();
        Assert.assertTrue(med >= 55);
    }

    @Test
    public void reach120FPSProfileLimit(){
        ArrayList<Float> fpsPerFrame = new ArrayList<>();
        TestWindow t = new TestWindow("FPS120 Profile",new Scene() {
            private TextMesh txm;
            @Override
            public void start() {
                txm = new TextMesh();
                txm.setPosition(20,20);
                txm.setFontSize(20);
                txm.setColor(Color.RED);
                try {
                    txm.getFont().loadFromStream(this.getClass().getResourceAsStream("/fonts/sans-mono.ttf"));
                } catch (IOException e) {
                    Assert.fail();
                }
            }

            @Override
            public void update(Window context, Iterable<Event> events) {
                txm.setText(NumberFormat.getIntegerInstance().format(context.getFramesPerSecond()) + " FPS");
                txm.instantiate(context);
                fpsPerFrame.add(context.getFramesPerSecond());
            }

            @Override
            public void close() {

            }
        }, FrameRate.FPS120,5000);

        t.run();

        float med = 0;
        for(double val : fpsPerFrame)
            med += val;

        med = med / fpsPerFrame.size();
        Assert.assertTrue(med >= 115);
    }
}
