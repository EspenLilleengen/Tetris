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

    @Test
    void rotateLeftTest() {
        PieceShape Lpiece = PieceShape.L;
        PieceShape Tpiece = PieceShape.T;
        PieceShape Ipiece = PieceShape.I;
        PieceShape Zpiece = PieceShape.Z;

        PieceShape rotatedL = Lpiece.rotateLeft();
        PieceShape rotatedT = Tpiece.rotateLeft();
        PieceShape rotatedI = Ipiece.rotateLeft();
        PieceShape rotatedZ = Zpiece.rotateLeft();

        boolean[][] expectedL = new boolean[][] {
            {true,true},
            {false,true},
            {false, true},
        };

        boolean[][] expectedT = new boolean[][] {
            {true,false},
            {true,true},
            {true, false},
        };

        boolean[][] expectedI = new boolean[][] {
            {true},
            {true},
            {true},
            {true},
        };

        boolean[][] expectedZ = new boolean[][] {
            {false,true},
            {true,true},
            {true, false},
        };


        assertEquals(expectedL.length, rotatedL.getHeight());
        assertEquals(expectedL[0].length, rotatedL.getWidth());
        assertEquals(expectedT.length, rotatedT.getHeight());
        assertEquals(expectedT[0].length, rotatedT.getWidth());
        assertEquals(expectedI.length, rotatedI.getHeight());
        assertEquals(expectedI[0].length, rotatedI.getWidth());
        assertEquals(expectedZ.length, rotatedZ.getHeight());
        assertEquals(expectedZ[0].length, rotatedZ.getWidth());

        for (int i = 0; i <3; i++) {
            for (int j = 0; j <2; j++) {
                assertEquals(expectedL[i][j],rotatedL.getShape()[i][j]);
                assertEquals(expectedT[i][j],rotatedT.getShape()[i][j]);
                assertEquals(expectedZ[i][j],rotatedZ.getShape()[i][j]);
            }
        }

        for (int i = 0; i <3;i++) {
            assertEquals(expectedI[i][0],rotatedI.getShape()[i][0]);
        }
    }

    @Test
    void rotateRightTest() {
        PieceShape rotatedL = PieceShape.L.rotateRight();

        boolean[][] expectedL = new boolean[][] {
            {true,false},
            {true,false},
            {true, true},
        };

        assertEquals(expectedL.length, rotatedL.getHeight());
        assertEquals(expectedL[0].length, rotatedL.getWidth());

        for (int i = 0; i <3; i++) {
            for (int j = 0; j <2; j++) {
                assertEquals(expectedL[i][j],rotatedL.getShape()[i][j]);
            }
        }
    }

    @Test
    void rotateMultipleTimesTest() {
        PieceShape rotated1 = PieceShape.L.rotateLeft();
        PieceShape rotated2 = rotated1.rotateLeft();
        PieceShape rotated3 = rotated2.rotateLeft();
        PieceShape rotated4 = rotated3.rotateLeft();

        boolean[][] expected1 = new boolean[][] {
            {true,true},
            {false,true},
            {false, true},
        };

        boolean[][] expected2 = new boolean[][] {
            {true,true, true},
            {true,false,false},
        };

        boolean[][] expected3 = new boolean[][] {
            {true,false},
            {true,false},
            {true, true},
        };

        for (int i = 0; i <3; i++) {
            for (int j = 0; j <2; j++) {
                assertEquals(expected1[i][j], rotated1.getShape()[i][j]);
                assertEquals(expected3[i][j], rotated3.getShape()[i][j]);
            }
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j<3; j++) {
                assertEquals(expected2[i][j], rotated2.getShape()[i][j]);
            }
        }
        assertEquals(PieceShape.L, rotated4);
    }
}
