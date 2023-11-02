package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.components.animator.Animator;
import com.asierso.vortexengine.components.physics.Centrifuge;
import com.asierso.vortexengine.sceneObjects.ParticleSystem;
import com.asierso.vortexengine.components.physics.Rigibody;
import com.asierso.vortexengine.components.animator.KeyFrame;
import com.asierso.vortexengine.miscellaneous.ColorModifier;
import com.asierso.vortexengine.sceneObjects.TextMesh;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;

import java.io.IOException;
import java.nio.file.Path;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

/**
 *
 * @author Asierso
 */
@SuppressWarnings("unused")
public class SampleScene implements Scene {

    private final SampleCube[] cubes = {new SampleCube(), new SampleCube(), new SampleCube(), new SampleCube(), new SampleCube()};
    private int itq = 1;
    private boolean incremental = true;
    private float massConstant = 7f;
    private final ParticleSystem[] ppss = {new ParticleSystem(), new ParticleSystem(), new ParticleSystem(), new ParticleSystem()};
    private TextMesh tm;
    private SampleDamero dam;
    private final SampleCube cubeAn = new SampleCube();

    private final SampleCube srot = new SampleCube();

    @Override
    public void start() {
        cubes[0].setPosition(0, 300);
        cubes[1].setPosition(0, 50);
        cubes[2].setPosition(0, 110);
        cubes[3].setPosition(0, 170);
        cubes[4].setPosition(0, 230);

        cubes[0].setBoxSize(50, 50);
        cubes[1].setBoxSize(50, 50);
        cubes[2].setBoxSize(70, 50);
        cubes[3].setBoxSize(90, 50);
        cubes[4].setBoxSize(110, 50);

        cubes[0].setColor(Color.RED);
        cubes[1].setColor(Color.YELLOW);
        cubes[2].setColor(Color.GREEN);
        cubes[3].setColor(Color.BLUE);
        cubes[4].setColor(Color.MAGENTA);

        Rigibody rb = new Rigibody();
        rb.setAcceleration(-1);
        rb.setBodyState(Rigibody.RigibodyStates.DYNAMIC);
        for (int i = 1; i < cubes.length; i++) {
            rb.getCollisionalObjectList().add(cubes[i]);
        }
        cubes[0].addComponent(rb);

        tm = new TextMesh(0, 0, "Vortex Test - ");
        tm.setFontSize(15);

        try {
            tm.getFont().loadFromFile(Path.of("C:/Windows/Fonts/Arial.ttf"));
        } catch (IOException ignore) {

        }

        var cps = new SampleCube();
        cps.setColor(new Color(255, 0, 0, 255));
        cps.setBoxSize(3, 3);

        var cps2 = new SampleCube();
        cps2.setColor(new Color(0, 120, 200, 255));
        cps2.setBoxSize(3, 3);

        var cps3 = new SampleCube();
        cps3.setColor(new Color(240, 240, 240, 120));
        cps3.setBoxSize(3, 3);

        var cps4 = new SampleCube();
        cps4.setColor(new Color(0, 255, 240, 120));
        cps4.setBoxSize(2, 3);

        ppss[0].setPosition(20, 350);
        ppss[1].setPosition(100, 350);
        ppss[2].setPosition(160, 350);
        ppss[3].setPosition(230, 380);

        ppss[0].setBoxSize(35, 35);
        ppss[1].setBoxSize(35, 1);
        ppss[2].setBoxSize(35, 35);
        ppss[3].setBoxSize(35, 1);

        ppss[0].setParticleModel(cps);
        ppss[1].setParticleModel(cps2);
        ppss[2].setParticleModel(cps3);
        ppss[3].setParticleModel(cps4);

        ppss[0].setMaxParticles(10);
        ppss[1].setMaxParticles(25);
        ppss[2].setMaxParticles(30);
        ppss[3].setMaxParticles(20);

        ppss[0].setLifetime(.5f);
        ppss[1].setLifetime(.75f);
        ppss[2].setLifetime(1.2f);
        ppss[3].setLifetime(.6f);

        ppss[0].setModifier(ParticleSystem.ParticleModifiers.POSITION, new Vector2f(1f, 0));
        ppss[0].setModifier(ParticleSystem.ParticleModifiers.BOX_SIZE, new Vector2f(1f, 0));
        ppss[0].setModifier(ParticleSystem.ParticleModifiers.COLOR, new ColorModifier(0, 10, 0, 0));
        ppss[0].setModifier(ParticleSystem.ParticleModifiers.ROTATION, 1f);

        ppss[1].setModifier(ParticleSystem.ParticleModifiers.POSITION, new Vector2f(0f, 1f));
        ppss[1].setModifier(ParticleSystem.ParticleModifiers.BOX_SIZE, new Vector2f(0, 0));
        ppss[1].setModifier(ParticleSystem.ParticleModifiers.COLOR, new ColorModifier(0, 2, 0, -7));

        ppss[2].setModifier(ParticleSystem.ParticleModifiers.POSITION, new Vector2f(0f, -.5f));
        ppss[2].setModifier(ParticleSystem.ParticleModifiers.BOX_SIZE, new Vector2f(.5f, .5f));
        ppss[2].setModifier(ParticleSystem.ParticleModifiers.COLOR, new ColorModifier(0, 0, 0, -10));

        ppss[3].setModifier(ParticleSystem.ParticleModifiers.BOX_SIZE, new Vector2f(0, -.75f));
        ppss[3].setModifier(ParticleSystem.ParticleModifiers.COLOR, new ColorModifier(10, 0, 0, -5));

        dam = new SampleDamero();
        dam.setPosition(0, 0);
        dam.setColor(new Color(60, 60, 60, 255));

        cubeAn.setPosition(20, 0);
        cubeAn.setBoxSize(20, 20);

        KeyFrame kf = new KeyFrame();
        kf.setPosition(0, 10);

        kf.setTime(2);
        kf.setFrameBlend(Animator.BlendMode.ADDITIVE_INTERPOLATE);

        KeyFrame kf2 = new KeyFrame();
        kf2.setPosition(0, -10);
        kf2.setFrameBlend(Animator.BlendMode.ADDITIVE_INTERPOLATE);
        kf2.setTime(4);

        cubeAn.addComponent(new Animator());

        cubeAn.<Animator>getComponent(Animator.class).addKeyFrame(kf);
        cubeAn.<Animator>getComponent(Animator.class).addKeyFrame(kf2);
        cubeAn.<Animator>getComponent(Animator.class).start();

        srot.setPosition(100, 100);
        srot.setRotation(15);
        srot.setBoxSize(25, 25);
        srot.setColor(new Color(255,128,0));
        srot.addComponent(new Centrifuge());
        srot.<Centrifuge>getComponent(Centrifuge.class).setMass(4);
        srot.<Centrifuge>getComponent(Centrifuge.class).setAcceleration(3);
        srot.<Centrifuge>getComponent(Centrifuge.class).addForce(40);
    }

