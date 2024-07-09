package com.asierso.vortexengine.textures;

import com.asierso.vortexengine.miscellaneous.Dimension;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

/**
 * Make easy the process of extracts tile sets from sprite defining rect shapes
 *
 * @author Asierso
 */
public class TextureTilemap {

    private Dimension tilesSize;
    private Vector2i gridPos;

    /**
     * Initialize tile map. Set the tile map dimension
     *
     * @param tilesSize Size of all single tiles
     */
    public TextureTilemap(Dimension tilesSize) {
        this.tilesSize = tilesSize;
        this.gridPos = new Vector2i(0, 0);
    }

    /**
     * Set the tile map dimension and position of the grid to get the tiles
     *
     * @param tilesSize Size of all single tiles
     * @param gridPos Position of the grid in texture
     */
    public TextureTilemap(Dimension tilesSize, Vector2i gridPos) {
        this.tilesSize = tilesSize;
        this.gridPos = gridPos;
    }

    /**
     * Get a tile of the tile map. Cords are based in tile position inside
     * tile map scaled by tile size
     *
     * @param dx Tile map position X of the tile to get
     * @param dy Tile map position Y of the tile to get
     * @return Rect of the tile split
     */
    public IntRect getTile(int dx, int dy) {
        return new IntRect(gridPos.x + (dx * tilesSize.width), gridPos.y + (dy * tilesSize.height), gridPos.x + tilesSize.width, gridPos.y + tilesSize.height);
    }

    /**
     * Gets a sprite instance and apply to it a tile split in the
     * proportional texture
     *
     * @param dx Tile map position X of the tile to get
     * @param dy Tile map position Y of the tile to get
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
