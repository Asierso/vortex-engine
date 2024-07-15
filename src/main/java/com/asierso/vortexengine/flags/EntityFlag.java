package com.asierso.vortexengine.flags;

import com.asierso.vortexengine.exceptions.InvalidFlagKeyException;

/**
 * Define single flag. A Flag is a relationship between a key (String) and a value of any type. 
 * A collection of EntityTag works like a dictionary to set custom settings
 * 
 * @author Asierso
 * @param <T> Value of flag
 */
public final class EntityFlag<T> {
    //Private flag key value
    private String key;
    private T value;
    
    /**
     * Main constructor of a EntityFlag
     * @param key Identifier of the flag to define
     * @param value Value assigned to the flag
     */
    public EntityFlag(String key, T value){
        if(key.isBlank())
            throw new InvalidFlagKeyException(key);
        else {
            this.key = key;
            this.value = value;
        }
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
        if(key.isBlank())
            throw new InvalidFlagKeyException(key);
        else
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

    /**
     * Get EntityFlag String representation
     * @return EntityFlag key and value pair
     */
    @Override
    public String toString() {
        return "EntityFlag{" + "key=" + key + ", value=" + value + '}';
    }
}
