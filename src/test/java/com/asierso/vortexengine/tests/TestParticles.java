package com.asierso.vortexengine.tests;

import com.asierso.vortexengine.miscellaneous.ColorModifier;
import com.asierso.vortexengine.objects.ParticleSystem;
import com.asierso.vortexengine.testobjects.SampleCube;
import com.asierso.vortexengine.testobjects.TestWindow;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestParticles {
    @Test
    public void createParticles() {
        TestWindow t = new TestWindow("Particles test", new Scene() {
            private SampleCube particleModel;
            private ParticleSystem ps;
            private ParticleSystem ps2;

            @Override
            public void start() {
                particleModel = new SampleCube();
                particleModel.setBoxSize(5, 5);

                ps = new ParticleSystem();
                ps.setParticleModel(particleModel);
                ps.setLifetime(1);
                ps.setMaxParticles(10);
                ps.setBoxSize(50, 50);
                ps.setPosition(50, 100);

                ps2 = new ParticleSystem();
                ps2.setParticleModel(particleModel);
                ps2.setLifetime(1);
                ps2.setMaxParticles(100);
                ps2.setBoxSize(50, 50);
                ps2.setPosition(150, 100);
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                ps.instantiate(window);
                ps2.instantiate(window);
            }

            @Override
            public void close() {

            }
        }, 3000);

        t.run();
        Assert.assertTrue(true);

    }

    @Test
    public void createDynamicParticles() {
        TestWindow t = new TestWindow("Dynamic particles test", new Scene() {
            private SampleCube particleModel;
            private ParticleSystem ps;
            private ParticleSystem ps2;
            private ParticleSystem ps3;
            private ParticleSystem ps4;

            private ParticleSystem ps5;
            @Override
            public void start() {
                particleModel = new SampleCube();
                particleModel.setBoxSize(5, 5);

                ps = new ParticleSystem();
                ps.setParticleModel(particleModel);
                ps.setLifetime(1);
                ps.setMaxParticles(10);
                ps.setBoxSize(50, 50);
                ps.setPosition(10, 100);

                ps.setModifier(ParticleSystem.ParticleModifiers.COLOR,new ColorModifier(-1,-5,-5,-5));

                ps2 = new ParticleSystem();
                ps2.setParticleModel(particleModel);
                ps2.setLifetime(1);
                ps2.setMaxParticles(10);
                ps2.setBoxSize(50, 50);
                ps2.setPosition(70, 100);

                ps2.setModifier(ParticleSystem.ParticleModifiers.POSITION,new Vector2f(0,-2));

                ps3 = new ParticleSystem();
                ps3.setParticleModel(particleModel);
                ps3.setLifetime(1);
                ps3.setMaxParticles(5);
                ps3.setBoxSize(50, 50);
                ps3.setPosition(140, 100);

                ps3.setModifier(ParticleSystem.ParticleModifiers.ROTATION,2f);

                ps4 = new ParticleSystem();
                ps4.setParticleModel(particleModel);
                ps4.setLifetime(2);
                ps4.setMaxParticles(5);
                ps4.setBoxSize(2, 2);
                ps4.setPosition(200, 100);

                ps4.setModifier(ParticleSystem.ParticleModifiers.BOX_SIZE,new Vector2f(0.3f,0.3f));

                ps5 = new ParticleSystem();
                ps5.setParticleModel(particleModel);
                ps5.setLifetime(2);
                ps5.setMaxParticles(5);
                ps5.setBoxSize(2, 2);
                ps5.setPosition(260, 100);

                ps5.setModifier(ParticleSystem.ParticleModifiers.POSITION,new Vector2f(0,1));
                ps5.setModifier(ParticleSystem.ParticleModifiers.ROTATION,15f);
                ps5.setModifier(ParticleSystem.ParticleModifiers.BOX_SIZE,new Vector2f(0.2f,0.2f));
                ps5.setModifier(ParticleSystem.ParticleModifiers.COLOR,new ColorModifier(-5,-5,0,-5));
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                ps.instantiate(window);
                ps2.instantiate(window);
                ps3.instantiate(window);
                ps4.instantiate(window);
                ps5.instantiate(window);
            }

            @Override
            public void close() {

            }
        }, 5000);

        t.run();
        Assert.assertTrue(true);

    }

    @Test
    public void createExcedureParticles() {
        TestWindow t = new TestWindow("Excedure particles test", new Scene() {
            private SampleCube particleModel;
            private ParticleSystem ps;

            private ArrayList<ParticleSystem> psList;
            @Override
            public void start() {
                particleModel = new SampleCube();
                particleModel.setBoxSize(5, 5);

                ps = new ParticleSystem();
                ps.setParticleModel(particleModel);
                ps.setLifetime(10);
                ps.setMaxParticles(1000);
                ps.setBoxSize(400,300);
                ps.setPosition(0,0);


                psList = new ArrayList<>();
                for(int i = 0; i< 50; i++){
                    try {
                        psList.add((ParticleSystem) ps.clone());
                    }catch (CloneNotSupportedException e) {
                        Assert.fail();
                    }
                }
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                psList.forEach(o -> o.instantiate(window));
            }

            @Override
            public void close() {

            }
        }, 5000);

        t.run();
        Assert.assertTrue(true);

    }
}
