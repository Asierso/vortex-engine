package com.asierso.vortexengine.miscellaneous;

import org.jsfml.graphics.Color;

/**
 * Color that can be modified in execution time. Using modifier controllers
 * @author Asierso
 */
public final class ColorModifier {
    //Red value
    public int r;
    
    //Green value
    public int g;
    
    //Blue value
    public int b;
    
    //Aplha value
    public int a;
    
    /**
     * Creates a new color modifier with custom colors. ColorModifier can supports negative color values
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @param a Alpha value
     */
    public ColorModifier(int r,int g, int b, int a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    /**
     * Parse ColorModifier to normal SFML color (set rgba values in treshold of 0-255)
     * @return SFML color
     */
    public Color getParsedColor(){
        r = (r < 0? 0 : r > 255? 255 : r);
        g = (g < 0? 0 : g > 255? 255 : g);
        b = (b < 0? 0 : b > 255? 255 : b);
        a = (a < 0? 0 : a > 255? 255 : a);
        return new Color(r,g,b,a);
    }
}
