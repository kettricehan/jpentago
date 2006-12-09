package pentago.Game;

import pentago.Model.IModel;

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
public class PentagoPointMove implements IPiece,IPieceLocation, IMove
{
    /**
     * the x for the location
     */
    private int x = 0;

    /**
     * the y for the location
     */
    private int y = 0;

    /**
     * the piece location
     */
    private Object pieceType = null;

    /**
     * performances the point location move on the board
     * @param model IModel
     * @return boolean
     */
    public boolean makeMove(IModel model)
    {
        /* get the board */
        boolean returnValue = true;
        IBoard board = model.getBoard();

        /**
         * make sure theres no piece at that location, if its blank
         * put the piece in the board
         */
        if(board.getPiece(this) != null)
        {
            returnValue = true;
        }
        else
        {
            board.setPiece(this, this);
        }

        return returnValue;
    }

    /**
     * sets the x
     * @param x int
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * returns the x
     * @return int
     */
    public int getX()
    {
        return x;
    }

    /**
     * sets the y
     * @param y int
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * gets the y
     * @return int
     */
    public int getY()
    {
        return y;
    }

    public void setPieceType(Object pieceType)
    {
        this.pieceType = pieceType;
    }

    public Object getPieceType()
    {
        return pieceType;
    }
}
