package inf101v22.tetris.controller;

/** 
 * An object of this type alows a tetris piece to be moved around on a board.
 * 
 * @author Espen Lilleengen
*/
public interface TetrisControllable {

    /** 
     * Moves a tetris piece in the given row and column direction. 
     * 
     * @param deltaRow where to move the piece along the row
     * @param deltaColumn where to move the piece along the column
     * @return true if the move was successful and false otherwise
    */
    boolean moveFallingPiece(int deltaRow, int deltaCol);
    
}
