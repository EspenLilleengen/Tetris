package inf101v22.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
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
        TetrisModel model = new TetrisModel();

        String testString = "g--------y\n";
        for (int i = 0; i <13; i++) {
            testString += "----------\n";
        }
        testString += "r--------b\n";

        String modelString = TetrisBoard.charArray2dToString(model.tetrisBoard.toCharArray2d());

        assertEquals(testString, modelString);

    }

    @Test
    void testIterator() {
        TetrisViewable model = new TetrisModel();

        List<CoordinateItem<Tile>> items = new ArrayList<>();
        for (CoordinateItem<Tile> coordinateItem : model.iterable()) {
            items.add(coordinateItem);
        }

        assertEquals(15 * 10, items.size());
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0, 0), new Tile(Color.GREEN, 'g'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(14,0), new Tile(Color.RED, 'r'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(0,9), new Tile(Color.YELLOW, 'y'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(14,9), new Tile(Color.BLUE, 'b'))));
        assertTrue(items.contains(new CoordinateItem<Tile>(new Coordinate(1,1), null)));
    }
}
