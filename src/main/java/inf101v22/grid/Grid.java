package inf101v22.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A gird that holds a set of elements
 * 
 * @param <E> The type of elements to be held by the grid
 * 
 * @author Espen Lilleengen
 */
public class Grid<E> implements IGrid<E>{
    private List<E> coordinateItems;
    private final int rows;
    private final int cols;

    /**
     * Construct a grid with the given row and column dimensions, 
     * which initially holds null
     * 
     * @param rows
     * @param cols
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    /**
     * Constructs a grid with the given dimensions
     * 
     * @param rows
     * @param cols
     * @param initialValue What value the coordinates should initally hold
     */
    public Grid(int rows, int cols, E initialValue) {
        if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException();
		}

        this.rows = rows;
        this.cols = cols;
        coordinateItems = new ArrayList<>(rows*cols);
        for (int i = 0; i < rows*cols; i++) {
            coordinateItems.add(initialValue);
        }
    }

    @Override
    public Iterator<CoordinateItem<E>> iterator() {
        ArrayList<CoordinateItem<E>> coordinateItemsIterable = new ArrayList<>();

        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<cols; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                CoordinateItem<E> coordinateItem = new CoordinateItem<E>(coordinate, this.get(coordinate));
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
        
        coordinateItems.set(coordinateToIndex(coordinate), value);
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
	 */
    private void checkCoordinate(Coordinate coordinate) {
        if(!coordinateIsOnGrid(coordinate)) {
            throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
        }
    }

    @Override
    public E get(Coordinate coordinate) {
        checkCoordinate(coordinate);
        return coordinateItems.get(coordinateToIndex(coordinate));
    }

    @Override
    public boolean coordinateIsOnGrid(Coordinate coordinate) {
        if (coordinate.row < 0 || coordinate.row >= rows) {
            return false;
        }
        return coordinate.col >= 0 && coordinate.col < cols;
    }

}
