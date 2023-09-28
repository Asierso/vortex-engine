/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.asierso.vortexengine;

import com.asierso.vortexengine.gameComponents.GameObject;
import com.asierso.vortexengine.gameComponents.Rigibody;
import com.asierso.vortexengine.gameComponents.Rigibody.RigibodyStates;
import com.asierso.vortexengine.gameComponents.TextMesh;
import com.asierso.vortexengine.window.BaseScene;
import com.asierso.vortexengine.window.Window;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.*;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.window.event.Event;

/**
 *
 * @author asier
 */
public class VortexEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Window win = new Window(300, 400);
        win.setTitle("Hola Mundo");
        win.setRenderRuntime(new SampleScene());
        win.instantiate();
    }

}
