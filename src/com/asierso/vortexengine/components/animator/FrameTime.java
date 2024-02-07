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
    public enum TimeMeasure {UNITS,TICKS};
    
    //Constructors
    public FrameTime(){
        this.units = 0;
        this.ticks = 0;
    }
    
    public FrameTime(int ticks){
        this.ticks = ticks;
        timeNormalization();
    }
    
    public FrameTime(int units,int ticks){
        this.units = units;
        this.ticks = ticks;
        timeNormalization();
    }
    
    private void timeNormalization(){
        if(ticks > 99){
            units += ticks / 100;
            ticks = ticks % 100;
        }
    }

    public int getMeasure(TimeMeasure measure) {
        int measuredTime = 0;
        switch(measure){
            case UNITS -> measuredTime = units;
            case TICKS -> measuredTime = ticks;
        }
        return measuredTime;
    }

    public void setMeasure(TimeMeasure measure,int value) {
        switch(measure){
            case UNITS -> units += value;
            case TICKS -> ticks += value;
        }
        timeNormalization();
    }

    public int getTicks() {
        return (units * 100) + ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        timeNormalization();
    }

    @Override
    public String toString() {
        return "FrameTime{" + "units=" + units + ", ticks=" + ticks + '}';
    }
    
}
