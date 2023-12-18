/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.textures;

import com.asierso.vortexengine.miscellaneous.Dimension;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

/**
 * Make easy the process of extracts tilesets from sprite defining IntRects
 *
 * @author Asierso
 */
public class TextureTilemap {

    private Dimension tilesSize;
    private Vector2i gridPos;

    /**
     * Initialize tilemap. Set the timemap dimension
     *
     * @param tilesSize Size of all single tiles
     */
    public TextureTilemap(Dimension tilesSize) {
        this.tilesSize = tilesSize;
        this.gridPos = new Vector2i(0, 0);
    }

    /**
     * Set the tilemap dimension and position of the grid to get the tiles
     *
     * @param tilesSize Size of all single tiles
     * @param gridPos Position of the grid in texture
     */
    public TextureTilemap(Dimension tilesSize, Vector2i gridPos) {
        this.tilesSize = tilesSize;
        this.gridPos = gridPos;
    }

    /**
     * Get a tile of the tilemap. Coords are based in tile position inside
     * tilemap scaled by tile size
     *
     * @param dx Tilemap position X of the tile to get
     * @param dy Tilemap position Y of the tile to get
     * @return Rect of the tile splitted
     */
    public IntRect getTile(int dx, int dy) {
        return new IntRect(gridPos.x + (dx * tilesSize.width), gridPos.y + (dy * tilesSize.height), gridPos.x + tilesSize.width, gridPos.y + tilesSize.height);
    }

    /**
     * Gets an sprite instance and apply to it a tile splitted in the
     * proportionated texture
     *
     * @param dx Tilemap position X of the tile to get
     * @param dy Tilemap position Y of the tile to get
     * @param target Sprite to modify his texture
     * @param texture Texture to split and get the tile to apply
     * @return Sprite with the tile texture applied
     */
    public Sprite getSpriteTiled(int dx, int dy, Sprite target, Texture texture) {
        target.setTexture(texture);
        target.setTextureRect(getTile(dx, dy));
        return target;
    }
}
