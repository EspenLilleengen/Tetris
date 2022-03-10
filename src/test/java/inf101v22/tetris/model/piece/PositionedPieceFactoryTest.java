package inf101v22.tetris.model.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import inf101v22.grid.Coordinate;

/**Testing the class {@link PositionedPieceFactory} */
public class PositionedPieceFactoryTest {
    
    public static final PositionedPiece[] positionedPieces = { 
        new PositionedPiece(PieceShape.T, new Coordinate(0,4)), 
        new PositionedPiece(PieceShape.O, new Coordinate(0,4)), 
        new PositionedPiece(PieceShape.I, new Coordinate(0,3)),
    };

    @Test
    void getNextPositionedPieceTest() { 
        PositionedPieceFactory factory = new PositionedPieceFactory(positionedPieces);

        assertEquals( new PositionedPiece(PieceShape.T, new Coordinate(0,4)), factory.getNextPositionedPiece());
        assertEquals( new PositionedPiece(PieceShape.O, new Coordinate(0,4)), factory.getNextPositionedPiece());
        assertEquals( new PositionedPiece(PieceShape.I, new Coordinate(0,3)), factory.getNextPositionedPiece());
    }
}
