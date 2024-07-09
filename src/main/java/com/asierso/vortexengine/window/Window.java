package com.asierso.vortexengine.window;

import com.asierso.vortexengine.miscellaneous.Dimension;
import org.jsfml.window.*;
import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.window.event.Event;

/**
 * This class is the GameWindow base class. 
 * Contains the SFML base window and load the scenes
 *
 * @author Asierso
 */
public class Window {

    //Private window data
    private int width = 0;
    private int height = 0;
    private int frames = 60;
    private FrameRate frate = FrameRate.FPS60;
    private boolean vsync;
    private float fps = 0;
    private String title = "Window";
    private Color background = Color.BLACK;
    private ConstView windowView;

    //SFML window instance
    private RenderWindow render;

    //Container of runtime methods
    private Scene scene;

    //Main constructors
    
    /**
     * Creates a void Window with undefined size
     */
    public Window() {
    }

    /**
     * Creates a void Window with specified size in px
     * @param width Window width
     * @param height Window height
     */
    public Window(int width, int height) {
        setSize(width, height);
    }

    //Field setters
    /**
     * Set window size
     *
     * @param width Horizontal size
     * @param height Vertical size
     */
    public final void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get current window dimension
     *
     * @return Dimension of window
     */
    public final Dimension getSize() {
        return new Dimension(width, height);
    }

    /**
     * Set window frame rate profile. This guarantee that a void
     * game window at least reach profile minimum amount of FPS on Windows devices
     *
     * @param frameRate Frame rate profile to render in a second
     */
    public final void setFrameRateProfile(FrameRate frameRate) {
        this.frate = frameRate;
    }

    /**
     * Set the window frame rate manually. Use this method sets frame profile in "specific" to take effect.
     * This doesn't guarantee to reach specified frame rate in a void window at Windows devices
     */
    public final void setFrameRate(int frames){
        this.frames = frames;
        frate = FrameRate.SPECIFIC;
    }

    /**
     * Get frames renderer in 1 second
     *
     * @return FPS value
     */
    public final float getFramesPerSecond() {
        return fps;
    }

    /**
     * Set runtime instructions when window executes
     *
     * @param scene Runtime class
     */
    public final void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Get RenderWindow class (SFML component)
     *
     * @return The SFML base window
     */
    public final RenderWindow getRender() {
        return render;
    }

    /**
     * Set top title of window
     *
     * @param title String title
     */
    public final void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set default background color when window refresh
     *
     * @param color Background color
     */
    public final void setBackground(Color color) {
        this.background = color;
    }

    /**
     * Render SFML window
     */
    private void render() {
        if (scene == null) {
            throw new NullPointerException();
        }
        //Create render instance
        render = new RenderWindow(new VideoMode(width, height), title);
        render.setVisible(true);
        reloadConfigs();

        //Startup execution
        scene.start();

        //Save default view
        windowView = render.getDefaultView();

        //Initialize FPS counter
        Clock frameClock = new Clock();
        while (render.isOpen()) {
            reloadConfigs();
            //Refresh window and execute update method
            render.clear(background);
            Iterable<Event> events = render.pollEvents();
            

            //Detects close event
            events.forEach(obj -> {
                switch (obj.type) {
                    case CLOSED -> {
                        scene.close();
                        render.close();
                    }
                    case RESIZED -> {
                        ((View) windowView).setSize(width, height);
                        render.setView(windowView);
                    }
                }
            });

            scene.update(this, events);
            //Display
            render.display();
            float currentTime = frameClock.restart().asSeconds();
            fps = 1.f / currentTime;
        }
    }

    /**
     * Shows window
     */
    public void show(){
        render();
    }

    /**
     * Instantiates SFML Window. Use show() instead
     *
     * @deprecated instantiate
     */
    @Deprecated
    public void instantiate(){
        render();
    }

    /**
     * Reload SFML configs with the set ones (even in main-loop)
     */
    private void reloadConfigs() {
        switch(frate){
            case FPS30 -> render.setFramerateLimit(45);
            case FPS60 -> render.setFramerateLimit(150);
            case FPS120 -> render.setFramerateLimit(310);
            case SPECIFIC -> render.setFramerateLimit(frames);
        }
        render.setTitle(title);
        render.setVerticalSyncEnabled(vsync);
    }

    /**
     * Close window
     */
    public void close() {
        if(render != null) //Avoid null pointer exception at non-rendering window
            render.close();
    }

    /**
     * Set window VSYNC option
     *
     * @param vsync Enables or disables vsync mode
     */
    public void setVsync(boolean vsync){
        this.vsync = vsync;
    }
}
