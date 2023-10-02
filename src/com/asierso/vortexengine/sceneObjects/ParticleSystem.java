/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.sceneObjects;

import com.asierso.vortexengine.miscellaneous.ColorModifier;
import com.asierso.vortexengine.window.Window;
import java.util.ArrayList;
import java.util.Random;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

/**
 *
 * @author asier
 */
public class ParticleSystem extends GameObject {
    //Particle prefab (any gmo)
    private GameObject particle = null;
    
    //Particle flags
    private float lifetime = .5f;
    private int maxParticles = 10;

    //Private modifiers
    private Vector2f positionModifier = new Vector2f(0, 0);
    private Vector2f boxSizeModifier = new Vector2f(0, 0);
    private ColorModifier colorModifier = null;
    
    //Mdifier type selector
    public enum ParticleModifiers {
        POSITION, BOX_SIZE, COLOR
    };
    
    //Particles collection
    private ArrayList<ParticleDictionary> instanciatedList = new ArrayList();
    
    //Others
    private Clock counter = new Clock();
    private Random rdn = new Random();
    
    public void render(Window win) {
        //Timer to calculate single particle lifetime
        float time = counter.restart().asSeconds();

        //Instanciate new particle if is posible
        generateParticle();

        for (int i = 0; i < instanciatedList.size()-1; i++) {
            //Update lifetime and load modifiers of each particle
            instanciatedList.get(i).lifetime -= time;
            loadModifiers(instanciatedList.get(i).particle);

            //Lifetime check (kill particles)
            if (instanciatedList.get(i).lifetime <= 0.0f) {
                instanciatedList.remove(i);
            }
            
            //Instance each particle
            instanciatedList.get(i).particle.instantiate(win);
        }
    }

    private void loadModifiers(GameObject particle) {
        //Process position modifiers
        if (positionModifier.x != 0) {
            particle.setPosition(particle.getPosition().x + positionModifier.x, particle.getPosition().y);
        }
        if (positionModifier.y != 0) {
            particle.setPosition(particle.getPosition().x, particle.getPosition().y + positionModifier.y);
        }
        
        //Process size modifiers
        if (boxSizeModifier.x != 0) {
            particle.setBoxSize(particle.getBoxSize().x + boxSizeModifier.x, particle.getBoxSize().y);
        }
        if (boxSizeModifier.y != 0) {
            particle.setBoxSize(particle.getBoxSize().x, particle.getBoxSize().y + boxSizeModifier.y);
        }
        
        //Process color modifiers
        if (colorModifier != null) {
            int[] colorDecompose = {
                particle.getColor().r,
                particle.getColor().g,
                particle.getColor().b,
                particle.getColor().a
            };
            //Modifiers buffer (color modifier class)
            int[] modifiers = {
                colorModifier.r,
                colorModifier.g,
                colorModifier.b,
                colorModifier.a
            };

            for (int i = 0; i < colorDecompose.length; i++) {
                if (colorDecompose[i] + modifiers[i] >= 0 && colorDecompose[i] + modifiers[i] <= 255);
                colorDecompose[i] += modifiers[i];
            }
            particle.setColor(new Color(colorDecompose[0], colorDecompose[1], colorDecompose[2], colorDecompose[3]));
        }
    }

    private void generateParticle() {
        //Clone gameObject in a random position if amount of particles is less than max
        if (instanciatedList.size() < maxParticles) {
            GameObject handle = null;
            
            //Try to clone it and adjust position
            try {
                handle = (GameObject) particle.clone();
            } catch (CloneNotSupportedException e) {

            }
            if (handle == null) {
                return;
            }
            handle.setPosition(rdn.nextInt(Math.round(this.getPosition().x), Math.round(this.getPosition().x + this.getBoxSize().x)), rdn.nextInt(Math.round(this.getPosition().y), Math.round(this.getPosition().y + this.getBoxSize().y)));
            
            //Add particle to particles list
            instanciatedList.add(new ParticleDictionary(handle, lifetime));
        }
    }

    public final void setParticleModel(GameObject model) {
        this.particle = model;
    }

    public final void setMaxParticles(int max) {
        this.maxParticles = max;
    }

    public final int getMaxParticles() {
        return maxParticles;
    }

    public final void setLifetime(float lifetime) {
        this.lifetime = lifetime;
    }

    public final float getLifetime() {
        return lifetime;
    }

    public final int getAmount() {
        return instanciatedList.size();
    }

    public final <T> void setModifier(ParticleModifiers modifier, T value) {
        switch (modifier) {
            case POSITION:
                positionModifier = (Vector2f) value;
                break;
            case BOX_SIZE:
                boxSizeModifier = (Vector2f) value;
                break;
            case COLOR:
                colorModifier = (ColorModifier) value;
                break;
        }
    }

    private class ParticleDictionary {

        public float lifetime = 0f;
        public GameObject particle;

        public ParticleDictionary(GameObject particle, float lifetime) {
            this.lifetime = lifetime;
            this.particle = particle;
        }
    }
}
