package com.asierso.vortexengine.objects;

import com.asierso.vortexengine.miscellaneous.interfaces.Startable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

/**
 *
 * @author Asierso
 */
public class SoundSource extends GameObject implements Startable {

    //Sound object
    private final Sound sound = new Sound();

    //Soundtrack list
    private final ArrayList<Soundtrack> soundtracks = new ArrayList<>();

    //Load modes
    public enum LoadModes {
        FILE, STREAM
    }

    /**
     * Get the current value of sound pitch. Default is 1
     *
     * @return Number of pitch value
     */
    public final float getPitch() {
        return sound.getPitch();
    }

    /**
     * Set the sound pitch to a specific value
     *
     * @param pitch Value to establish the pitch
     */
    public final void setPitch(float pitch) {
        sound.setPitch(pitch);
    }

    /**
     * Detects if the sound is looping
     *
     * @return Looping value
     */
    public final boolean isLoop() {
        return sound.isLoop();
    }

    /**
     * Enable or disable the looping function
     *
     * @param isLoop Looping value
     */
    public final void setLoop(boolean isLoop) {
        sound.setLoop(isLoop);
    }

    /**
     * Get current sound volume
     *
     * @return The sound volume with decimals
     */
    public final float getVolume() {
        return sound.getVolume();
    }

    /**
     * Set sound volume. Default is 50
     *
     * @param volume Sound volume to assign
     */
    public final void setVolume(float volume) {
        sound.setVolume(volume);
    }

    /**
     * Start to play the sound
     */
    @Override
    public void start() {
        sound.play();
    }

    /**
     * Stops the sound
     */
    @Override
    public void stop() {
        sound.stop();
    }

    /**
     * Add a new soundtrack to soundList. This list is a sound container where
     * you can select the sound to play
     *
     * @param name The name of the sound to add
     * @param source Path or Stream of the sound to add
     * @param mode Aggregation mode of the sound
     */
    public void addSoundtrack(String name, Object source, LoadModes mode) throws IOException {
        Soundtrack st = new Soundtrack(this, name);
        switch (mode) {
            case FILE ->
                st.loadFromFile((Path) source);
            case STREAM ->
                st.loadFromStream((InputStream) source);
        }
        soundtracks.add(st);
    }

    /**
     * Add a new soundtrack to soundList like a file. Uses addSountrack method
     * with FILE load mode
     *
     * @param name The name of the sound to add
     * @param path Path of the sound to add
     */
    public void addSoundtrack(String name, String path) throws IOException {
        this.addSoundtrack(name, path, LoadModes.FILE);
    }

    /**
     * Get a specific soundtrack of the sountrack list by their name
     *
     * @param name The name of the soundtrack to select
     * @return Sountrack object
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Soundtrack getSoundtrack(String name) {
        return soundtracks.stream()
                .filter(obj -> (obj.getName().equals(name)))
                .findFirst().get();
    }

    /**
     * Get a specific soundtrack of the sountrack list by their position in the
     * list
     *
     * @param id The index of the soundtrack to select
     * @return Soundtrack object
     */
    public Soundtrack getSoundtrack(int id) {
        return soundtracks.get(id);
    }

    /**
     * Allow soundtrack to charge them in sound source buffer (soundtrack to
     * play)
     *
     * @param target Soundtrack to select
     */
    protected void select(Soundtrack target) {
        sound.setBuffer(target);
    }

    /**
     * Define main options of SFML base sound
     */
    public SoundSource() {
        sound.setVolume(50f);
        sound.setLoop(false);
        sound.setPitch(1.0f);
    }

    public static final class Soundtrack extends SoundBuffer {

        private final String name;
        private final SoundSource source;

        /**
         * Main constructor of soundtrack. Defines his name and source
         *
         * @param source Resource of the soundtrack song
         * @param name Name to identify the soundtrack
         */
        private Soundtrack(SoundSource source, String name) {
            this.source = source;
            this.name = name;
        }

        /**
         * Get the soundtrack name
         *
         * @return Soundtrack current name
         */
        private String getName() {
            return name;
        }

        /**
         * Select the sountrack. This is used to add the sound to the
         * reproduction buffer in SoundSource
         */
        public void select() {
            source.select(this);
        }
    }
}
