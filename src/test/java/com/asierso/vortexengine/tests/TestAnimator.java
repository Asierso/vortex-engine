package com.asierso.vortexengine.tests;

import com.asierso.vortexengine.components.animator.Animator;
import com.asierso.vortexengine.components.animator.FrameTime;
import com.asierso.vortexengine.components.animator.KeyFrame;
import com.asierso.vortexengine.testobjects.SampleCube;
import com.asierso.vortexengine.testobjects.TestWindow;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.window.event.Event;
import org.junit.Assert;
import org.junit.Test;

public class TestAnimator {
    @Test
    public void testStaticAnimator(){
        TestWindow t = new TestWindow("Static animator test",new Scene() {
            private SampleCube sc = new SampleCube();
            @Override
            public void start() {
                sc.setPosition(20,20);
                sc.setBoxSize(20,20);

                Animator anim = new Animator();

                KeyFrame kf1 = new KeyFrame();
                kf1.setTime(new FrameTime(0));
                kf1.setPosition(20,20);
                kf1.setBoxSize(20,20);
                kf1.setFrameBlend(Animator.BlendMode.STATIC);

                KeyFrame kf2 = new KeyFrame();

                kf2.setTime(new FrameTime(50));
                kf2.setPosition(50,50);
                kf2.setBoxSize(20,20);
                kf2.setRotation(45);
                kf2.setFrameBlend(Animator.BlendMode.STATIC);

                KeyFrame kf3 = new KeyFrame();

                kf3.setTime(new FrameTime(100));
                kf3.setPosition(75,75);
                kf3.setBoxSize(75,75);
                kf3.setFrameBlend(Animator.BlendMode.STATIC);

                anim.addKeyFrame(kf1);
                anim.addKeyFrame(kf2);
                anim.addKeyFrame(kf3);
                anim.setLoop(false);
                anim.setEndTime(new FrameTime(200));
                sc.addComponent(anim);
            }

            @Override
            public void update(Window context, Iterable<Event> events) {
                sc.instantiate(context);
                sc.<Animator>getComponent(Animator.class).start();
            }

            @Override
            public void close() {

            }
        },2500);

        t.run();
        Assert.assertTrue(true);
    }

    @Test
    public void testAdditiveAnimator(){
        TestWindow t = new TestWindow("Additive animator test",new Scene() {
            private SampleCube sc = new SampleCube();
            @Override
            public void start() {
                sc.setPosition(20,20);
                sc.setBoxSize(20,20);

                Animator anim = new Animator();

                KeyFrame kf1 = new KeyFrame();
                kf1.setTime(new FrameTime(1));
                kf1.setPosition(20,20);
                kf1.setFrameBlend(Animator.BlendMode.STATIC);

                KeyFrame kf2 = new KeyFrame();

                kf2.setTime(new FrameTime(50));
                kf2.setPosition(1,1);
                kf2.setRotation(1);
                kf2.setFrameBlend(Animator.BlendMode.ADDITIVE_INTERPOLATE);

                KeyFrame kf3 = new KeyFrame();

                kf3.setTime(new FrameTime(75));
                kf3.setPosition(1,2);
                kf3.setFrameBlend(Animator.BlendMode.ADDITIVE_INTERPOLATE);
                kf3.setBoxSize(1,1);

                anim.addKeyFrame(kf1);
                anim.addKeyFrame(kf2);
                anim.addKeyFrame(kf3);
                sc.addComponent(anim);
            }

            @Override
            public void update(Window context, Iterable<Event> events) {
                sc.instantiate(context);
                sc.<Animator>getComponent(Animator.class).start();
            }

            @Override
            public void close() {

            }
        },2500);

        t.run();
        Assert.assertTrue(true);
    }

    @Test
    public void testLoopingAnimator(){
        TestWindow t = new TestWindow("Loop animator test",new Scene() {
            private SampleCube sc = new SampleCube();
            @Override
            public void start() {
                sc.setPosition(20,20);
                sc.setBoxSize(20,20);

                Animator anim = new Animator();

                KeyFrame kf1 = new KeyFrame();
                kf1.setTime(new FrameTime(1));
                kf1.setPosition(20,20);
                kf1.setBoxSize(20,20);
                kf1.setFrameBlend(Animator.BlendMode.STATIC);

                KeyFrame kf2 = new KeyFrame();

                kf2.setTime(new FrameTime(10));
                kf2.setPosition(50,50);
                kf2.setBoxSize(50,50);
                kf2.setRotation(45);
                kf2.setFrameBlend(Animator.BlendMode.STATIC);


                anim.addKeyFrame(kf1);
                anim.addKeyFrame(kf2);
                anim.setLoop(true);
                anim.setEndTime(new FrameTime(20));
                sc.addComponent(anim);
            }

            @Override
            public void update(Window context, Iterable<Event> events) {
                sc.instantiate(context);
                sc.<Animator>getComponent(Animator.class).start();
            }

            @Override
            public void close() {

            }
        },2500);

        t.run();
        Assert.assertTrue(true);
    }
}
