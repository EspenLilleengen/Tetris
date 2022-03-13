package inf101v22.tetris.model;

import java.awt.Color;

import inf101v22.grid.Coordinate;
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

    private PositionedPiece positionedPiece;
    private PositionedPieceFactory positionedPieceFactory;

    private GameScreen gameScreen;

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
        positionedPiece= positionedPieceFactory.getNextPositionedPiece();
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

        positionedPieceFactory = new PositionedPieceFactory();
        positionedPieceFactory.setCenterColumn(getCols()/2);
        positionedPiece = positionedPieceFactory.getNextPositionedPiece();
        gameScreen = GameScreen.ACTIVE_GAME;
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
        for (CoordinateItem<Tile> coordinateItem : positionedPiece) {
            charArray2d[coordinateItem.getRow()][coordinateItem.getCol()] = coordinateItem.item.character;
        }

        return charArray2d;
    }

    @Override
    public Iterable<CoordinateItem<Tile>> pieceIterable() {
        return positionedPiece; 
    }

    @Override
    public boolean moveFallingPiece(int deltaRow, int deltaCol) {
        PositionedPiece candidate = positionedPiece.copyTo(deltaRow, deltaCol);
    
        if (positionedPieceLegality(candidate)) {
            this.positionedPiece = candidate;
            return true;
        }
        return false;
            
    }

    @Override
    public boolean rotateFallingPiece() {
        PositionedPiece candidate = positionedPiece.rotate();

        if (positionedPieceLegality(candidate)) {
            this.positionedPiece = candidate;
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
        fixFallingPiece();
    }

    private void getNewPiece() {
        PositionedPiece newPiece = positionedPieceFactory.getNextPositionedPiece();
        if (!(positionedPieceLegality(newPiece))) { 
            gameScreen = GameScreen.GAME_OVER;
            return;
        }
        this.positionedPiece = newPiece;
    }

    private void fixFallingPiece() {
        for (CoordinateItem<Tile> cItem : positionedPiece) {
            tetrisBoard.set(cItem.coordinate, cItem.item);
        }
        getNewPiece();
    }

    @Override
    public GameScreen getGameScreen() {
        return gameScreen;
    }
}
