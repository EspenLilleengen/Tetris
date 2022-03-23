package inf101v22.tetris.controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

/** 
 * Displayes a box which allows users to type in their initials. 
 * 
 * @author Espen Lilleengen
*/
public class ScoreNamer extends JFrame{

    private final JButton okButton;
    private final JTextField textField;

    /**
     * Constructs and displays a new ScoreNamer frame. 
     * @param e the object that prosses the actions 
    */
    ScoreNamer(ActionListener e) {
        JPanel userInterface = new JPanel();
        textField = new JTextField(15); 
        okButton = new JButton("OK");
        userInterface.add(new JLabel("Enter your initials: ")); 
        userInterface.add(textField);
        okButton.addActionListener(e);
        userInterface.add(okButton);
        this.add(userInterface);
    }

    /**Displays the panel that alows the user to enter their name*/
    void run() {
        this.setSize(325,100);
        this.setTitle("Enter your initials to submit your score");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**@return the OK button to be listened for*/
    JButton getOkButton() {
        return okButton;
    }

    /**@return the textfield the user types in */
    JTextField getTextField() {
        return textField;
    }
    
}
