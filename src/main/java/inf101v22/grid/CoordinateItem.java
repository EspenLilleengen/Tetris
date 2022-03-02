package inf101v22.grid;

import java.util.Objects;

/**
 * This class represents an object with that holds a {@link Coordinate}-object and an element 
 * 
 * @param <E> The type of element to be held by the CoordinateItem
 * 
 * @author Espen Lilleengen
 */
public class CoordinateItem <E> {

    public final Coordinate coordinate;
    public final E item;

    /**
     * Constructs a CoordinateItem with the given coordinates and item
     * 
     * @param coordinate - the coordinates to be held by the object
     * @param item - the item to be held by the object
     */
    public CoordinateItem(Coordinate coordinate, E item) {
        this.coordinate = coordinate;
        this.item = item;
    }

    /**
     * @return the row of the coordinate held by the object
     */
    public int getRow() {
        return coordinate.row;
    }

    /**
     * @return the colum of the coordinate held by the object
     */
    public int getCol() {
        return coordinate.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, item);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (!(obj instanceof CoordinateItem)) 
            return false;
        
        CoordinateItem<E> other = (CoordinateItem<E>) obj;
        if (other.item!=null) {
            return this.coordinate.equals(other.coordinate) && this.item.equals(other.item);
        } else {
            return this.coordinate.equals(other.coordinate) && this.item==null;
        }
    }

    @Override
    public String toString() {
        return "{ coordinate='" + coordinate.toString() + 
        "', item='" + this.item + "' }";
    }
}