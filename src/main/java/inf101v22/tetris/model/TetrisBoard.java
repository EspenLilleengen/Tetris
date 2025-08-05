package inf101v22.tetris.model;

import inf101v22.grid.Coordinate;
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
    TetrisBoard(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Constructs a tetris board with the given dimentions 
     * 
     * @param rows the number of rows of the board
     * @param cols the number of columns of the board
     * @param initialValue the initial value of the tiles in the board
     */
    TetrisBoard(int rows, int cols, Tile initialValue) {
        super(rows, cols, initialValue);
    }

    /**
     * @return a 2 dimentional array of characters that repsent the given tetris board
     */
    char[][] toCharArray2d() {
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
    static String charArray2dToString(char[][] charArray2d) {
        String charArrayAsString = "";

        for (int row = 0; row < charArray2d.length; row++) {
            for (int col = 0; col < charArray2d[row].length; col++) {
                charArrayAsString += charArray2d[row][col];
            }
            charArrayAsString += "\n";
        }

        return charArrayAsString;
    }

    /**
     * Removes all the full rows from the board
     * 
     * @return the number of rows removed from the board
     */
    int removeFullRows() {
        int rowsRemoved = 0;

        int a = getRows() -1;
        int b = getRows() -1;

        while(a>=0) {
            while (b>=0 && (!checkElementInRow(b, null))) {
                rowsRemoved++;
                b--;
            }
            if (b>=0 && rowsRemoved>0) {
                moveRow(b, a);
            } else if (rowsRemoved>0) {
                fillRow(a, null);
            }
            a--;
            b--; 
        }
        return rowsRemoved;
    }

    private <E> boolean checkElementInRow(int row, E elem) {

        for (int i = 0; i<getCols(); i++ ) {
            if (get(new Coordinate(row,i))==elem) {
                return true;
            } else if (get(new Coordinate(row,i)).equals(elem)) {
                return true;
            }
        }
        return false;
    }

    private void fillRow(int row, Tile tile) {
        for (int i = 0; i < getCols(); i++) {
            set(new Coordinate(row,i), tile);
        }
    }

    private void moveRow(int fromRow, int toRow) {
        for (int i = 0; i<getCols(); i++) {
            set(new Coordinate(toRow,i), get(new Coordinate(fromRow,i)));
        }
    }
}
