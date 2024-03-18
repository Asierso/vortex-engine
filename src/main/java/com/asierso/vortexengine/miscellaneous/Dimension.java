package com.asierso.vortexengine.miscellaneous;

/**
 * Represents dimension in with and height as a Vector2
 * @author Asierso
 */
public final class Dimension {

    /**
     * Dimensional width value
     */
    public int width;

    /**
     * Dimensional height value
     */
    public int height;

    /**
     * Creates dimension based in specified width and height
     * @param width Width value
     * @param height Height value
     */
    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get Dimension String representation
     * @return Dimension String
     */
    @Override
    public String toString() {
        return "Dimension{" + "width=" + width + ", height=" + height + '}';
    }
}
