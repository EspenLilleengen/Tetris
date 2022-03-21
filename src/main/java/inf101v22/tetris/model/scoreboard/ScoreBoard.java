package inf101v22.tetris.model.scoreboard;

import java.util.Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** Stores the scores from a game and ranks them from best to worst.
 * <p>
 * An object of this type can be used to acsess a high score list for the game.
 * 
 * @author Espen Lilleengen
*/
public class ScoreBoard implements Iterable<Score> {

    private final String path;
    private List<Score> highScores = new ArrayList<Score>();

    /**
     * Construct a scoreboard with the given file path.
     * @param path the path to the file in which the date is storeds
     */
    public ScoreBoard(String path) {
        this.path = path;
        try {
            FileInputStream fileStream = new FileInputStream(path);
            ObjectInputStream scoreIn = new ObjectInputStream(fileStream);
            highScores = (List<Score>) scoreIn.readObject();   // TODO: check cast with instansof
            scoreIn.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**Adds a score to the scoreboard*/
    public void add(Score score) {
        highScores.add(score);
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream scoreOut = new ObjectOutputStream(fileOut);
            scoreOut.writeObject(highScores);
            scoreOut.close();
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public Iterator<Score> iterator() {
        Collections.sort(highScores);
        return highScores.iterator();
    }

    public static void main(String[] args) {
        ScoreBoard s = new ScoreBoard("/Users/lx/Desktop/INF101/Espen.Lilleengen_sem1-tetris/src/main/java/inf101v22/tetris/model/scoreboard/ScoreBoardData.txt");
        
        for (Score i : s) {
            System.out.println(i.toString());
        }
    }
}
