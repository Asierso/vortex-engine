package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.window.Window;

/**
 *
 * @author Asierso
 */
public class VortexTest {
    public static void TestWindow() {
        Window win = new Window(300, 400);
        win.setTitle("Escena prueba - Vortex");
        win.setRenderRuntime(new SampleScene());
        win.instantiate();
    }
}
