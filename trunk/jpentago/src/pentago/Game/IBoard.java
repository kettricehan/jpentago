package pentago.Game;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public interface IBoard {

    /**
     * Returns the length of the board
     * @return int
     */
    public int getLength();

    /**
     * Returns the width of the board
     * @return int
     */
    public int getWidth();

    /**
     * Sets the current piece at the piece location - this will
     * accept a piece which equals null
     * @param point IPieceLocation
     * @param piece IPiece
     */
    public void setPiece(IPieceLocation point, IPiece piece);

    /**
     * Gets the piece at the current piece location - will return null if
     * no piece is found
     * @param point IPieceLocation
     * @return IPiece
     */
    public IPiece getPiece(IPieceLocation point);
}
