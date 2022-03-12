package inf101v22.tetris.model.piece;

import java.awt.Color;
import java.util.Objects;

import inf101v22.tetris.model.Tile;

/** 
 * This class represents a Tetris piece with a shape and a {@link Tile}-object. 
 * There are eight different Tetris pieces that each are represented with an induvidual shape 
 * and {@link Tile}-object
 * 
 * @author Espen Lilleengen
*/
public class PieceShape {

    private Tile tile;
    private boolean[][] shape;

    static final PieceShape T = new PieceShape(
        new Tile(Color.GREEN, 'T'), 
        new boolean[][] {
            {true, true, true},
            {false, true, false}
        }
    );

    static final PieceShape S = new PieceShape(
        new Tile(Color.CYAN, 'S'), 
        new boolean[][] {
            {false, true, true},
            {true, true, false}
        }
    );

    static final PieceShape Z = new PieceShape(
        new Tile(Color.RED, 'Z'),
        new boolean[][] {
            {true, true, false},
            {false, true, true}
        }
    );

    static final PieceShape I = new PieceShape(
        new Tile(Color.MAGENTA, 'I'),
        new boolean[][] {
            { true,  true,  true, true }
        }
    );

    static final PieceShape J = new PieceShape(
        new Tile(Color.YELLOW, 'J'),
        new boolean[][] {
            { true, false, false },
            { true, true,  true }
        }
    );

    static final PieceShape L = new PieceShape(
        new Tile(Color.BLUE, 'L'),
        new boolean[][] {
            { false, false, true },
            { true,  true,  true }
        }
    );

    static final PieceShape O = new PieceShape(
        new Tile(Color.ORANGE, 'O'),
        new boolean[][] {
            {true, true},
            {true, true}
        }
    );

    static final PieceShape[] STANDARD_TETRIS_PIECES = {T, S, Z, I, J, L, O};

    private PieceShape(Tile tile, boolean[][] pieceShape) {
        this.tile = tile;
        this.shape = pieceShape;
    }

    /** @return the shape of the piece as a 2 dimentional array of boolean values*/
    public boolean[][] getShape() {
        return shape;
    }

    /** @return the width of the piece*/
    public int getWidth() {
        return shape[0].length;
    }

    /** @return the height of the piece*/
    public int getHeight() {
        return shape.length;
    }

    /** @return the tile coresponding to a {@link PieceShape}-object*/
    public Tile getTile() {
        return tile;
    }

    /**
     * Roates the shape of the teris piece
     * @return a 2 dimentional boolean array of the rotatet shape
     */
    public PieceShape rotate() { 
        boolean[][] rotatedShape = new boolean[getWidth()][getHeight()];

        for (int i = 0; i <getWidth(); i++) { 
            for (int j = 0; j < getHeight(); j++) {
                rotatedShape[getWidth()-1-i][j] = getShape()[j][i];
            }
        }
        return new PieceShape(getTile(), rotatedShape);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof PieceShape)) {
            return false;
        }
        PieceShape other = (PieceShape) obj;
        if (!(this.getHeight() == other.getHeight()))
            return false;
        if (!(this.getWidth() == other.getWidth()))
            return false;
        for (int i = 0; i <getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (this.shape[i][j] != other.shape[i][j]) { 
                    return false;
                }
            }
        }
        return this.tile.equals(other.tile);
    }


    @Override
    public int hashCode() {
        return Objects.hash(tile, shape);
    }


    @Override
    public String toString() {
        return "{" +
            " piece='" + tile.character + "'" +
            "}";
    }
}
