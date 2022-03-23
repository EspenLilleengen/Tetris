package inf101v22.grid;
import java.util.Objects;

/**
 * This class repsesents a cordinate on a grid, the 
 * cordinate is represented by a row-value and a column-value
 * 
 * @author Espen Lilleengen
 */
public class Coordinate {

    /** The row index of the coordinate*/
    public final int row;
    /** The column index of the coordinate*/
    public final int col;

    /**
     * Contruct a coordinate with the given row and column
     * 
     * @param row the row index
     * @param col the column index
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate other = (Coordinate) o;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "{" +
            " row='" + this.row + "'" +
            ", col='" + this.col + "'" +
            " }";
    }
}
