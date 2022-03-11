package inf101v22.tetris.model.piece;

import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.Tile;
import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/** 
 * Testing the class {@link positionedPiece}
*/
public class positionedPieceTest {

    @Test
    void testPositionedPieceGetMethods() { 
        PositionedPiece positionPiece = new PositionedPiece(PieceShape.T, new Coordinate(0,0));
        Tile testTile = new Tile(Color.GREEN, 'T');

        assertEquals(2, positionPiece.getHeight());
        assertEquals(3, positionPiece.getWidth());
        assertEquals(testTile, positionPiece.getTile());
    }

    @Test
    void testPositionedPieceIterator() { 
        PositionedPiece positionPiece = new PositionedPiece(PieceShape.T, new Coordinate(0,0));
        Tile testTile = new Tile(Color.GREEN, 'T');

        List<CoordinateItem<Tile>> items = new ArrayList<>();
        for (CoordinateItem<Tile> coordinateItem : positionPiece) {
            items.add(coordinateItem);
        }

        assertEquals(4, items.size());

        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0, 0), testTile )));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0, 1), testTile )));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0, 2), testTile )));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(1, 1), testTile )));
    }

    @Test
    void testCopyTo() {
        PositionedPiece testPiece = new PositionedPiece(PieceShape.O, new Coordinate(0,0));
        PositionedPiece copyPiece = testPiece.copyTo(1,1);

        assertEquals(new PositionedPiece(PieceShape.O, new Coordinate(1,1)), copyPiece);
        assertFalse(testPiece.equals(copyPiece));
    }
    
}