    @Override
    public void update(Window rw, Iterable<Event> events) {
        //Draw bg
        dam.setBoxSize(rw.getSize().width, rw.getSize().height);
        dam.instantiate(rw);

        if (cubes[itq].getPosition().x + cubes[itq].getBoxSize().x >= rw.getSize().width && incremental) {
            itq++;
        } else if (cubes[itq].getPosition().x <= 0 && !incremental) {
            itq--;
        }

        if (itq == 5) {
            incremental = false;
            massConstant += 1f;
            itq--;
        }

        if (itq == 0) {
            incremental = true;
            massConstant += 1f;
            itq++;
        }
        if (incremental) {
            cubes[itq].setPosition(cubes[itq].getPosition().x + 5f, cubes[itq].getPosition().y);
        } else {
            cubes[itq].setPosition(cubes[itq].getPosition().x - 10f, cubes[itq].getPosition().y);
        }

        for (SampleCube cube : cubes) {
            cube.instantiate(rw);
        }

        Rigibody rb = cubes[0].getComponent(Rigibody.class);
        rb.setMass(massConstant);
        rb.setWeighing(Rigibody.GravityWeighing.MASS_PRECISION);

        if (cubes[0].getPosition().y <= 0) {
            rb.setAcceleration(1.2f);
            rb.flushDelta();
            if (incremental) {
                cubes[0].setPosition(0, 1);
            }
        }
        if (cubes[0].getPosition().y + cubes[0].getBoxSize().y >= rw.getSize().height) {
            rb.setAcceleration(-1.2f);
            rb.flushDelta();
            if (!incremental) {
                cubes[0].setPosition(0, rw.getSize().height - cubes[0].getBoxSize().y - 1);
            }
        }

        if (incremental) {
            ppss[0].stop();
        } else {
            ppss[0].start();
        }

        StringBuilder particleDebug = new StringBuilder();
        int psAmount = 0;
        for (var ps : ppss) {
            ps.instantiate(rw);
            particleDebug.append(ps.getAmount()).append(",");
            psAmount += ps.getAmount();

        }

        tm.setText("Vortex Test Window - " + Math.round(rw.getFramesPerSecond()) + " fps \nPosition (x=" + cubes[0].getPosition().x + ",y=" + cubes[0].getPosition().y + ")\nRigibody (acel=" + rb.getAcceleration() + ",delta=" + rb.getDelta() + ",mass=" + rb.getMass() + ")\nParticles (array=[" + particleDebug + "],total=" + psAmount + ")");
        tm.instantiate(rw);
        cubeAn.instantiate(rw);
        srot.instantiate(rw);
    }

    @Override
    public void close() {

    }
}
