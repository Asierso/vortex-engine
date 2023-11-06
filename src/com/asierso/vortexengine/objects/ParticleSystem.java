package com.asierso.vortexengine.objects;

import com.asierso.vortexengine.miscellaneous.ColorModifier;
import com.asierso.vortexengine.miscellaneous.interfaces.Startable;
import com.asierso.vortexengine.miscellaneous.interfaces.Transform;
import com.asierso.vortexengine.window.Window;
import java.util.ArrayList;
import java.util.Random;
import org.jsfml.graphics.Color;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

/**
 * Create a particle system object
 * A particle system instantiates GameObject using one GameObject prefab with random transform properties
 * @author Asierso
 */
public class ParticleSystem extends GameObject implements Startable, Transform {

    //Particle prefab (any gmo)
    private GameObject particle = null;

    //Particle flags
    private float lifetime = .5f;
    private int maxParticles = 10;

    //Private modifiers
    private Vector2f positionModifier = new Vector2f(0, 0);
    private Vector2f boxSizeModifier = new Vector2f(0, 0);
    private ColorModifier colorModifier = null;
    private float rotationModifier = 0;

    //Particle emission status (allow generation or not)
    private boolean isActive = true;

    //Modifier type selector
    public enum ParticleModifiers {
        POSITION, BOX_SIZE, COLOR, ROTATION
    }

    //Particles collection
    private final ArrayList<ParticleDictionary> instantiatedList = new ArrayList<>();

    //Others
    private final Clock counter = new Clock();
    private final Random rdn = new Random();

    /**
     * Method that render every single particle in the ParticleSystem Handle the
     * particle lifetime and max amount
     *
     * @param win Window where the ParticleSystem will be rendered
     */
    @SuppressWarnings("SuspiciousListRemoveInLoop")
    @Override
    protected void render(Window win) {
        //Timer to calculate single particle lifetime
        float time = counter.restart().asSeconds();

        //Instantiate new particle if is possible
        if (isActive) {
            generateParticle();
        }
        
        //Lifetime and particle render
        for (int i = 0; i < instantiatedList.size() - 1; i++) {
            //Update lifetime and load modifiers of each particle
            instantiatedList.get(i).lifetime -= time;
            loadModifiers(instantiatedList.get(i).particle);

            //Lifetime check (kill particles)
            if (instantiatedList.get(i).lifetime <= 0.0f) {
                instantiatedList.remove(i);
            }

            //Instance each particle
            instantiatedList.get(i).particle.instantiate(win);
        }
    }

    /**
     * Load defined modifiers of a specific particle This modifiers make
     * constrain changes in particles during lifetime
     *
     * @param particle Particle to handle
     */
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

            //Iterate decompose array and add or rest color modifier
            for (int i = 0; i < colorDecompose.length; i++) {
                if (colorDecompose[i] + modifiers[i] >= 0 && colorDecompose[i] + modifiers[i] <= 255) {
                    colorDecompose[i] += modifiers[i];
                }
            }
            particle.setColor(new Color(colorDecompose[0], colorDecompose[1], colorDecompose[2], colorDecompose[3]));
        }

        //Process rotation modifier
        if (rotationModifier != 0) {
            particle.setRotation(particle.getRotation() + rotationModifier);
        }
    }

    /**
     * Generates new particles if the particle limit is not exceeded
     */
    private void generateParticle() {
        //Clone gameObject in a random position if amount of particles is less than max
        if (instantiatedList.size() < maxParticles) {
            GameObject handle = null;

            //Try to clone it and adjust position
            try {
                handle = (GameObject) particle.clone();
            } catch (CloneNotSupportedException ignore) {

            }
            if (handle == null) {
                return;
            }
            handle.setPosition(rdn.nextInt(Math.round(this.getPosition().x), Math.round(this.getPosition().x + this.getBoxSize().x)), rdn.nextInt(Math.round(this.getPosition().y), Math.round(this.getPosition().y + this.getBoxSize().y)));

            //Add particle to particles list
            instantiatedList.add(new ParticleDictionary(handle, lifetime));
        }
    }

    /**
     * Set the particle model object. This is a GameObject that will be
     * instantiated like a particle
     *
     * @param model The GameObject model that will be used like a particle
     */
    public final void setParticleModel(GameObject model) {
        this.particle = model;
    }

    /**
     * Set the amount limit of particles. The ParticleSystem can't exceed this
     * limit
     *
     * @param max Number of max particles
     */
    public final void setMaxParticles(int max) {
        this.maxParticles = max;
    }

    /**
     * Get the established limit of max particles
     *
     * @return Number of max particles
     */
    public final int getMaxParticles() {
        return maxParticles;
    }

    /**
     * Set the particle lifetime in the Window. Particle will be destroyed when
     * lifetime = 0
     *
     * @param lifetime Particle time of life
     */
    public final void setLifetime(float lifetime) {
        this.lifetime = lifetime;
    }

    /**
     * Get the particle lifetime
     *
     * @return Particle time of life
     */

    public final float getLifetime() {
        return lifetime;
    }

    /**
     * Get the current amount of particles instantiated
     *
     * @return Number of elements in particles array
     */
    public final int getAmount() {
        return instantiatedList.size();
    }

    /**
     * Starts ParticleSystem
     */
    @Override
    public final void start() {
        isActive = true;
    }

    /**
     * Stops ParticleSystem
     */
    @Override
    public final void stop() {
        isActive = false;
    }

    /**
     * Set the Particles modifier fields to a specific value The modifiers are
     * constant values that operate the properties of the particles in each
     * execution tick. They allow you to make animations with the particles
     *
     * @param modifier Field of the ParticleSystem to modify.
     * @param value Object to which the property to be modified is set
     */
    public final void setModifier(ParticleModifiers modifier, Object value) {
        switch (modifier) {
            case POSITION ->
                positionModifier = (Vector2f) value;
            case BOX_SIZE ->
                boxSizeModifier = (Vector2f) value;
            case COLOR ->
                colorModifier = (ColorModifier) value;
            case ROTATION ->
                rotationModifier = (float) value;
        }
    }
    
    //Match particle with this lifetime
    private static class ParticleDictionary {

        public float lifetime;
        public GameObject particle;

        public ParticleDictionary(GameObject particle, float lifetime) {
            this.lifetime = lifetime;
            this.particle = particle;
        }
    }
}
