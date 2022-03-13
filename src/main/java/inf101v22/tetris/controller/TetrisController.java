package inf101v22.tetris.controller;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import inf101v22.tetris.model.GameScreen;

/**
 * Controls the data flow into {@link TetrisModel} and updates the {@link TetrisView} whenever data changes. 
 * 
 * @author Espen Lilleengen
 */
public class TetrisController implements java.awt.event.KeyListener {

    private TetrisControllable model;
    private JComponent view;
    
    /**
     * Construct a new TetrisController with the the given arguments.
     * @param model a {@link TetrisControllable}-object to be held by the object
     * @param view a {@link JComponent}-object to be held by the object
    */
    public TetrisController(TetrisControllable model, JComponent view) {
         this.model = model;
         this.view = view;
         view.addKeyListener(this);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameScreen()==GameScreen.GAME_OVER) {
            view.repaint();
            return;
        }
        else if (e.getKeyCode()==KeyEvent.VK_LEFT) { 
            model.moveFallingPiece(0, -1);
        } 
        else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            model.moveFallingPiece(0, 1);
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            model.moveFallingPiece(1, 0);
        }
        else if (e.getKeyCode()==KeyEvent.VK_SHIFT) { //my up-key is broken;(
            model.rotateFallingPiece();
        }
        else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
           model.dropFallingPiece();
        }
        view.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


}
