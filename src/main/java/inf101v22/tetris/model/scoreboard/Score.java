package inf101v22.tetris.model.scoreboard;

import java.io.Serializable;


/**
 * This class repsenets a player score. An object of this type cointains the players name 
 * and the players score.
 * 
 * @author Espen Lilleengen
 */
public class Score implements Comparable<Score>, Serializable{

    private final int score;
    private final String name;

    /** 
     * Contructs a new score object with the given arguments.
     * @param score the players score
     * @param name the name of the player
    */
    public Score(int score, String name) {
        this.score = score;
        this.name = name;
    }

    @Override
    public int compareTo(Score that) {
        if (score>that.score) {
            return -1;
        }
        else if (score<that.score) {
            return 1;
        }
        else {
            return name.compareTo(that.name);
        }
    }

    @Override
    public String toString() {
        return "" + name + ": " + score;
    }
    
}

