package inf101v22.tetris.model;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.controller.TetrisControllable;
import inf101v22.tetris.model.piece.PositionedPiece;
import inf101v22.tetris.model.piece.PositionedPieceFactory;
import inf101v22.tetris.model.scoreboard.Score;
import inf101v22.tetris.model.scoreboard.ScoreBoard;
import inf101v22.tetris.view.TetrisViewable;

/**
 * A model for a game of Teris. Implements {@link TetrisViewable} and {@link TetrisControllable}.
 * q
 * 
 * @author Espen Lilleengen
 */
public class TetrisModel implements TetrisViewable, TetrisControllable{

    private TetrisBoard tetrisBoard;

    private PositionedPiece activePiece;
    private PositionedPiece originalActivePiece;
    private PositionedPiece nextPiece;
    private PositionedPiece heldPiece;
    private PositionedPiece ghostPiece;
    private PositionedPieceFactory positionedPieceFactory;
    private boolean isHoldPossible = true;

    private GameScreen gameScreen;
    private final ScoreBoard scoreBoard;

    private final int initialDelay = 2000;
    private int numPieces = 0;
    private int scoreCount = 0;

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
        updateGhostPiece();
    }

    /**
     * Constuct a tetris model that holds a {@link TetrisBoard}-object with the given dimensions
     * @param rows the number of rows of the tetris board
     * @param cols the number of columns of the tetris board
     */
    public TetrisModel(int rows, int cols) {
        tetrisBoard = new TetrisBoard(rows,cols);
        positionedPieceFactory = new PositionedPieceFactory();
        positionedPieceFactory.setCenterColumn(getCols()/2);
        activePiece = positionedPieceFactory.getNextPositionedPiece();
        updateGhostPiece();
        originalActivePiece=activePiece;
        nextPiece = positionedPieceFactory.getNextPositionedPiece();
        gameScreen = GameScreen.WELCOME;
        scoreBoard = new ScoreBoard("src/main/java/inf101v22/tetris/model/scoreboard/ScoreBoardData.txt");
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
    char[][] addPieceToCharArray2d() {
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
        PositionedPiece candidate = activePiece.movedCopy(deltaRow, deltaCol);
        return assignCanddidate(candidate);     
    }

    @Override
    public void rotateFallingPieceLeft() {
        PositionedPiece candidate = activePiece.rotateLeft();
        bounceOfWallsIfNeeded(candidate);
    }

    @Override
    public void rotateFallingPieceRight() {
        PositionedPiece candidate = activePiece.rotateRight();
        bounceOfWallsIfNeeded(candidate);
    }

    private void bounceOfWallsIfNeeded(PositionedPiece candidate) {
        if (assignCanddidate(candidate)) {
            return;
        }
       for (int i = 0; i>-2; i--) {
            if (bounceOfRoofIfNeeded(i, candidate))
                return;
        }
        for (int i = 0; i<3; i++) {
            if (bounceOfRoofIfNeeded(i, candidate))
                return;
        }
    }

    private boolean bounceOfRoofIfNeeded(int i, PositionedPiece candidate) {
        for (int j = 0; j<3; j++) {
            PositionedPiece temp = candidate.movedCopy(j, i);
            if (assignCanddidate(temp)) {
                return true;
            }
        }
        return false;
    }

    private boolean assignCanddidate(PositionedPiece candidate) {
        if (checkPositionedPieceLegality(candidate)) {
            activePiece = candidate;
            updateGhostPiece();
            return true;
        }
        return false;
    }

    /** @return true if the coordinates of the piece is legal and false otherwise*/
    private boolean checkPositionedPieceLegality(PositionedPiece candidate) {
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
        if (!(checkPositionedPieceLegality(newPiece))) { 
            gameScreen = GameScreen.GAME_OVER;
            return;
        }
        activePiece = nextPiece;
        nextPiece = newPiece;
        originalActivePiece = activePiece;
        numPieces++;
        unblockHoldAction();
        updateGhostPiece();
    }

    private void fixFallingPiece() {
        for (CoordinateItem<Tile> cItem : activePiece) {
            tetrisBoard.set(cItem.coordinate, cItem.item);
        }
    }

    private void landPiece() {
        fixFallingPiece();
        int rowsRemoved = tetrisBoard.removeFullRows();
        scoreCount+= rowsRemoved*rowsRemoved;
        getNewPiece();
    }

    @Override
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    @Override
    public int getDelay() {
        return (int) (initialDelay* Math.pow(0.985, numPieces));
    }

    @Override
    public void timerTick() {
        if (!moveFallingPiece(1, 0)) {
            landPiece();
        }
    }

    @Override
    public int getScore() {
        return scoreCount;
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
        updateGhostPiece();
    }

    @Override
    public boolean getHoldAction() {
        return isHoldPossible;
    }

    @Override
    public void blockHoldAction() {
        isHoldPossible = false;
    }

    @Override
    public void unblockHoldAction() {
        isHoldPossible = true;
    }

    @Override
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen=gameScreen;
    }

    @Override
    public void resetBoard() {
        tetrisBoard = new TetrisBoard(15,10);
        scoreCount=0;
        numPieces=0;
        heldPiece=null;
        activePiece = positionedPieceFactory.getNextPositionedPiece();
        originalActivePiece=activePiece;
        nextPiece = positionedPieceFactory.getNextPositionedPiece();
        isHoldPossible=true;
        updateGhostPiece();
    }

    @Override
    public Iterable<CoordinateItem<Tile>> ghostPieceIterable() {
        return ghostPiece;
    }

    private void updateGhostPiece() {
        PositionedPiece candidate = activePiece;
        ghostPiece = candidate;
        while(true) {
            candidate = candidate.movedCopy(1, 0);
            if (checkPositionedPieceLegality(candidate)) {
                ghostPiece = candidate;
                continue;
            }
            break;
        }
    }

    @Override
    public Iterable<Score> scoreIterable() {
        return scoreBoard;
    }

    @Override
    public void submitScore(String name) {
        scoreBoard.add(new Score(scoreCount, name));
    }

}