package com.asierso.vortexengine.miscellaneous.flags;

/**
 * Define single flag. A Flag is a relationship between a key (String) and a value of any type. 
 * A collection of EntityTag works like a dictionary to set custom settings
 * 
 * @author Asierso
 */
public class EntityFlag<T> {
    //Private flag key value
    private String key;
    private T value;
    
    /**
     * Main constructor of a EntityFlag
     * @param key Identifier of the flag to define
     * @param value Value assigned to the flag
     */
    public EntityFlag(String key, T value){
        this.key = key;
        this.value = value;
    }

    /**
     * Get flag defined key
     * @return Defined key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set a new key value to the current flag
     * @param key Key value to assign
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Get the stored value of the current flag
     * @return Value stored
     */
    public T getValue() {
        return value;
    }

    /**
     * Modify current value of the flag
     * @param value New value to assign
     */
    public void setValue(T value) {
        this.value = value;
    }    
}
