// package inf101v22.grid;
// import java.util.Iterator;
// import java.util.List;

// public class GridIterator<E> implements Iterator<CoordinateItem<E>>{

//     private final int rows;
//     private final int cols;
//     private CoordinateItem<E> next;
//     public final List<E> coordinateItems;

//     public GridIterator(int rows, int cols, List<E> coordinateItems) {
//         this.rows = rows;
//         this.cols = cols;
//         this.coordinateItems = coordinateItems;
//         if (coordinateItems.get(0) instanceof CoordinateItem) {
//             throw new IllegalArgumentException();
//         }
//         next = (CoordinateItem<E>) coordinateItems.get(0);

//     }

//     @Override
//     public boolean hasNext() {
//         return isOnGrid(next);
//     }

//     private boolean isOnGrid(CoordinateItem<E> c) {
//         if (c.coordinate.row < rows && c.coordinate.col < cols) {
//             return true;
//         }
//         else return false;
//     }


//     @Override
//     public CoordinateItem<E> next() {
//         CoordinateItem<E> toReturn = next;
//         next = (CoordinateItem<E>) coordinateItems.get(Grid.coordinateToIndex(next.coordinate, rows)+1);
//         return toReturn;
//     }
// }
