package com.asierso.vortexengine.testobjects.events;

import com.asierso.vortexengine.testobjects.Component;
import com.asierso.vortexengine.objects.GameObject;
import java.util.ArrayList;

/**
 * Event listener allows GameObject to have embebed conditions. This is usefull to create input events
 * @author Asierso
 */
public class EventListener implements Component {
    //Actions container array
    private final ArrayList<Action> actionsList = new ArrayList<>();

    /**
     * Get the actions list array
     * @return Actions ArrayList
     */
    public ArrayList<Action> getActionsList() {
        return actionsList;
    }

    /**
     * Runs each action in actions list
     * @param target GameObject to handle in action execution
     */
    @Override
    public void run(GameObject target) {
        actionsList.forEach(action -> action.check(target));
    }
}
