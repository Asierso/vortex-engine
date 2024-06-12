package com.asierso.vortexengine.tests;

import com.asierso.vortexengine.objects.TextMesh;
import com.asierso.vortexengine.testobjects.TestWindow;
import com.asierso.vortexengine.window.Scene;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.window.event.Event;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class TestText {
    @Test
    public void createMultipleText() {
        TestWindow t = new TestWindow("Text test", new Scene() {
            private TextMesh txm;
            private ArrayList<TextMesh> txmList;
            @Override
            public void start() {
                txm = new TextMesh();
                txm.setText("This is a sample text");
                txm.setPosition(10,10);
                txm.setFontSize(10);
                txm.setColor(Color.RED);
                try {
                    txm.getFont().loadFromStream(this.getClass().getResourceAsStream("/fonts/sans-mono.ttf"));
                } catch (IOException e) {
                    Assert.fail();
                }

                txmList = new ArrayList<>();
                for(int i = 0; i< 10; i++){
                    try {
                        TextMesh handle = (TextMesh) txm.clone();
                        handle.setFontSize(handle.getFontSize()+ (i*2));
                        handle.setPosition(10,handle.getPosition().y*(i*2));
                        txmList.add(handle);
                    }catch (CloneNotSupportedException e){
                        Assert.fail();
                    }
                }
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                txmList.forEach(o -> o.instantiate(window));
            }

            @Override
            public void close() {

            }
        }, 2500);

        t.run();
        Assert.assertTrue(true);

    }
    @Test
    public void createLongText() {
        TestWindow t = new TestWindow("Long text test", new Scene() {
            private TextMesh txm;
            @Override
            public void start() {
                txm = new TextMesh();
                txm.setPosition(0,00);
                txm.setFontSize(10);
                txm.setColor(Color.RED);
                try {
                    txm.getFont().loadFromStream(this.getClass().getResourceAsStream("/fonts/sans-mono.ttf"));
                } catch (IOException e) {
                    Assert.fail();
                }

                for(int i = 0;i<50;i++){
                    txm.setText(txm.getText() + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur cursus lacinia tortor, sed eleifend sapien. Nulla facilisi. Mauris et iaculis ligula, sed auctor nunc. Cras ullamcorper feugiat tortor, eget faucibus neque cursus in\n");
                }
            }

            @Override
            public void update(Window window, Iterable<Event> events) {
                txm.instantiate(window);
            }

            @Override
            public void close() {

            }
        }, 2500);

        t.run();
        Assert.assertTrue(true);

    }
}
