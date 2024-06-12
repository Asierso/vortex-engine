package com.asierso.vortexengine.tests;

import com.asierso.vortexengine.testobjects.SampleCube;
import com.asierso.vortexengine.testobjects.TestWindow;
import com.asierso.vortexengine.testobjects.physics.Centrifuge;
import com.asierso.vortexengine.testobjects.physics.Rigibody;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.window.event.Event;
import org.junit.Assert;
import org.junit.Test;

public class TestSolids {
    @Test
    public void slowMass(){
        TestWindow t = new TestWindow("Slow mass test",new Scene() {
            private SampleCube sc;
            private SampleCube sc2;
            private SampleCube sc3;
            @Override
            public void start() {
                sc = new SampleCube();
                sc.setBoxSize(50,15);
                sc.setPosition(25,25);
                sc.addComponent(new Rigibody());
                sc.setColor(Color.RED);
                sc.<Rigibody>getComponent(Rigibody.class).setMass(1);

                sc2 = new SampleCube();
                sc2.setBoxSize(50,15);
                sc2.setPosition(125,25);
                sc2.addComponent(new Rigibody());
                sc2.setColor(Color.GREEN);
                sc2.<Rigibody>getComponent(Rigibody.class).setMass(2);

                sc3 = new SampleCube();
                sc3.setBoxSize(50,15);
                sc3.setPosition(225,25);
                sc3.addComponent(new Rigibody());
                sc3.setColor(Color.BLUE);
                sc3.<Rigibody>getComponent(Rigibody.class).setMass(3);
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                sc.instantiate(window);
                sc2.instantiate(window);
                sc3.instantiate(window);
                if(sc.getPosition().y > 300){
                    sc.setPosition(25,25);
                    sc.<Rigibody>getComponent(Rigibody.class).flushDelta();
                }
                if(sc2.getPosition().y > 300){
                    sc2.setPosition(125,25);
                    sc2.<Rigibody>getComponent(Rigibody.class).flushDelta();
                }
                if(sc3.getPosition().y > 300){
                    sc3.setPosition(225,25);
                    sc3.<Rigibody>getComponent(Rigibody.class).flushDelta();
                }
            }

            @Override
            public void close() {

            }
        },5000);

        t.run();
        Assert.assertTrue(true);
    }

    @Test
    public void highMass(){
        TestWindow t = new TestWindow("High mass test",new Scene() {
            private SampleCube sc;
            private SampleCube sc2;
            private SampleCube sc3;
            @Override
            public void start() {
                sc = new SampleCube();
                sc.setBoxSize(50,15);
                sc.setPosition(25,25);
                sc.addComponent(new Rigibody());
                sc.setColor(Color.RED);
                sc.<Rigibody>getComponent(Rigibody.class).setMass(10);

                sc2 = new SampleCube();
                sc2.setBoxSize(50,15);
                sc2.setPosition(125,25);
                sc2.addComponent(new Rigibody());
                sc2.setColor(Color.GREEN);
                sc2.<Rigibody>getComponent(Rigibody.class).setMass(20);

                sc3 = new SampleCube();
                sc3.setBoxSize(50,15);
                sc3.setPosition(225,25);
                sc3.addComponent(new Rigibody());
                sc3.setColor(Color.BLUE);
                sc3.<Rigibody>getComponent(Rigibody.class).setMass(30);
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                sc.instantiate(window);
                sc2.instantiate(window);
                sc3.instantiate(window);
                if(sc.getPosition().y > 300){
                    sc.setPosition(25,25);
                    sc.<Rigibody>getComponent(Rigibody.class).flushDelta();
                }
                if(sc2.getPosition().y > 300){
                    sc2.setPosition(125,25);
                    sc2.<Rigibody>getComponent(Rigibody.class).flushDelta();
                }
                if(sc3.getPosition().y > 300){
                    sc3.setPosition(225,25);
                    sc3.<Rigibody>getComponent(Rigibody.class).flushDelta();
                }
            }

            @Override
            public void close() {

            }
        },5000);

        t.run();
        Assert.assertTrue(true);
    }

