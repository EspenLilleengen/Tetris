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

    /**
     * Sets the game screen to the given argument 
     * @param gameScreen the screen status to be set
    */
    void setGameScreen(GameScreen gameScreen);

    /** @return the numbber of milliseconds between each timer action*/
    int getDelay();

    /** Moves the falling piece 1 row down for every timer action. 
     * If the the piece cannot move down it wil be fixed in place.
    */
    void clockTick();

    /** Holds the cuurent tetris piece and swaps the active piece with the held piece.
     * If no piece is held, the active piece is replaced with the next piece.
     */
    void holdPiece();

     /** @return true if the hold action is not blocked and false otherwise */
    boolean getHoldAction();

    /** Sets {@link holdActionActive} to false. This blocks the hold function from being used*/
    void blockHoldAction();

    /** Sets {@link holdActionActive} to true. This allows the hold function to be used*/
    void unblockHoldAction();

    /**Resets the teris boards and all the game data */
    void resetBoard();
    
}
