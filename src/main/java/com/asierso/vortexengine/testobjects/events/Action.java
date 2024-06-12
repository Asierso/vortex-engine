package com.asierso.vortexengine.testobjects.events;

import com.asierso.vortexengine.testobjects.events.callback.ActionPerform;
import com.asierso.vortexengine.testobjects.events.callback.EventPredicate;
import com.asierso.vortexengine.objects.GameObject;

/**
 * Defines single action. Actions are composed by his condition and the code to execute if its true
 * @author Asierso
 */
public class Action {
    //Event and perform callbacks
    private final EventPredicate predicate;
    private final ActionPerform perform;
    
    /**
     * Main construction of action
     * @param predicate Expresion to evaluate every action execution
     * @param perform Code to execute if the expresion return is true
     */
    public Action(EventPredicate predicate,ActionPerform perform){
        this.predicate = predicate;
        this.perform = perform;
    }
    
    /**
     * Check predicate if it's true
     * @param handle Object implicated
     */
    protected void check(GameObject handle) {
        if(predicate.run(handle))
            perform.run(handle);
    }
}
