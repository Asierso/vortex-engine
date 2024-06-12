package com.asierso.vortexengine.testobjects.events.callback;

import com.asierso.vortexengine.objects.GameObject;

/**
 * Evaluates a condition to execute actions. It depends of the returned boolean value
 * @author Asierso
 */
public interface EventPredicate {
    /**
     * Evaluates a condition
     * @param handle Object implicated in evaluation
     * @return Condition result
     */
    public boolean run(GameObject handle);
}
