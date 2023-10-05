/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.window.Window;

/**
 *
 * @author asier
 */
public class VortexTest {
    public static void TestWindow() {
        Window win = new Window(300, 400);
        win.setTitle("Escena prueba - Vortex");
        win.setRenderRuntime(new SampleScene());
        win.instantiate();
    }
}