    @Test
    public void softCollisions(){
        TestWindow t = new TestWindow("Soft collision test",new Scene() {
            private SampleCube sc;
            private SampleCube floor;
            @Override
            public void start() {
                sc = new SampleCube();
                sc.setBoxSize(50,50);
                sc.setPosition(25,25);
                sc.addComponent(new Rigibody());
                sc.setColor(Color.RED);
                sc.<Rigibody>getComponent(Rigibody.class).setMass(5);
                sc.<Rigibody>getComponent(Rigibody.class).setWeighing(Rigibody.GravityWeighing.SOFT_PRECISION);


                floor = new SampleCube();
                floor.setBoxSize(150,25);
                floor.setPosition(25,225);
                floor.addComponent(new Rigibody());
                floor.<Rigibody>getComponent(Rigibody.class).setBodyState(Rigibody.RigibodyStates.KINEMATIC);
                floor.<Rigibody>getComponent(Rigibody.class).setMass(10);
                floor.<Rigibody>getComponent(Rigibody.class).setAcceleration(0);
                sc.<Rigibody>getComponent(Rigibody.class).getCollisionalObjectList().add(floor);
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                sc.instantiate(window);
                floor.instantiate(window);

                if(sc.getPosition().y > floor.getPosition().y + 50)
                    Assert.fail();
            }

            @Override
            public void close() {
            }
        },5000);

        t.run();
        Assert.assertTrue(true);
    }

    @Test
    public void hardCollisions(){
        TestWindow t = new TestWindow("Hard collision test",new Scene() {
            private SampleCube sc;
            private SampleCube floor;
            @Override
            public void start() {
                sc = new SampleCube();
                sc.setBoxSize(50,50);
                sc.setPosition(25,25);
                sc.addComponent(new Rigibody());
                sc.setColor(Color.BLUE);
                sc.<Rigibody>getComponent(Rigibody.class).setMass(20);
                sc.<Rigibody>getComponent(Rigibody.class).setWeighing(Rigibody.GravityWeighing.MASS_PRECISION);


                floor = new SampleCube();
                floor.setBoxSize(150,25);
                floor.setPosition(25,225);
                floor.addComponent(new Rigibody());
                floor.<Rigibody>getComponent(Rigibody.class).setBodyState(Rigibody.RigibodyStates.KINEMATIC);
                floor.<Rigibody>getComponent(Rigibody.class).setMass(10);
                floor.<Rigibody>getComponent(Rigibody.class).setAcceleration(0);
                sc.<Rigibody>getComponent(Rigibody.class).getCollisionalObjectList().add(floor);
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                sc.instantiate(window);
                floor.instantiate(window);

                if(sc.getPosition().y > floor.getPosition().y + 50)
                    Assert.fail();
            }

            @Override
            public void close() {
            }
        },5000);

        t.run();
        Assert.assertTrue(true);
    }

    @Test
    public void softCentrifuge(){
        TestWindow t = new TestWindow("Soft centrifuge test",new Scene() {
            private SampleCube sc;
            @Override
            public void start() {
                try {
                    sc = new SampleCube();
                    sc.setBoxSize(50, 50);
                    sc.setPosition(100, 100);
                    sc.addComponent(new Centrifuge());
                    sc.setColor(Color.RED);
                    sc.<Centrifuge>getComponent(Centrifuge.class).setMass(3);
                    sc.<Centrifuge>getComponent(Centrifuge.class).addForce(50);
                }catch(Exception e){
                    Assert.fail();
                }
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                sc.instantiate(window);
            }

            @Override
            public void close() {
            }
        },5000);

        t.run();
        Assert.assertTrue(true);
    }

    @Test
    public void hardCentrifuge(){
        TestWindow t = new TestWindow("Hard centrifuge test",new Scene() {
            private SampleCube sc;
            @Override
            public void start() {
                try {
                    sc = new SampleCube();
                    sc.setBoxSize(50, 50);
                    sc.setPosition(100, 100);
                    sc.addComponent(new Centrifuge());
                    sc.setColor(Color.BLUE);
                    sc.<Centrifuge>getComponent(Centrifuge.class).setMass(30);
                    sc.<Centrifuge>getComponent(Centrifuge.class).addForce(50);
                }catch(Exception e){
                    Assert.fail();
                }
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                sc.instantiate(window);
            }

            @Override
            public void close() {
            }
        },5000);

        t.run();
        Assert.assertTrue(true);
    }
}
