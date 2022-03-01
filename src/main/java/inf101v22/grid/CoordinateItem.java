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
        return this.coordinate.equals(other.coordinate) && this.item.equals(other.item);
    }

    @Override
    public String toString() {
        return "{ coordinate='" + coordinate.toString() + 
        "', item='" + this.item + "' }";
    }
}