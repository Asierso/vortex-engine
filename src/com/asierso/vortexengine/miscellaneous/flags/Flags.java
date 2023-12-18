package com.asierso.vortexengine.miscellaneous.flags;

import java.util.ArrayList;

/**
 * Object used to handle entity flags and allow access to it in every time that is needed. 
 * Is similar to have declared global proyect vars
 * @author Asierso
 */
public class Flags {
    //Singleton handlers
    private static Flags instance;
    private Flags(){}
    
    //Colection used to storage flags (work as an indexed dictionary)
    private ArrayList<EntityFlag> flagList = new ArrayList<>();
    
    /**
     * Get current instance of the flags system. Alls flags will de storaged in the same flag system instance
     * @return The instance of Flags
     */
    public static synchronized Flags getInstance(){
        if(instance==null){ //Singleton instance creator
            instance=new Flags();
        }
        return instance;
    }
    
    /**
     * Set a entityflag inside flags array. 
     * If the flag exists, the method modify his value and if the flag not exist, method adds it
     * @param entity Flag to set
     */
    public void set(EntityFlag entity){
        if(flagExists(entity.getKey()))
            flagList.set(getIndex(entity.getKey()),entity);
        else
            flagList.add(entity);
    }
    
    /**
     * Set a entityflag inside flags array. Method creates a new EntityFlag objects. 
     * If the flag exists, the method modify his value and if the flag not exist, method adds it
     * @param <T> Type of the value to assign
     * @param key Key value of the flag
     * @param value Value to assign
     */
    public <T> void set(String key,T value){
        set(new EntityFlag(key,value));
    }
    
    /**
     * Remove a flag of the flag array. If flag is not present, method will throw a NullPointerException 
     * @param entity Entity to delete
     */
    public void remove(EntityFlag entity){
        if(flagExists(entity.getKey()))
            flagList.remove(entity);
        else
            throw new NullPointerException();
    }
    
    /**
     * Remove a flag of the flag array by his key. If flag is not present, method will throw a NullPointerException 
     * @param key Key value of the flag to delete
     */
    public void remove(String key){
        if(flagExists(key))
            flagList.remove(getIndex(key));
        else
            throw new NullPointerException();
    }
    
    /**
     * Detects if there is a flag defined with the privided key value.
     * @param key Key value to filter the flag
     * @return Boolean of the flag existance
     */
    public boolean flagExists(String key){
        return flagList.stream().anyMatch(obj->obj.getKey().equals(key));
    }
    
    /**
     * Get entity flag filtered by his key value. If it's not detected, method will return null
     * @param key Key value used to filter
     * @return Getted entity flag
     */
    public EntityFlag get(String key){
        EntityFlag result = null;
        if(flagExists(key)){
            result = flagList.stream().filter(obj -> obj.getKey().equals(key)).findFirst().get();
        }
        return result;
    }
    
    /**
     * Get the id of the entity flag filtered by his key value. If is not detected, method will return -1
     * @param key Key of the flag to get
     * @return Index of tje flag
     */
    private int getIndex(String key){
        int index = -1;
        if(flagExists(key)){
            index = flagList.indexOf(get(key));
        }
        return index;
    }
}