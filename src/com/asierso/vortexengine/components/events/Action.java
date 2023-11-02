package com.asierso.vortexengine.components.events;

import com.asierso.vortexengine.components.events.callback.ActionPerform;
import com.asierso.vortexengine.components.events.callback.EventPredicate;
import com.asierso.vortexengine.objects.GameObject;

/**
 *
 * @author Asierso
 */
public class Action {
    private final EventPredicate event;
    private final ActionPerform perform;
    public Action(EventPredicate handler,ActionPerform perform){
        this.event = handler;
        this.perform = perform;
    }
    public void check(GameObject handle) {
        if(event.run())
            perform.run(handle);
    }
}
