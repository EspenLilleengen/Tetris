package inf101v22.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.Objects;

import org.junit.jupiter.api.Test;

/**
 * Testing the class {@link Tile}
 */
public class TileTest {

    @Test
    void tileSanityTest() {
        Tile t = new Tile(Color.BLUE, 'b');
        assertEquals(Color.BLUE, t.color);
        assertEquals('b', t.character);
    }

    @Test
    void tileEqualityTest() {
        Tile a = new Tile(Color.WHITE, 'w');
        Tile b = new Tile(Color.WHITE, 'w');

        assertFalse(a == b);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(Objects.equals(a, b));
    }

    @Test
    void tileInequalityTest() {
        Tile a = new Tile(Color.WHITE, 'w');
        Tile b = new Tile(Color.BLACK, 'b');

        assertFalse(a == b);
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
        assertFalse(Objects.equals(a, b));
    }
}
