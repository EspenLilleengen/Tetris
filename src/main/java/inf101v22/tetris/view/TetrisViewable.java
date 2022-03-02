package inf101v22.tetris.view;

import inf101v22.grid.CoordinateItem;
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
     * @return An iterator over a collection of {@link CoordinateItem} with the type parameter {@link Tile}.
     */
    Iterable<CoordinateItem<Tile>> iterable();
}
