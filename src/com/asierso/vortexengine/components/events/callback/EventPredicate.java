package com.asierso.vortexengine.components.events.callback;

import com.asierso.vortexengine.objects.GameObject;

/**
 *
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
