package inf101v22.tetris;

import javax.swing.JComponent;
import javax.swing.JFrame;

import inf101v22.tetris.controller.TetrisController;
import inf101v22.tetris.model.TetrisModel;
import inf101v22.tetris.view.TetrisView;
import java.awt.event.KeyListener;


public class TetrisMain {
    public static final String WINDOW_TITLE = "INF101 Tetris";
    

    public static void main(String[] args) {
        TetrisModel tetrisModel = new TetrisModel();
        JComponent view = new TetrisView(tetrisModel);
        @SuppressWarnings("unused")
        KeyListener controller =new TetrisController(tetrisModel, view);
          
        // The JFrame is the "root" application window.
        // We here set som properties of the main window, 
        // and tell it to display our tetrisView
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Here we set which component to view in our window
        frame.setContentPane(view);

        // Call these methods to actually display the window
        frame.pack();
        frame.setVisible(true);
    }
}