/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.components.animator;

/**
 *
 * @author asierso
 */
public class FrameTime {

    private int units; //1 unit = 100 ticks
    private int ticks; //time representation

    //Measure types

    /**
     * FrameTime measures to representate time
     */
    public enum TimeMeasure {

        /**
         * Units time measure. 1 unit = 100 ticks
         */
        UNITS,

        /**
         * Base time measure. 1 ticks = 0.01 unit
         */
        TICKS
    };

    /**
     * Void constructor. Set a void FrameTime with 0 ticks
     */
    public FrameTime() {
        this.units = 0;
        this.ticks = 0;
    }

    /**
     * Create FrameTime with ticks value. Ticks vlaue will parsed automatically
     * if surpases 100 ticks incrementing units
     *
     * @param ticks Ticks value
     */
    public FrameTime(int ticks) {
        this.ticks = ticks;
        timeNormalization();
    }

    /**
     * Create FrameTime with cusotm units and ticks value Ticks vlaue will
     * parsed automatically if surpases 100 ticks incrementing units
     *
     * @param units Units value (1 unit = 100 ticks)
     * @param ticks Ticks value
     */
    public FrameTime(int units, int ticks) {
        this.units = units;
        this.ticks = ticks;
        timeNormalization();
    }

    /**
     * Normalizate time following the rule: 1 ud = 100 ticks This method is used
     * internally to parse units na dticks value
     */
    private void timeNormalization() {
        if (ticks > 99) {
            units += ticks / 100;
            ticks = ticks % 100;
        }
    }

    /**
     * Get FrameTime time measure. Returned time measure is the actual value of
     * selected measure Example: If you initialize a FrameTime with 120 ticks
     * and you request units value, method will returns 1
     *
     * @param measure Measure type to return
     * @return Measure value
     */
    public int getMeasure(TimeMeasure measure) {
        int measuredTime = 0;
        switch (measure) {
            case UNITS ->
                measuredTime = units;
            case TICKS ->
                measuredTime = ticks;
        }
        return measuredTime;
    }

    /**
     * Set time value in specified measure format. Value will parsed
     * automatically if surpases 100 ticks incrementing units
     *
     * @param measure Measure type to set
     * @param value Value to set
     */
    public void setMeasure(TimeMeasure measure, int value) {
        switch (measure) {
            case UNITS ->
                units = value;
            case TICKS ->
                ticks = value;
        }
        timeNormalization();
    }

    /**
     * Get FrameTime value in ticks considering that 1 unit = 100 ticks
     *
     * @return FrameTime ticks
     */
    public int getTicks() {
        return (units * 100) + ticks;
    }

    /**
     * Get FrameTime String representation
     * @return FrameTime String
     */
    @Override
    public String toString() {
        return "FrameTime{" + "units=" + units + ", ticks=" + ticks + '}';
    }

}
