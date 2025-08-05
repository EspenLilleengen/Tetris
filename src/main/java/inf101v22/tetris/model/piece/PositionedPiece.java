package inf101v22.tetris.model.piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    final PieceShape pieceShape;
    final Coordinate coordinate;

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
        List<CoordinateItem<Tile>> coordinateItemsIterable = new ArrayList<>();
        boolean[][] shape = pieceShape.getShape();

        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (shape[row][col]) {
                    Coordinate c = new Coordinate(coordinate.row + row, coordinate.col + col);
                    coordinateItemsIterable.add(new CoordinateItem<Tile>(c,pieceShape.getTile()));
                }
            }
        }
        return coordinateItemsIterable.iterator();
    }

    /** 
     * Makes a copy of the object, but moved up/down acording to the given arguments
     * 
     * @param deltaRow where to move the new object along the row
     * @param deltaColumn where to move the new object along the column
     * @return The copy of the object, with new Coordinates
    */
    public PositionedPiece movedCopy(int deltaRow, int deltaColumn) {
        return new PositionedPiece(pieceShape, new Coordinate(coordinate.row+deltaRow, coordinate.col+deltaColumn));
     }

     /**
     * Rotates a piece to the left around its center
     * @return the rotated and positioned piece 
     */
     public PositionedPiece rotateLeft() {
         return rotate(false);
     }

     /**
     * Rotates a piece to the right around its center
     * @return the rotated and positioned piece 
     */
    public PositionedPiece rotateRight() {
        return rotate(true);
    }

    private PositionedPiece rotate(boolean doesRotateRight) {
        PositionedPiece rotatedPiece;
        if (doesRotateRight) {
            rotatedPiece = new PositionedPiece(pieceShape.rotateRight(), coordinate);
        } else {
            rotatedPiece = new PositionedPiece(pieceShape.rotateLeft(), coordinate);
        }

        int oldCenterRow = coordinate.row + getHeight()/2;
        int oldCenterCol = coordinate.col + getWidth()/2;
        int newCenterRow = rotatedPiece.coordinate.row + rotatedPiece.getHeight()/2;
        int newCenterCol = rotatedPiece.coordinate.col + rotatedPiece.getWidth()/2;
        int deltaRow = oldCenterRow-newCenterRow;
        int deltaCol = oldCenterCol-newCenterCol;

        return rotatedPiece.movedCopy(deltaRow,deltaCol);
    } 

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof PositionedPiece)) {
            return false;
        }
        PositionedPiece other = (PositionedPiece) obj;
        return coordinate.equals(other.coordinate) && pieceShape.equals(other.pieceShape);
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
