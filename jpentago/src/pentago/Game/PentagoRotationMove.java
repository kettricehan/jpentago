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
public class PentagoRotationMove implements IPentagoRotation, IMove
{
    private IPieceLocation quadrant = null;
    private Object rotation = null;

    public PentagoRotationMove()
    {
        super();
    }

    /**
     * makes the move
     * @param model IModel
     * @return boolean
     */
    public boolean makeMove(IModel model)
    {
        boolean returnValue = true;
        IBoard board = model.getBoard();
        IQuadrant quadrant = (IQuadrant) board;
        int quadrantSize = quadrant.getQuadrantSize();

        if (rotation.equals(IPentagoRotation.LEFT_ROTATION))
        {
            returnValue = rotateLeft(board, quadrantSize);
        }
        else if (rotation.equals(IPentagoRotation.RIGHT_ROTATION))
        {
            returnValue = rotateRight(board, quadrantSize);
        }
        else
        {
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * Rotate the board left
     * @param board IBoard
     * @param quadrantSize int
     * @return boolean
     */
    private boolean rotateLeft(IBoard board, int quadrantSize)
    {
        boolean returnValue = true;

        /**
         * gets the quadrant
         */
        IPieceLocation quadrantLoc = getQuadrant();
        int x = quadrantLoc.getX();
        int y = quadrantLoc.getY();

        /**
         * get the real x and y
         */
        int realX = x * quadrantSize;
        int realY = y * quadrantSize;

        /**
         * create a new board to do the out-of-place rotation (out-of-place
         * means extra storage is needed..in place means the rotation would happen
         * in the same storage
         */
        IBoard newBoard = new PentagoBoard(quadrantSize, quadrantSize);

        /**
         * for each piece, rotation and place into new board
         */
        for (int i = 0; i < quadrantSize; i++)
        {
            for (int j = 0; j < quadrantSize; j++)
            {
                //  IPieceLocation location = new PentagoPieceLocation(realY + j,realX + (quadrantSize - i-1));
                IPieceLocation location = new PentagoPieceLocation((
                        quadrantSize - j - 1), i);
                IPieceLocation oldLocation = new PentagoPieceLocation(realX + i,
                        realY + j);
                if (board.getPiece(oldLocation) != null)
                {
                    //System.out.println(board.getPiece(oldLocation).getPieceType());
                }
                newBoard.setPiece(location, board.getPiece(oldLocation));
            }
        }

        for (int i = 0; i < quadrantSize; i++)
        {
            for (int j = 0; j < quadrantSize; j++)
            {
                IPieceLocation location = new PentagoPieceLocation(i, j);
                IPieceLocation location2 = new PentagoPieceLocation(realX + i,
                        realY + j);

                board.setPiece(location2, newBoard.getPiece(location));
            }
        }

        return returnValue;
    }

    /**
     * rotate the board clockwise
     * @param board IBoard
     * @param quadrantSize int
     * @return boolean
     */
    private boolean rotateRight(IBoard board, int quadrantSize)
    {
        boolean returnValue = true;

        /**
         * gets the quadrant
         */
        IPieceLocation quadrantLoc = getQuadrant();
        int x = quadrantLoc.getX();
        int y = quadrantLoc.getY();

        /**
         * get the real x and y
         */
        int realX = x * quadrantSize;
        int realY = y * quadrantSize;

        /**
         * create a new board to do the out-of-place rotation (out-of-place
         * means extra storage is needed..in place means the rotation would happen
         * in the same storage
         */
        IBoard newBoard = new PentagoBoard(quadrantSize, quadrantSize);

        /**
         * for each piece, rotation and place into new board
         */
        for (int i = 0; i < quadrantSize; i++)
        {
            for (int j = 0; j < quadrantSize; j++)
            {
                IPieceLocation location = new PentagoPieceLocation(j,
                        (quadrantSize - i - 1));

                IPieceLocation oldLocation = new PentagoPieceLocation(realX + i,
                        realY + j);
                newBoard.setPiece(location, board.getPiece(oldLocation));
            }
        }

        /**
         * tranlsate into the original board
         */
        for (int i = 0; i < quadrantSize; i++)
        {
            for (int j = 0; j < quadrantSize; j++)
            {
                IPieceLocation location = new PentagoPieceLocation(i, j);
                IPieceLocation location2 = new PentagoPieceLocation(realX + i,
                        realY + j);

                board.setPiece(location2, newBoard.getPiece(location));
            }
        }

        return returnValue;
    }

    /**
     * sets the quadrant
     * @param quadrant IPieceLocation
     */
    public void setQuadrant(IPieceLocation quadrant)
    {
        this.quadrant = quadrant;
    }

    /**
     * sets the rotation object - use IPentagoRotation.LEFT_ROTATION
     * or IPentagoRotation.RIGHT_ROTATION
     * @param rotation Object
     */
    public void setRotation(Object rotation)
    {
        this.rotation = rotation;
    }

    /**
     * returns the quadrant
     * @return IPieceLocation
     */
    public IPieceLocation getQuadrant()
    {
        return quadrant;
    }

    /**
     * gets the rotation
     * @return Object
     */
    public Object getRotation()
    {
        return rotation;
    }
}
