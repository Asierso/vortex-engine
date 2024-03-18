package com.asierso.vortexengine.window;

import com.asierso.vortexengine.objects.GameObject;
import java.util.ArrayList;

/**
 * Allow to create a layer used render more GameObjects at the same time in the same z-axis
 * @author Asierso
 */
public class Layer extends ArrayList<GameObject> {
    /**
     * Instantiate all GameObjecs added to current Layer
     * @param win Window where objects will be renderer
     */
    public void instantiate(Window win){
        for(GameObject handle : this)
            handle.instantiate(win);
    }
    /**
     * Get a array of GameObjects finding it by his class
     * @param type GameObject class to find
     * @return Array of found GameObjects
     */
    public GameObject[] getGameObjects(Class type){
        return (GameObject[])this.stream().filter(obj -> obj.getClass() == type).toArray();
    }
}
