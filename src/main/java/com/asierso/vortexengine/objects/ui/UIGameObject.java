package com.asierso.vortexengine.objects.ui;

import com.asierso.vortexengine.components.events.EventListener;
import com.asierso.vortexengine.objects.GameObject;
import com.asierso.vortexengine.window.MouseTrigger;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

import java.text.NumberFormat;

public abstract class UIGameObject extends GameObject {
    private MouseTrigger mouseTrigger;
    private IntRect mouseTrgRect;
    private EventListener evt;
    public UIGameObject(){
        super();
        evt = new EventListener();
    }

    @Override
    public void setPosition(Vector2f position) {
        super.setPosition(position);
        updateTrigger();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateTrigger();
    }

    @Override
    public void setBoxSize(Vector2f size) {
        super.setBoxSize(size);
        updateTrigger();
    }

    @Override
    public void setBoxSize(float x, float y) {
        super.setBoxSize(x, y);
        updateTrigger();
    }

    @Override
    protected void render(Window context) {
        mouseTrigger = new MouseTrigger(context,mouseTrgRect,1);
        evt.run(this);
        uiRender(context);
    }

    protected abstract void uiRender(Window context);

    protected MouseTrigger getMouseTrigger(){
        return mouseTrigger;
    }

    protected void updateTrigger(){
        NumberFormat nf = NumberFormat.getIntegerInstance();
        int dx = Integer.parseInt(nf.format(this.getPosition().x));
        int dy = Integer.parseInt(nf.format(this.getPosition().y));
        int dw = Integer.parseInt(nf.format(this.getPosition().x + this.getBoxSize().x));
        int dh = Integer.parseInt(nf.format(this.getPosition().y + this.getBoxSize().y));
        mouseTrgRect = new IntRect(dx,dy,dw,dh);
    }

    protected EventListener getEventListener(){
        return evt;
    }
}
