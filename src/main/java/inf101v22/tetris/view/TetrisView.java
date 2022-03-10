package inf101v22.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JComponent;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;

/**
 * This class extends {@link JComponent} and can be used to 
 * paint a tetris board
 *
 * @author Espen Lilleengen
 */
public class TetrisView extends JComponent{
    { this.setFocusable(true); }

    private final TetrisViewable viewable;

    /**
     * @param viewable - a {@link TetrisViewable}-object to get infomation about the tetrisboard from
     */
    public TetrisView( TetrisViewable viewable) {
        this.viewable = viewable;
    }

    @Override
    public void paint(Graphics canvas) {
        super.paint(canvas);
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();

        drawTetrisBoard(canvas, 4, 4, windowWidth-2*4, windowHeight-2*4, 2);
        drawTetrisPiece(canvas, 4, 4, windowWidth-2*4, windowHeight-2*4, 2);
    }

    private void drawTetrisPiece(Graphics g, int xBoard, int yBoard, int boardWidth, int boardHeight, int boardPadding) {
        int rows = viewable.getRows();
        int cols = viewable.getCols();
        boardHeight-=boardPadding;
        boardWidth-=boardPadding;

        for (CoordinateItem<Tile> cItem : viewable.pieceIterable()) {
            int row = cItem.getRow();
            int col = cItem.getCol();
            Color color = cItem.item.color;

            int tileX = xBoard + col * boardWidth/cols;
            int tileY = yBoard + row * boardHeight/rows;
            int nextX = xBoard + (col +1) * boardWidth/cols;
            int nextY = yBoard + (row +1) * boardHeight/rows;
            int tileWidth = nextX - tileX;
            int tileHeight = nextY - tileY;
            drawTileWithRightBottomPadding(g, tileX+2, tileY+2, tileWidth, tileHeight, 2, color);
        }
    }

    private void drawTetrisBoard(Graphics g, int xBoard, int yBoard, int boardWidth, int boardHeight, int boardPadding) {
        drawBoardWithRightBottomPadding(g, xBoard+2, yBoard+2, boardWidth, boardHeight, boardPadding);
    }

    private void drawBoardWithRightBottomPadding(Graphics g, int xBoard, int yBoard, int boardWidth, int boardHeight, int boardPadding) {
        int rows = viewable.getRows();
        int collums = viewable.getCols();
        boardHeight-=boardPadding;
        boardWidth-=boardPadding;

        for (CoordinateItem<Tile> cItem : viewable.boardIterable()) {
            int row = cItem.getRow();
            int col = cItem.getCol();
            Color color;
            
            if (cItem.item==null) {
                color = Color.BLACK;
            } else {
                color = cItem.item.color;
            } 

            int tileX = xBoard + col * boardWidth/collums;
            int tileY = yBoard + row * boardHeight/rows;
            int nextX = xBoard + (col +1) * boardWidth/collums;
            int nextY = yBoard + (row +1) * boardHeight/rows;
            int tileWidth = nextX - tileX;
            int tileHeight = nextY - tileY;
            drawTileWithRightBottomPadding(g, tileX, tileY, tileWidth, tileHeight, 2, color);
        }
    }

    private void drawTileWithRightBottomPadding(Graphics g, int x, int y, int tileWidth, int tileHeight, int tilePadding, Color color) {
        g.setColor(color);
        g.fillRect(x, y, tileWidth-tilePadding, tileHeight-tilePadding);
    }

    @Override
    public Dimension getPreferredSize() {
        int columns = viewable.getCols();
        int rows = viewable.getRows();
        int s=35;

        int preferredWidth = (s + 2) * columns + 2 + 4;
        int preferredHeight = (s + 2) * rows + 2 + 4;


        return new Dimension(preferredWidth, preferredHeight);
    }
}
