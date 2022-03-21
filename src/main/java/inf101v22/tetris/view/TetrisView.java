package inf101v22.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JComponent;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.scoreboard.Score;

//TODO: Make the text adapt to the window size

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
        int panelWidth = (int) (windowWidth*0.2);
        int panelHeight = (int) (windowHeight*0.18);
        int boardWidth =  windowWidth - (panelWidth);
        int boardHeight = windowHeight;

        drawTetrisBoard(canvas, 4, 4, boardWidth-2*4, boardHeight-2*4, 2);
        drawFallingPiece(canvas, 4, 4, boardWidth-2*4, boardHeight-2*4, 2, viewable.ghostPieceIterable(), true);
        drawFallingPiece(canvas, 4, 4, boardWidth-2*4, boardHeight-2*4, 2, viewable.activePieceIterable(), false);
        drawScoreCount(canvas, boardWidth-3, 6, panelWidth, panelHeight/2);
        drawNextPiece(canvas, boardWidth -3, 8+panelHeight/2, panelWidth, panelHeight);
        drawHeldPiece(canvas, boardWidth -3, 10+panelHeight+panelHeight/2-panelHeight/10, panelWidth, panelHeight);
        drawScoreBoard(canvas, boardWidth -3, 12+panelHeight*2+panelHeight/2-panelHeight/10, panelWidth, panelHeight+panelHeight/8);
        
        if (viewable.getGameScreen()==GameScreen.GAME_OVER) {
            drawGameOver(canvas, windowWidth, windowHeight);
        } 
        else if (viewable.getGameScreen()==GameScreen.WELCOME) {
            drawWelcomeScreen(canvas, windowWidth, windowHeight);
        }
        else if (viewable.getGameScreen()==GameScreen.PAUSE) { 
            drawPauseScreen(canvas, windowWidth, windowHeight);
        }
    }

    private void drawTetrisBoard(Graphics g, int xBoard, int yBoard, int boardWidth, int boardHeight, int boardPadding) {
        drawBoardWithRightBottomPadding(g, xBoard+2, yBoard+2, boardWidth, boardHeight, boardPadding);
    }

    private void drawNextPiece(Graphics g, int x, int y, int panelWidth, int panelHeight) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, panelWidth, panelHeight - panelHeight/10);
        drawSidePanelString(g, "Next piece:", x, y,panelWidth, panelHeight, 15, true);
        drawSidePanelPiece(g, x, y, panelWidth, panelHeight, viewable.nextPieceIterable());
    }

    private void drawGameOver(Graphics canvas, int windowWidth, int windowHeight) {
        fillScreen(canvas, windowWidth, windowHeight, new Color(0xff, 0, 0, 0x80));
        drawWindowString(canvas, "Game over", 0, 0, windowWidth, windowHeight, 30);
        drawWindowString(canvas, "Score: "+viewable.getScore(), 0, 50, windowWidth, windowHeight, 20);
        drawWindowString(canvas, "press enter to return to the welcome screen", 0, 100, windowWidth, windowHeight, 18);
        drawWindowString(canvas, "press 1 to submit your score", 0, 130, windowWidth, windowHeight, 18);
    }

    private void drawWelcomeScreen(Graphics canvas, int windowWidth, int windowHeight) {
        fillScreen(canvas, windowWidth, windowHeight, Color.BLUE);
        drawWindowString(canvas, "Welcome", 0, 0, windowWidth, windowHeight, 35);
        drawWindowString(canvas, "press enter to begin", 0, 50, windowWidth, windowHeight, 18);
    }

    private void drawPauseScreen(Graphics g, int windowWidth, int windowHeight) {
        fillScreen(g, windowWidth, windowHeight, new Color(0, 0, 0, 128));
        drawWindowString(g, "Paused", 0, 0, windowWidth, windowHeight, 35);
        drawWindowString(g, "press enter to unpause", 0, 50, windowWidth, windowHeight, 18);
    }

    private void drawScoreCount(Graphics g, int x, int y, int panelWidth, int panelHeight) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, panelWidth, panelHeight);
        drawSidePanelString(g, "Score: ", x, y,panelWidth, panelHeight, 15, true);
        drawSidePanelString(g,""+viewable.getScore(), x, y+15, panelWidth, panelHeight, 15, true);
    }

    private void drawHeldPiece(Graphics g, int x, int y, int panelWidth, int panelHeight) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, panelWidth, panelHeight);
        drawSidePanelString(g, "Held piece:", x, y, panelWidth, panelHeight, 15, true);
        drawSidePanelPiece(g, x, y, panelWidth, panelHeight, viewable.heldPieceIterable());
    }

    private void drawScoreBoard(Graphics g, int x, int y, int panelWidth, int panelHeight) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, panelWidth, panelHeight);
        drawSidePanelString(g, "Highscores: ", x, y, panelWidth, panelHeight, 15, true);

        int i = 1;
        for (Score s : viewable.scoreIterable()) {
            drawSidePanelString(g, i + ": " + s.toString(), x, y+(18*i), panelWidth, panelHeight, 13, false);
            i++;
            if (i==6) {
                break;
            }
        }
    }

    private void drawFallingPiece(Graphics g, int xBoard, int yBoard, int boardWidth, int boardHeight, int boardPadding, 
                                  Iterable<CoordinateItem<Tile>> iterable, boolean isGhostPiece) {
        int rows = viewable.getRows();
        int cols = viewable.getCols();
        boardHeight-=boardPadding;
        boardWidth-=boardPadding;
        Color color;

        for (CoordinateItem<Tile> cItem : iterable) {
            int row = cItem.getRow();
            int col = cItem.getCol();
            if (isGhostPiece) {
                color = new Color(204, 204, 204, 128);


            } else {
                color = cItem.item.color;
            }

            int tileX = xBoard + col * boardWidth/cols;
            int tileY = yBoard + row * boardHeight/rows;
            int nextX = xBoard + (col +1) * boardWidth/cols;
            int nextY = yBoard + (row +1) * boardHeight/rows;
            int tileWidth = nextX - tileX;
            int tileHeight = nextY - tileY;
            drawTileWithRightBottomPadding(g, tileX+2, tileY+2, tileWidth, tileHeight, 2, color);
        }
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


    private void drawSidePanelString(Graphics g, String s, int x, int y, int panelWidth, int panelHeight, int fontSize, boolean centered) {
        g.setColor(Color.BLACK);
        Font f = new Font("SansSerif", Font.BOLD,fontSize);
        g.setFont(f);
        int scoreX;

        int stringWidth = GraphicHelperMethods.getStringWidth(g, f, s);
        int stringAscent = GraphicHelperMethods.getStringAscent(g, f);
        if (centered) {
            scoreX = x + (panelWidth - stringWidth) /2;
        }
        else {
            scoreX = x;
        }
        int scoreY = y + stringAscent;

        g.drawString(s, scoreX, scoreY);
    }

    private void drawSidePanelPiece(Graphics g, int x, int y, int panelWidth, int panelHeight, Iterable<CoordinateItem<Tile>> iterable) {
        if (iterable == null) {
            return;
        }
        x-=panelWidth/9;
        int z = 0;
        for (CoordinateItem<Tile> cItem : iterable) {
            int row = cItem.getRow()+1;
            int col = cItem.getCol()-3;
            Color color = cItem.item.color;

            if (cItem.item.character=='I' || cItem.item.character=='O') {
                z+=panelWidth/10;
            }

            int tileX = x + col * panelWidth/4 +z;
            int tileY = y + row * panelHeight/4;
            int nextX = x + (col +1) * panelWidth/4 +z;
            int nextY = y + (row +1) * panelHeight/4;
            int tileWidth = nextX - tileX;
            int tileHeight = nextY - tileY;
            drawTileWithRightBottomPadding(g, tileX+2, tileY+2, tileWidth, tileHeight, 2, color);
            z = 0;
        }
    }

    private void fillScreen(Graphics canvas, int windowWidth, int windowHeight, Color color) {
        canvas.setColor(color);
        canvas.fillRect(0, 0, windowWidth, windowHeight);
    }

    private void drawWindowString(Graphics g, String s, int x, int y, int windowWidth, int windowHeight, int fontSize) {
        g.setColor(Color.WHITE);
        Font f = new Font("SansSerif", Font.BOLD, fontSize);
        g.setFont(f);
        GraphicHelperMethods.drawCenteredString(g, s, x, y, windowWidth, windowHeight);
    }

    @Override
    public Dimension getPreferredSize() {
        int columns = viewable.getCols();
        int rows = viewable.getRows();
        int sWidth = 45;
        int sHeight=35;

        int preferredWidth = (sWidth + 2) * columns + 2 + 4;
        int preferredHeight = (sHeight + 2) * rows + 2 + 4;


        return new Dimension(preferredWidth, preferredHeight);
    }
}
