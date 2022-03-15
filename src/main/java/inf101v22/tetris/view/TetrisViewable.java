package inf101v22.tetris.view;


import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.Tile;

/**
 * Implementing this interface allows an object to be used to draw a tetris board by {@link TetrisView}
 * 
 * @author Espen Lilleengen
 */
public interface TetrisViewable {
    
    /** @return rumber of rows on the board */
    int getRows();

    /** @return number of columns on the board */
    int getCols();

    /**
     * An iterator over the tiles in a Tetris board
     * @return An iterable of {@link CoordinateItem}-objects with the type parameter {@link Tile}.
     */
    Iterable<CoordinateItem<Tile>> boardIterable();

    /** 
     * An iterator over the coordinates in the current Tetris-piece 
     * @return An iterable of {@link CoordinateItem}-objects with the type parameter {@link Tile}.
    */
    Iterable<CoordinateItem<Tile>> pieceIterable();

    /**@return the game screen status of the object */
    GameScreen getGameScreen();

    /**@return the score at the cuurent state of the game */
    int getScore();

    /**
     * An iterator over the coordinates in the next Tetris-piece 
     * @return An iterable of {@link CoordinateItem}-objects with the type parameter {@link Tile}.
     */
    Iterable<CoordinateItem<Tile>> nextPieceIterable();

}
