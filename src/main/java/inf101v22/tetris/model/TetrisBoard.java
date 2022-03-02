package inf101v22.tetris.model;

import inf101v22.grid.CoordinateItem;
import inf101v22.grid.Grid;

/**
 * This class represents a board in the game tetris, the board is represented by a grid of {@Tile}-objects.
 * Extendes {@link Grid} with the type parameter {@link Tile}
 * 
 * @author Espen Lilleengen 
 */
public class TetrisBoard extends Grid<Tile>{

    /**
     * Constructs a tetris board with the given dimentions and with the initial value of null.
     * 
     * @param rows the number of rows of the board
     * @param cols the number of columns of the board
     */
    public TetrisBoard(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Constructs a tetris board with the given dimentions 
     * 
     * @param rows the number of rows of the board
     * @param cols the number of columns of the board
     * @param initialValue the initial value of the tiles in the board
     */
    public TetrisBoard(int rows, int cols, Tile initialValue) {
        super(rows, cols, initialValue);
    }

    /**
     * @return a 2 dimentional array of characters that repsent the given tetris board
     */
    public char[][] toCharArray2d() {
        char[][] charArray2d = new char[getRows()][getCols()];

        for (CoordinateItem<Tile> coordinateItem : this) {
            if (coordinateItem.item==null) {
                charArray2d[coordinateItem.getRow()][coordinateItem.getCol()] = '-';
            }
            else {
                charArray2d[coordinateItem.getRow()][coordinateItem.getCol()] = coordinateItem.item.character;
            }
        }
        return charArray2d;    
    }

    /**
     * @param charArray2d a 2 dimentional array of characters 
     * @return a string where every row in the 2 dimentional array of characters is merged
     */
    public static String charArray2dToString(char[][] charArray2d) {
        String charArrayAsString = "";

        for (int row = 0; row < charArray2d.length; row++) {
            for (int col = 0; col < charArray2d[row].length; col++) {
                charArrayAsString += charArray2d[row][col];
            }
            charArrayAsString += "\n";
        }

        return charArrayAsString;
    }

}
