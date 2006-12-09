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
public interface IPiece {

    /**
     * Sets the piece type for this piece with a generic object type
     * @param pieceType Object
     */
    public void setPieceType(Object pieceType);

    /**
     * Returns the piece object
     * @return Object
     */
    public Object getPieceType();

}
