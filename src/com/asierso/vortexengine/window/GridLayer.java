/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.window;

import com.asierso.vortexengine.objects.GameObject;
import java.util.ArrayList;

/**
 * Allow to create a matrix layer used to render more GameObjects at the same time in the same z-axis
 * @author Asierso
 */
public class GridLayer extends ArrayList<ArrayList<GameObject>> {
    /**
     * Instantiate all GameObjecs added to current GridLayer
     * @param win Window where objects will be renderer
     */
    public void instantiate(Window win){
        for(ArrayList<GameObject> layer : this)
            for(GameObject handle : layer)
                handle.instantiate(win);
    }
    /**
     * Get a array of GameObjects finding it by his class inside GridLayer
     * @param type GameObject class to find
     * @return Array of found GameObjects
     */
    public GameObject[] getGameObjects(Class type){
        ArrayList<GameObject> list = new ArrayList<>();
        for(ArrayList<GameObject> layer : this)
            list.addAll(layer.stream().filter(obj -> obj.getClass() == type).toList());
        return (GameObject[])list.toArray();
    }
}
