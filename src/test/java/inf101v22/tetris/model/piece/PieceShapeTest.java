package inf101v22.tetris.model.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.Tile;

/**Testing the class {@link PieceShape} */
public class PieceShapeTest {

    @Test
    void testGetMethods() { 
        PieceShape piece = PieceShape.T;
        boolean[][] testShape= {
            {true, true, true},
            {false, true, false}};

        assertEquals(2,piece.getHeight());
        assertEquals(3, piece.getWidth());
        assertEquals(new Tile(Color.GREEN, 'T'), piece.getTile());
        for (int i = 0; i <piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(); j++) {
                assertEquals(testShape[i][j], piece.getShape()[i][j]);
            }
        }
    }
}
