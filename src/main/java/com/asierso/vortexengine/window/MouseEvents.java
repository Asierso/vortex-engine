package com.asierso.vortexengine.window;

public interface MouseEvents {
    boolean isMouseHover();
    boolean isMouseClick(org.jsfml.window.Mouse.Button button);
    boolean isMouseDown(org.jsfml.window.Mouse.Button button);
}
