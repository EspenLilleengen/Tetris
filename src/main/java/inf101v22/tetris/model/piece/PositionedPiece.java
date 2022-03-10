package inf101v22.tetris.model.piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;

/** 
 * An object of this class represents a Tetris piece with a position
 * 
 * @author Espen Lilleengen
*/
public class PositionedPiece implements Iterable<CoordinateItem<Tile>>{

    private PieceShape pieceShape;
    private Coordinate coordinate;

    /** 
     * Constructs a new {@link PositionedPiece}-object with the given {@link PieceShape} and {@link Coordinate}
     */
    PositionedPiece(PieceShape pieceShape, Coordinate coordinate) {
        this.pieceShape = pieceShape;
        this.coordinate = coordinate;
    }

    /** @return the height of the {@link PieceShape}-object*/
    public int getHeight() {
        return pieceShape.getHeight();
    }

    /** @return the width of the {@link PieceShape}-object*/
    public int getWidth() {
        return pieceShape.getWidth();
    }

    /** @return the tile of the {@link PieceShape}-object*/
    public Tile getTile() {
        return pieceShape.getTile();
    }

    @Override
    public Iterator<CoordinateItem<Tile>> iterator() {
        ArrayList<CoordinateItem<Tile>> coordinateItemsIterable = new ArrayList<>();
        boolean[][] shape = pieceShape.getShape();

        for (int row = coordinate.row; row < coordinate.row + getHeight(); row++) {
            for (int col = coordinate.col; col < coordinate.col + getWidth(); col++) {
                if (shape[row - coordinate.row][col - coordinate.col]) {
                    Coordinate c = new Coordinate(row,col);
                    coordinateItemsIterable.add(new CoordinateItem<Tile>(c,pieceShape.getTile()));
                }
            }
        }
        return coordinateItemsIterable.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof PositionedPiece)) {
            return false;
        }
        PositionedPiece other = (PositionedPiece) obj;
        return this.coordinate.equals(other.coordinate) && this.pieceShape.equals(other.pieceShape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceShape, coordinate);
    }

    @Override
    public String toString() {
        return "{" +
            " pieceShape='" + pieceShape.toString() + "'" +
            ", coordinate='" + coordinate.toString() + "'" +
            "}";
    }


}
