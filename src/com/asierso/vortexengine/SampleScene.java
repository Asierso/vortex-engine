/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine;

import com.asierso.vortexengine.gameComponents.Rigibody;
import com.asierso.vortexengine.gameComponents.TextMesh;
import com.asierso.vortexengine.window.BaseScene;
import com.asierso.vortexengine.window.Window;
import java.io.IOException;
import java.nio.file.Path;
import org.jsfml.graphics.Color;
import org.jsfml.window.event.Event;

/**
 *
 * @author asier
 */
public class SampleScene extends BaseScene{
    private SampleCube[] cubes = {new SampleCube(), new SampleCube(), new SampleCube(), new SampleCube(), new SampleCube()};
            private int itq = 1;
            private boolean incremental = true;
            private TextMesh tm;

            @Override
            public void start() {
                cubes[0].setPosition(0, 300);
                cubes[1].setPosition(0, 50);
                cubes[2].setPosition(0, 100);
                cubes[3].setPosition(0, 150);
                cubes[4].setPosition(0, 200);

                cubes[0].setBoxSize(50, 50);
                cubes[1].setBoxSize(50, 50);
                cubes[2].setBoxSize(70, 50);
                cubes[3].setBoxSize(90, 50);
                cubes[4].setBoxSize(110, 50);

                cubes[0].setCubeColor(Color.RED);
                cubes[1].setCubeColor(Color.YELLOW);
                cubes[2].setCubeColor(Color.GREEN);
                cubes[3].setCubeColor(Color.BLUE);
                cubes[4].setCubeColor(Color.MAGENTA);

                Rigibody rb = new Rigibody();
                rb.setAcceleration(-1);
                rb.setBodyState(Rigibody.RigibodyStates.DYNAMIC);
                for (int i = 1; i < cubes.length; i++) {
                    rb.getCollisionalObjectList().add(cubes[i]);
                }
                cubes[0].addComponent(rb);

                tm = new TextMesh(100, 0, "Vortex Test - ");
                tm.setFontSize(15);
                try {
                    tm.getFont().loadFromFile(Path.of("C:/Windows/Fonts/Arial.ttf"));
                } catch (IOException ex) {

                }
            }

            @Override
            public void update(Window rw, Iterable<Event> events) {
                if (cubes[itq].getPosition().x + cubes[itq].getBoxSize().x >= rw.getSize().width && incremental) {
                    itq++;
                } else if (cubes[itq].getPosition().x <= 0 && !incremental) {
                    itq--;
                }

                if (itq == 5) {
                    incremental = false;
                    itq--;
                }

                if (itq == 0) {
                    incremental = true;
                    itq++;
                }

                if (incremental) {
                    cubes[itq].setPosition(cubes[itq].getPosition().x + 5f, cubes[itq].getPosition().y);
                } else {
                    cubes[itq].setPosition(cubes[itq].getPosition().x - 5f, cubes[itq].getPosition().y);
                }

                for (int i = 0; i < cubes.length; i++) {
                    cubes[i].instantiate(rw);
                }

                Rigibody rb = cubes[0].getComponentById(0);
                if (cubes[0].getPosition().y <= 0) {
                    rb.setAcceleration(1.2f);
                    rb.flushDelta();
                    if (incremental == true) {
                        cubes[0].setPosition(0, 1);
                    }
                }
                if (cubes[0].getPosition().y + cubes[0].getBoxSize().y >= rw.getSize().height) {
                    rb.setAcceleration(-1.2f);
                    rb.flushDelta();
                    if (incremental == false) {
                        cubes[0].setPosition(0, rw.getSize().height - cubes[0].getBoxSize().y - 1);
                    }
                }

                tm.setText("Vortex Test - " + Math.round(rw.getFramesPerSecond()) + " fps \nPos (x=" + cubes[0].getPosition().x + ",y=" + cubes[0].getPosition().y + ")\nRigiBody (ac=" + rb.getAcceleration() + ",dlt=" + rb.getDelta() + ",mss=" + rb.getMass() + ")");
                tm.instantiate(rw);
            }
}
