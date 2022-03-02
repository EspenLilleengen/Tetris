package inf101v22.tetris.model;

import java.awt.Color;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.view.TetrisViewable;

/**
 * A class that implements {@link TetrisViewable}. Holds a {@link TetrisBoard}-object 
 * 
 * @author Espen Lilleengen
 */
public class TetrisModel implements TetrisViewable{

    public final TetrisBoard tetrisBoard;

    /**
     * Construct a tetris model that holds a {@link TetrisBoard}-object with the width of 10 tiles an a height of 15 tiles
     */
    public TetrisModel() {
        this(15,10);
    }

    /**
     * Constuct a tetris model that holds a {@link TetrisBoard}-object with the given dimensions
     * @param rows the number of rows of the tetris board
     * @param cols the number of columns of the tetris board
     */
    public TetrisModel(int rows, int cols) {
        this.tetrisBoard = new TetrisBoard(rows,cols);
        tetrisBoard.set(new Coordinate(0,0), new Tile(Color.GREEN, 'g'));
        tetrisBoard.set(new Coordinate(0, tetrisBoard.getCols()-1), new Tile(Color.YELLOW, 'y'));
        tetrisBoard.set(new Coordinate(tetrisBoard.getRows()-1,0), new Tile(Color.RED, 'r'));
        tetrisBoard.set(new Coordinate(tetrisBoard.getRows()-1, tetrisBoard.getCols()-1), new Tile(Color.BLUE, 'b'));
    }

    @Override
    public int getRows() {
        return tetrisBoard.getRows();
    }

    @Override
    public int getCols() {
        return tetrisBoard.getCols();
    }

    @Override
    public Iterable<CoordinateItem<Tile>> iterable() {
        return tetrisBoard;
    }
    
}
