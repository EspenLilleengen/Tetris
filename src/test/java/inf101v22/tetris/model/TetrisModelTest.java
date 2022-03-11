package inf101v22.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.piece.PositionedPieceFactory;
import inf101v22.tetris.model.piece.PositionedPieceFactoryTest;
import inf101v22.tetris.view.TetrisViewable;

/**
 * Testing the class {@link TetrisModel}
 */
public class TetrisModelTest {

    @Test
    void getRowsandColsTest () {
        TetrisViewable model = new TetrisModel();
        assertEquals(15, model.getRows());
        assertEquals(10,model.getCols());
    }

    @Test
    void modelSanityTest() {
        TetrisModel model = new TetrisModel(new PositionedPieceFactory(PositionedPieceFactoryTest.positionedPieces));

        String testString = "g---TTT--y\n-----T----\n";
        for (int i = 0; i <12; i++) {
            testString += "----------\n";
        }
        testString += "r--------b\n";

        String modelString = TetrisBoard.charArray2dToString(model.addPieceToCharArray2d());

        assertEquals(testString, modelString);
    }
    

    @Test
    void testBoardIterator() {
        TetrisViewable model = new TetrisModel();

        List<CoordinateItem<Tile>> items = new ArrayList<>();
        for (CoordinateItem<Tile> coordinateItem : model.boardIterable()) {
            items.add(coordinateItem);
        }

        assertEquals(15 * 10, items.size());
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0, 0), new Tile(Color.GREEN, 'g'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(14,0), new Tile(Color.RED, 'r'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0,9), new Tile(Color.YELLOW, 'y'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(14,9), new Tile(Color.BLUE, 'b'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(1,1), null)));
    }

    @Test
    void testPieceIterable() {
        TetrisModel model = new TetrisModel(new PositionedPieceFactory(PositionedPieceFactoryTest.positionedPieces));

        List<CoordinateItem<Tile>> items = new ArrayList<>();
        for (CoordinateItem<Tile> item : model.pieceIterable()) { 
            items.add(item);
        }

        assertEquals(4, items.size());
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0, 4), new Tile(Color.GREEN, 'T'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0,5), new Tile(Color.GREEN, 'T'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0,6), new Tile(Color.GREEN, 'T'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(1,5), new Tile(Color.GREEN, 'T'))));
    }

    @Test
    void testMoveFallingPiece() { 
        TetrisModel model = new TetrisModel(new PositionedPieceFactory(PositionedPieceFactoryTest.positionedPieces));

        assertFalse(model.moveFallingPiece(-1, 0));
        assertTrue(model.moveFallingPiece(0,1));
        assertTrue(model.moveFallingPiece(1,0));
        assertTrue(model.moveFallingPiece(0,-1));
        assertTrue(model.moveFallingPiece(-1,0));
        assertFalse(model.moveFallingPiece(-1,0));
        while(model.moveFallingPiece(1, 0));

        String modelString = TetrisBoard.charArray2dToString(model.addPieceToCharArray2d());

        String testString = "g--------y\n";

        for (int i = 0; i <12; i++) {
            testString += "----------\n";
        }

        testString += "----TTT---\nr----T---b\n";

        assertEquals(testString, modelString);
    }
}
