package com.asierso.vortexengine.exceptions;

/**
 * Handles when a Color modifier code exceeds the allowed threshold [-255,255] at his initialization
 *
 * @author Asierso
 */
public class ColorOutOfBoundsException extends RuntimeException {
    public ColorOutOfBoundsException(int r, int g, int b, int a){
        super("Color code '[" + r + ", " + g + ", " + b + ", " + a + "]' out of bounds of [-255,255]");
    }
}
