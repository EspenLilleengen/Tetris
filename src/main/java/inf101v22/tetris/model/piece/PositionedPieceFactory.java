package inf101v22.tetris.model.piece;

import java.util.Random;

import inf101v22.grid.Coordinate;

/** 
 * An object of this class can be used to create new {@link PositionedPiece}-objects 
 * 
 * @author Espen Lilleengen
*/
public class PositionedPieceFactory {

    private int centerCol;
    private PositionedPiece[] testList;
    private int testListIndex;

    /** Creates a new {@link PositionedPieceFactory}-object. */
    public PositionedPieceFactory() {}

    /** 
     * Construct a new {@link PositionedPieceFactory}-object for testing purposes.
     * @param testList the list the {@link PositionedPiece}-object will be choosen from
     */
    public PositionedPieceFactory(PositionedPiece[] testList) {
        this.testList = testList;
        testListIndex = 0;
    }
    
    /** 
     * Sets the center collumn to the given argument
     * @param centerCol the column to be set as the center column 
    */
    public void setCenterColumn(int centerCol) { 
        this.centerCol = centerCol;
    }

    /**
     * Creates a new random {@link PieceShape}-object and construct a new {@link PositionedPiece}-object
     * based on the {@link PieceShape}-object. 
     * <p>
     * If this method is used for testing purposes, it will choose the {@link PositionedPiece}-object from a 
     * list that was given to the constructor
     * 
     * @return a new {@link PositionedPiece}-object
     */
    public PositionedPiece getNextPositionedPiece() {
        if (testList!=null && testList.length>testListIndex) {
            testListIndex++;
            return testList[testListIndex-1];
        }

        Random r = new Random();
        PieceShape pieceShape = PieceShape.STANDARD_TETRIS_PIECES[r.nextInt(PieceShape.STANDARD_TETRIS_PIECES.length)];
        int topLeftCol= centerCol - pieceShape.getWidth()/2;
        Coordinate coordinate = new Coordinate(0,topLeftCol);

        return new PositionedPiece(pieceShape, coordinate);
    }
}
