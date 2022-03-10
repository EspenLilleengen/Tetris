package inf101v22.tetris.model;

import java.awt.Color;
import java.util.Objects;

/**
 * Represents a tile in a tetris board.
 * 
 * @author Espen Lilleengen
 */
public class Tile {
   
    public final Color color;
    public final char character;

    /**
     * Construct a tile with a color and a character that represent the type of tile
     * @param color the color of the tile
     * @param character the character that represent the given tile
     */
    public Tile(Color color, char character) {
        this.color = color;
        this.character = character;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (!(obj instanceof Tile)) 
            return false;
        Tile other = (Tile) obj;
        return this.color.equals(other.color) && this.character==other.character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, character);
    }

    @Override
    public String toString() {
        return "{ character='" + character + "'}";
    }

    
}
