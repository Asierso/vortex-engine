package com.asierso.vortexengine.miscellaneous;

import com.asierso.vortexengine.miscellaneous.interfaces.Transform;
import org.jsfml.system.Vector2f;

/**
 * Allow to build a transform object to assign the same transform properties to
 * multiple objects reducing the amount of code needed
 *
 * @author Asierso
 */
public class TransformBuilder {
    private final Transform transform = new Transform() {
        private Vector2f position;
        private Vector2f size;
        private float rotation;
        @Override
        public Vector2f getPosition() {
            return  position;
        }

        @Override
        public void setPosition(Vector2f position) {
            this.position = position;
        }

        @Override
        public void setPosition(float x, float y) {
            this.position = new Vector2f(x,y);
        }

        @Override
        public void setRotation(float rotation) {
            this.rotation = rotation;
        }

        @Override
        public float getRotation() {
            return rotation;
        }

        @Override
        public void setBoxSize(Vector2f size) {
            this.size = size;
        }

        @Override
        public void setBoxSize(float x, float y) {
            this.size = new Vector2f(x,y);
        }

        @Override
        public Vector2f getBoxSize() {
            return size;
        }
    };

    public TransformBuilder(){

    }

    public TransformBuilder inPosition(Vector2f position){
        transform.setPosition(position);
        return this;
    }

    public TransformBuilder inPosition(float x, float y){
        transform.setPosition(x,y);
        return this;
    }

    public TransformBuilder withSize(Vector2f size){
        transform.setBoxSize(size);
        return this;
    }

    public TransformBuilder withSize(float x, float y){
        transform.setBoxSize(x,y);
        return this;
    }

    public TransformBuilder withRotation(float rotation){
        transform.setRotation(rotation);
        return this;
    }

    public Transform getTransform(){
        return transform;
    }

}
