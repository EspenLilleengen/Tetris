package inf101v22.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import inf101v22.grid.Coordinate;
import inf101v22.grid.IGrid;

/**
 * Testing the class {@link TetrisBoard}
 */
public class TetrisBoardTest {

    @Test
    void boardSanityTest() {
        Tile defaultValue = new Tile(Color.BLACK, 'b');
        IGrid<Tile> board = new TetrisBoard(3, 2, defaultValue);

        assertEquals(3, board.getRows());
        assertEquals(2, board.getCols());

        assertTrue(defaultValue.equals(board.get(new Coordinate(0, 0))));
        assertTrue(defaultValue.equals(board.get(new Coordinate(2,1))));
        assertFalse(new Tile(Color.BLACK, 'e').equals(board.get(new Coordinate(1,1))));
        assertFalse(new Tile(Color.GRAY, 'b').equals(board.get(new Coordinate(0,1))));

        board.set(new Coordinate(1, 1), new Tile(Color.YELLOW, 'y'));

        assertTrue(new Tile(Color.YELLOW, 'y').equals(board.get(new Coordinate(1,1))));
        assertTrue(defaultValue.equals(board.get(new Coordinate(0, 1))));
        assertTrue(defaultValue.equals(board.get(new Coordinate(1, 0))));
        assertTrue(defaultValue.equals(board.get(new Coordinate(2, 1))));
    }

    @Test
    void toCharArray2dTest(){
        TetrisBoard board = new TetrisBoard(4,4);
        char[][] charArray2d = board.toCharArray2d();
        charArray2d[2][1]='t';
        charArray2d[3][3]='b';

        assertEquals(4, charArray2d.length);

        for(int row = 0; row < 3; row++){
            assertEquals(4, charArray2d[row].length);
            for (int col = 0; col < 3; col++){
                if (row == 2 && col == 1) {
                    assertEquals('t', charArray2d[row][col]);
                } else if (row == 3 && col == 3) {
                    assertEquals('b', charArray2d[row][col]);
                } else {
                    assertEquals('-', charArray2d[row][col]);
                }
            }
        }
    }

    @Test
    void charArray2dToStringTest() {
        TetrisBoard board = new TetrisBoard(3,3);
        char[][] charArray2d = board.toCharArray2d();
        charArray2d[0][0] = 'b';
        charArray2d[1][1] = 'y';
        charArray2d[2][2] = 'g';

        String charArrayAsString = TetrisBoard.charArray2dToString(charArray2d);

        String testString = "b--\n-y-\n--g\n";

        assertTrue(testString.equals(charArrayAsString));
    }
}
