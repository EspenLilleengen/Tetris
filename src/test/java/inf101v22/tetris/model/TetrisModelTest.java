package inf101v22.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
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

        String testString = "----TTT---\n-----T----\n";
        for (int i = 0; i <13; i++) {
            testString += "----------\n";
        }

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
        for (CoordinateItem<Tile> item : model.activePieceIterable()) { 
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

        String testString = "----------\n";

        for (int i = 0; i <12; i++) {
            testString += "----------\n";
        }

        testString += "----TTT---\n-----T----\n";

        assertEquals(testString, modelString);
    }

    @Test
    void getGameScreenTest() {
        TetrisModel testModel = new TetrisModel();

        assertEquals(GameScreen.WELCOME, testModel.getGameScreen());
    }

    @Test
    void dropFallingPieceTest() {
        TetrisModel model = new TetrisModel(new PositionedPieceFactory(PositionedPieceFactoryTest.positionedPieces));

        model.dropFallingPiece();

        model.moveFallingPiece(0, 1);
        model.moveFallingPiece(0, 1);

        String modelString1 = TetrisBoard.charArray2dToString(Arrays.copyOfRange(model.addPieceToCharArray2d(), 13, 15));
        String modelString2 = TetrisBoard.charArray2dToString(Arrays.copyOfRange(model.addPieceToCharArray2d(), 0, 2));

        String expectedString1 = "----TTT---\n-----T----\n";
        String expectedString2 = "------OO--\n------OO--\n";

        assertEquals(expectedString1, modelString1);
        assertEquals(expectedString2, modelString2);
    }

    @Test
    void getTimeIntervalTest() {
        TetrisModel model = new TetrisModel();

        assertEquals(2000, model.getDelay());

        model.dropFallingPiece();
        model.dropFallingPiece();

        int delay =  (int) (2000 * Math.pow(0.98,2));

        assertEquals(delay, model.getDelay());
    }

    @Test
    void clockTickTest() {
        TetrisModel model = new TetrisModel(new PositionedPieceFactory(PositionedPieceFactoryTest.positionedPieces));
        for (int i = 0; i<15;i++) {
            model.clockTick();
        }

        String modelString = TetrisBoard.charArray2dToString(Arrays.copyOfRange(model.addPieceToCharArray2d(), 13, 15));
        String expectedString = "----TTT---\n-----T----\n";

        assertEquals(modelString, expectedString);
    }

    @Test
    void holdPieceTest() {
        TetrisModel model = new TetrisModel(new PositionedPieceFactory(PositionedPieceFactoryTest.positionedPieces));
        model.holdPiece();

        assertEquals(PositionedPieceFactoryTest.positionedPieces[0], model.heldPieceIterable());
        assertEquals(PositionedPieceFactoryTest.positionedPieces[1], model.activePieceIterable());
        assertEquals(PositionedPieceFactoryTest.positionedPieces[2], model.nextPieceIterable());

        model.dropFallingPiece();
        model.holdPiece();

        assertEquals(PositionedPieceFactoryTest.positionedPieces[2], model.heldPieceIterable());
        assertEquals(PositionedPieceFactoryTest.positionedPieces[0], model.activePieceIterable());
        assertEquals(PositionedPieceFactoryTest.positionedPieces[3], model.nextPieceIterable());
    }

}
