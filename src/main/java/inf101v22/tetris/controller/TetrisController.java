package inf101v22.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import inf101v22.tetris.midi.TetrisSong;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.TetrisModel;

/**
 * Controls the data flow into {@link TetrisModel} and updates the {@link TetrisView} whenever data changes. 
 * 
 * @author Espen Lilleengen
 */
public class TetrisController implements java.awt.event.KeyListener, java.awt.event.ActionListener {

    private TetrisControllable model;
    private JComponent view;
    private Timer timer;
    private TetrisSong tetrisSong = new TetrisSong();
    private ScoreNamer scoreNamer = new ScoreNamer(this);


    /**
     * Construct a new TetrisController with the the given arguments.
     * @param model a {@link TetrisControllable}-object to be held by the object
     * @param view a {@link JComponent}-object to be held by the object
    */
    public TetrisController(TetrisControllable model, JComponent view) {
         this.model = model;
         this.view = view;
         view.addKeyListener(this);
         timer = new Timer(model.getDelay(), this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameScreen()==GameScreen.GAME_OVER) {
            if (e.getKeyCode()==KeyEvent.VK_1) {
                scoreNamer.run();
            }
            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                tetrisSong.doStopMidiSounds();
                model.setGameScreen(GameScreen.WELCOME);
                model.resetBoard();
            }
            view.repaint();
            return;
        }
        else if (model.getGameScreen()==GameScreen.WELCOME) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                tetrisSong.run();
                model.setGameScreen(GameScreen.ACTIVE_GAME);
                timer.start();
            }
            view.repaint();
            return;
        }
        else if (model.getGameScreen() == GameScreen.PAUSE) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                tetrisSong.doUnpauseMidiSounds();
                model.setGameScreen(GameScreen.ACTIVE_GAME);
            }
        }
        else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            tetrisSong.doPauseMidiSounds();
            model.setGameScreen(GameScreen.PAUSE);
        }
        else if (e.getKeyCode()==KeyEvent.VK_C && model.getHoldAction()) {
            model.holdPiece();
            timer.restart();
            model.blockHoldAction();
        }
        else if (e.getKeyCode()==KeyEvent.VK_LEFT) { 
            model.moveFallingPiece(0, -1);
        } 
        else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            model.moveFallingPiece(0, 1);
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
             if (model.moveFallingPiece(1, 0)) {
                 timer.restart();
             }
        }
        else if (e.getKeyCode()==KeyEvent.VK_Z) {
            model.rotateFallingPieceLeft(); 
        }
        else if (e.getKeyCode()==KeyEvent.VK_SHIFT) { //my up-key is broken;( //TODO: change keys
            model.rotateFallingPieceRight();
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getGameScreen()==GameScreen.ACTIVE_GAME) {
            model.clockTick();
            view.repaint();
            setTimerDelay();
        }
        if (e.getSource() == scoreNamer.getOkButton()) {
            if (scoreNamer.getTextField().getText().length() == 0) {
                JOptionPane.showMessageDialog(
                    scoreNamer,
                    "You didn't enter anything",
                    "Bafoon",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                model.submitScore(scoreNamer.getTextField().getText());
                JOptionPane.showMessageDialog(
                    scoreNamer,
                    "Your score was added to the scoreboard",
                    "Succsess",
                    JOptionPane.INFORMATION_MESSAGE);
                scoreNamer.dispose();
                model.setGameScreen(GameScreen.WELCOME);
                model.resetBoard();
            }
        }
    }

    private void setTimerDelay() {
        int delay = model.getDelay();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }
}
