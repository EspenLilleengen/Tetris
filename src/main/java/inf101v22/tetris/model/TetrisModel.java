package inf101v22.tetris.model;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.controller.TetrisControllable;
import inf101v22.tetris.model.piece.PositionedPiece;
import inf101v22.tetris.model.piece.PositionedPieceFactory;
import inf101v22.tetris.view.TetrisViewable;

/**
 * A class that implements {@link TetrisViewable} and {@link TetrisControllable}. Holds a {@link TetrisBoard}-object 
 * 
 * @author Espen Lilleengen
 */
public class TetrisModel implements TetrisViewable, TetrisControllable{

    private TetrisBoard tetrisBoard;

    private PositionedPiece activePiece;
    private PositionedPiece originalActivePiece;
    private PositionedPiece nextPiece;
    private PositionedPiece heldPiece;
    private PositionedPieceFactory positionedPieceFactory;
    private boolean holdActionActive = true;

    private GameScreen gameScreen;

    private final int initialDelay = 2000;
    private int numPieces = 0;
    private int score = 0;

    /**
     * Construct a tetris model that holds a {@link TetrisBoard}-object with the width of 10 tiles an a height of 15 tiles
     */
    public TetrisModel() {
        this(15,10);
    }

    /** 
     * Construct a tetris model that holds a {@link TetrisBoard}-object with the width of 10 tiles an a height of 15 tiles
     * This constructor is used for testing the class 
     * @param positionedPieceFactory the positionedPieceFactory-object used to get new positionedPiece-objects
    */
    public TetrisModel(PositionedPieceFactory positionedPieceFactory) {
        this(15,10);
        this.positionedPieceFactory=positionedPieceFactory;
        activePiece = positionedPieceFactory.getNextPositionedPiece();
        originalActivePiece=activePiece;
        nextPiece = positionedPieceFactory.getNextPositionedPiece();
    }

    /**
     * Constuct a tetris model that holds a {@link TetrisBoard}-object with the given dimensions
     * @param rows the number of rows of the tetris board
     * @param cols the number of columns of the tetris board
     */
    public TetrisModel(int rows, int cols) {
        this.tetrisBoard = new TetrisBoard(rows,cols);
        positionedPieceFactory = new PositionedPieceFactory();
        positionedPieceFactory.setCenterColumn(getCols()/2);
        activePiece = positionedPieceFactory.getNextPositionedPiece();
        originalActivePiece=activePiece;
        nextPiece = positionedPieceFactory.getNextPositionedPiece();
        gameScreen = GameScreen.WELCOME;
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
    public Iterable<CoordinateItem<Tile>> boardIterable() {
        return tetrisBoard;
    }

    /**
     * @return a 2 dimentional array of characters that repsent the given tetris board with pieces
     */
    public char[][] addPieceToCharArray2d() {
        char[][] charArray2d = tetrisBoard.toCharArray2d();
        for (CoordinateItem<Tile> coordinateItem : activePiece) {
            charArray2d[coordinateItem.getRow()][coordinateItem.getCol()] = coordinateItem.item.character;
        }

        return charArray2d;
    }

    @Override
    public Iterable<CoordinateItem<Tile>> activePieceIterable() {
        return activePiece; 
    }

    @Override
    public boolean moveFallingPiece(int deltaRow, int deltaCol) {
        PositionedPiece candidate = activePiece.copyTo(deltaRow, deltaCol);
    
        if (positionedPieceLegality(candidate)) {
            this.activePiece = candidate;
            return true;
        }
        return false;
            
    }

    @Override
    public boolean rotateFallingPiece() {
        PositionedPiece candidate = activePiece.rotate();
        if (positionedPieceLegality(candidate)) {
            this.activePiece = candidate;
            return true;
        }
        return false;
    }

    /** 
     * Chechs if the next position is legal for the candidate
     * @return true if the next position is legal and false otherwise
    */
    private boolean positionedPieceLegality(PositionedPiece candidate) {
        for (CoordinateItem<Tile> cItem : candidate) { 
            if (!tetrisBoard.coordinateIsOnGrid(cItem.coordinate)) 
                return false;
            
            if (!(tetrisBoard.get(cItem.coordinate)==null)) 
                return false;
        }
        return true;
    }

    @Override
    public void dropFallingPiece() {
        while(moveFallingPiece(1,0));
        landPiece();
    }

    private void getNewPiece() {
        PositionedPiece newPiece = positionedPieceFactory.getNextPositionedPiece();
        if (!(positionedPieceLegality(newPiece))) { 
            gameScreen = GameScreen.GAME_OVER;
            return;
        }
        this.activePiece = nextPiece;
        this.nextPiece = newPiece;
        originalActivePiece = activePiece;
        numPieces++;
        unblockHoldAction();
    }

    private void fixFallingPiece() {
        for (CoordinateItem<Tile> cItem : activePiece) {
            tetrisBoard.set(cItem.coordinate, cItem.item);
        }
    }

    private void landPiece() {
        fixFallingPiece();
        int rowsRemoved = tetrisBoard.removeFullRows();
        score+= rowsRemoved*rowsRemoved;
        getNewPiece();
    }

    @Override
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    @Override
    public int getDelay() {
        return (int) (initialDelay* Math.pow(0.98, numPieces));
    }

    @Override
    public void clockTick() {
        if (!moveFallingPiece(1, 0)) {
            landPiece();
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Iterable<CoordinateItem<Tile>> nextPieceIterable() {
        return nextPiece;
    }

    @Override
    public Iterable<CoordinateItem<Tile>> heldPieceIterable() {
        return heldPiece;
    }

    @Override
    public void holdPiece() {
        if (heldPiece==null) {
            heldPiece=originalActivePiece;
            getNewPiece();
            return;
        } 
        PositionedPiece temp = heldPiece;
        heldPiece = originalActivePiece;
        activePiece = temp;
    }

    @Override
    public boolean getHoldAction() {
        return holdActionActive;
    }

    @Override
    public void blockHoldAction() {
        holdActionActive = false;
    }

    @Override
    public void unblockHoldAction() {
        holdActionActive = true;
    }

    @Override
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen=gameScreen;
    }

    @Override
    public void resetBoard() {
        tetrisBoard = new TetrisBoard(15,10);
        score=0;
        numPieces=0;
        heldPiece=null;
        activePiece = positionedPieceFactory.getNextPositionedPiece();
        originalActivePiece=activePiece;
        nextPiece = positionedPieceFactory.getNextPositionedPiece();
        holdActionActive=true;
    }
}
