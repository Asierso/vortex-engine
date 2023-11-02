/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.components.events;

import com.asierso.vortexengine.components.Component;
import com.asierso.vortexengine.objects.GameObject;
import java.util.ArrayList;

/**
 *
 * @author Asierso
 */
public class EventListener implements Component {

    private final ArrayList<Action> actionsList = new ArrayList<>();

    public ArrayList<Action> getActionsList() {
        return actionsList;
    }

    @Override
    public void run(GameObject target) {
        actionsList.forEach(action -> action.check(target));
    }
}
