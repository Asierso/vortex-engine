package com.asierso.vortexengine.miscellaneous;

/**
 * Color that can be modified in execution time. Using modifier controllers
 * @author Asierso
 */
public class ColorModifier {
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
}
