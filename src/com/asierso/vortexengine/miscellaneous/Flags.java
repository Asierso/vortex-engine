package com.asierso.vortexengine.miscellaneous;

import java.util.ArrayList;

/**
 *
 * @author Asierso
 */
public class Flags {
    private static Flags instance;
    private Flags(){}
    private ArrayList<EntityFlag> flagList = new ArrayList<>();
    
    public static synchronized Flags getInstance(){
        if(instance==null){ //Singleton instance creator
            instance=new Flags();
        }
        return instance;
    }
    
    public void set(EntityFlag entity){
        if(flagExists(entity.getKey()))
            flagList.set(getIndex(entity.getKey()),entity);
        else
            flagList.add(entity);
    }
    
    public <T> void set(String key,T value){
        set(new EntityFlag(key,value));
    }
    
    public void remove(EntityFlag entity){
        if(flagExists(entity.getKey()))
            flagList.remove(entity);
    }
    
    public boolean flagExists(String key){
        return flagList.stream().anyMatch(obj->obj.getKey().equals(key));
    }
    
    public EntityFlag get(String key){
        EntityFlag result = null;
        if(flagExists(key)){
            result = flagList.stream().filter(obj -> obj.getKey().equals(key)).findFirst().get();
        }
        return result;
    }
    
    private int getIndex(String key){
        int index = -1;
        if(flagExists(key)){
            index = flagList.indexOf(get(key));
        }
        return index;
    }
}