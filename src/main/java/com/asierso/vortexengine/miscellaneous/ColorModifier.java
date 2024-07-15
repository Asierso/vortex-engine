package com.asierso.vortexengine.miscellaneous;

import com.asierso.vortexengine.exceptions.ColorOutOfBoundsException;
import org.jsfml.graphics.Color;

/**
 * Color that can be modified in execution time. Using modifier controllers
 * @author Asierso
 */
public final class ColorModifier {
    /**
     * Red value
     */
    public int r;

    /**
     * Green value
     */
    public int g;
    
    /**
     * Blue value
     */
    public int b;
    
    /**
     * Alpha value
     */
    public int a;
    
    /**
     * Creates a new color modifier with custom colors. ColorModifier can supports negative color values
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @param a Alpha value
     */
    public ColorModifier(int r,int g, int b, int a){
        if(!inBounds(r,g,b,a))
            throw new ColorOutOfBoundsException(r,g,b,a);

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    /**
     * Creates a new color modifier using a SFML color base and modifying it with rgba values
     * @param base SFML base color
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @param a Alpha value
     */
    public ColorModifier(Color base,int r,int g, int b, int a){
        if(!inBounds(r,g,b,a))
            throw new ColorOutOfBoundsException(r,g,b,a);

        this.r = base.r + r;
        this.g = base.g + g;
        this.b = base.b + b;
        this.a = base.a + a;
    }
    
    /**
     * Parse ColorModifier to normal SFML color (set rgba values in threshold of 0-255)
     * @return SFML color
     */
    public Color getParsedColor(){
        r = ((r < 0) ? 0 : ((r > 255) ? 255 : r));
        g = ((g < 0) ? 0 : ((g > 255) ? 255 : g));
        b = ((b < 0) ? 0 : ((b > 255) ? 255 : b));
        a = ((a < 0) ? 0 : ((a > 255) ? 255 : a));
        return new Color(r,g,b,a);
    }

    private boolean inBounds(int r,int g,int b, int a){
        return (r >= -255 && r <= 255 && g >= -255 && g <= 255 && b >= -255 && b <= 255 && a >= -255 && a <= 255);
    }

    /**
     * Get ColorModifier String representation
     * @return ColorModifier String
     */
    @Override
    public String toString() {
        return "ColorModifier{" + "r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + '}';
    }
}
