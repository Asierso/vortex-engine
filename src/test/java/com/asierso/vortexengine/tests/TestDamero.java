package com.asierso.vortexengine.tests;

import com.asierso.vortexengine.testobjects.SampleCube;
import com.asierso.vortexengine.testobjects.TestWindow;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.window.event.Event;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestDamero {
    @Test
    public void createDamero(){
        TestWindow t = new TestWindow("Static damero test",new Scene() {
            private ArrayList<SampleCube> list = new ArrayList<>();
            @Override
            public void start() {
                int blk = 0;
                for(int i = 0; i < 400; i+=25){
                    for(int j = 0; j < 300; j+=25){
                        SampleCube sc = new SampleCube();
                        sc.setPosition(i,j);
                        sc.setBoxSize(25,25);
                        sc.setColor(blk%2==0? Color.BLACK : Color.WHITE);
                        list.add(sc);
                        blk++;
                    }
                    blk++;
                }
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                list.forEach(obj -> obj.instantiate(window));
            }

            @Override
            public void close() {

            }
        },2000);

        t.run();
        Assert.assertTrue(true);

    }

    @Test
    public void createRotatingDamero(){
        TestWindow t = new TestWindow("Rotating damero test",new Scene() {
            private ArrayList<SampleCube> list = new ArrayList<>();
            @Override
            public void start() {
                int blk = 0;
                for(int i = 0; i < 400; i+=25){
                    for(int j = 0; j < 300; j+=25){
                        SampleCube sc = new SampleCube();
                        sc.setPosition(i,j);
                        sc.setBoxSize(25,25);
                        sc.setColor(blk%2==0? Color.BLACK : Color.WHITE);
                        list.add(sc);
                        blk++;
                    }
                    blk++;
                }
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                list.forEach(obj -> {
                    obj.setRotation(obj.getRotation()+2);
                    obj.instantiate(window);
                });
            }

            @Override
            public void close() {

            }
        },3000);

        t.run();
        Assert.assertTrue(true);
    }
}
