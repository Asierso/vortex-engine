/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.textures;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.Texture;

/**
 * Load image file and returns his texture
 * @author Asierso
 */
public class TextureLoader {
    //Texture Object with the image charged
    private Texture texture;
    
    /**
     * Load image file by his Uri
     * @param uri File Uri
     */
    public TextureLoader(String uri){
        try{ //Try to load image
            texture = new Texture();
            texture.loadFromFile(Paths.get(uri));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Gets SFML texture object with the image loaded
     * @return 
     */
    public Texture getTexture(){
        return texture;
    }
}
