package inf101v22.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A 2 dimentional grid that holds a set of elements
 * 
 * @param <E> The type of elements to be held by the grid
 * 
 * @author Espen Lilleengen
 */
public class Grid<E> implements IGrid<E>{
    private final List<E> items;
    private final int rows;
    private final int cols;

    /**
     * Construct a grid with the given row and column dimensions,which initially holds null.
     * A grid cannot have rows and collum dimentions smallen than 1,in which case the grid 
     * would not have any dimentions.
     * 
     * @param rows
     * @param cols
     * @throws IllegalArgumentException of the rows or collums values is smaller than 1
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    /**
     * Constructs a grid with the given dimensions. A grid cannot have rows and collum dimentions smallen than 1,
     * in which case the grid would not have any dimentions.
     * 
     * @param rows
     * @param cols
     * @param initialValue What value the coordinates should initally hold
     * @throws IllegalArgumentException of the rows or collums values is smaller than 1
     */
    public Grid(int rows, int cols, E initialValue) {
        if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Rows and collum values must be at least 1");
		}

        this.rows = rows;
        this.cols = cols;
        items = new ArrayList<>(rows*cols);
        for (int i = 0; i < rows*cols; i++) {
            items.add(initialValue);
        }
    }

    @Override
    public Iterator<CoordinateItem<E>> iterator() {
        ArrayList<CoordinateItem<E>> coordinateItemsIterable = new ArrayList<>();

        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<cols; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                CoordinateItem<E> coordinateItem = new CoordinateItem<E>(coordinate, get(coordinate));
                coordinateItemsIterable.add(coordinateItem);
            }
        }
        return coordinateItemsIterable.iterator();
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public void set(Coordinate coordinate, E value) {
        checkCoordinate(coordinate);
        items.set(coordinateToIndex(coordinate), value);
    }

    /**
     * Computes which index in the list belongs to a given Coordinate
     * 
     * @param coordinate the coordinate to be indexed
     * @param rows the number of rows in the grid that contains the coordinate
     * @return the index that belongs to the coordinate
     */
    private int coordinateToIndex(Coordinate coordinate) {
        return coordinate.row + coordinate.col * rows;
    }

     /**
	 * Checks if a given coordinate is within the bounds of this grid.
	 * If it is not, an IndexOutOfBoundsException is thrown.
	 * 
	 * @param coordinate the coordinate to check
     * @throws IndexOutOfBoundsException if the coordinate is not within the bounds of the grid
	 */
    private void checkCoordinate(Coordinate coordinate) {
        if(!coordinateIsOnGrid(coordinate)) {
            throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
        }
    }

    @Override
    public E get(Coordinate coordinate) {
        checkCoordinate(coordinate);
        return items.get(coordinateToIndex(coordinate));
    }

    @Override
    public boolean coordinateIsOnGrid(Coordinate coordinate) {
        if (coordinate.row < 0 || coordinate.row >= rows) {
            return false;
        }
        return coordinate.col >= 0 && coordinate.col < cols;
    }

}
