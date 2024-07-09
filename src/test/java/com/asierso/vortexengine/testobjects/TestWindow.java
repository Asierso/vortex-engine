package com.asierso.vortexengine.testobjects;

import com.asierso.vortexengine.window.FrameRate;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;

import java.awt.*;

public class TestWindow {
    private Thread t;
    private int millis;
    private Window win;
    public TestWindow(String testName, Scene scene, int millis){
        win = new Window(400,300);
        win.setTitle(testName);
        win.setScene(scene);
        this.millis = millis;
        init();
    }

    public TestWindow(String testName, Scene scene, FrameRate frate, int millis){
        win = new Window(400,300);
        win.setTitle(testName);
        win.setScene(scene);
        win.setFrameRateProfile(frate);
        this.millis = millis;
        init();
    }

    private void init(){
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                win.show();
            }
        });
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
