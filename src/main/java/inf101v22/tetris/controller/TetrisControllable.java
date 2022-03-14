package inf101v22.tetris.controller;

import inf101v22.tetris.model.GameScreen;

/** 
 * An object of this type alows a tetris piece to be moved around on a board.
 * 
 * @author Espen Lilleengen
*/
public interface TetrisControllable {

    /** 
     * Moves a falling tetris piece in the given row and column direction. 
     * 
     * @param deltaRow where to move the piece along the row
     * @param deltaColumn where to move the piece along the column
     * @return true if the move was successful and false otherwise
    */
    boolean moveFallingPiece(int deltaRow, int deltaCol);

    /**
     * Rotates a falling tetris piece
     * 
     * @return true if the piece can rotate and false otherwise
     */
    boolean rotateFallingPiece();

    /**
     * Moves a falling tetris piece down until it reaches an obstacle. 
     * When a piece has been droppd, its position will be fixed
    */
    void dropFallingPiece();

    /**@return the game screen status of the object */
    GameScreen getGameScreen();

    /** @return the numbber of milliseconds between each timer action*/
    int getDelay();

    /** Moves the falling piece 1 row down for every timer action. 
     * If the the piece cannot move down it wil be fixed in place.
    */
    void clockTick();
    
}
