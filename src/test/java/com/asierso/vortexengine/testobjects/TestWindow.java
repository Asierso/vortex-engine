package com.asierso.vortexengine.testobjects;

import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;

public class TestWindow {
    private Thread t;
    private int millis;
    private Window win;
    public TestWindow(String testName, Scene scene, int millis){
        win = new Window(400,300);
        win.setTitle(testName);
        win.setScene(scene);

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                win.instantiate();
            }
        });
        this.millis = millis;
    }

    public void run(){
        t.start();

        try{
            Thread.sleep(millis);
        }catch (Exception ignore){}
        win.close();
        t.interrupt();
    }
}
