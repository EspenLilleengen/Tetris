package inf101v22.tetris.controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

/** 
 * Displayes a box which allows users to type in their name. 
 * 
 * @author Espen Lilleengen
*/
public class ScoreNamer extends JFrame{

    private final JButton okButton;
    private final JTextField textField;

    /**Constructs and displays a new ScoreNamer frame. 
     * @param e the object that prosses the actions 
    */
    public ScoreNamer(ActionListener e) {
        JPanel userInterface = new JPanel();
        textField = new JTextField(15); 
        okButton = new JButton("OK");
        userInterface.add(new JLabel("Enter your name: ")); 
        userInterface.add(textField);
        okButton.addActionListener(e);
        userInterface.add(okButton);
        this.add(userInterface);
    }

    /**Displays a panel that alows the user to enter their name*/
    public void run() {
        this.setSize(325,100);
        this.setTitle("Enter your name to submit your score");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**@return the OK button to be listened for*/
    public JButton getOkButton() {
        return okButton;
    }

    /**@return the textfield the user types in */
    public JTextField getTextField() {
        return textField;
    }
    
}
