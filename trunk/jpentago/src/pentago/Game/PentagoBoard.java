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
public class PentagoBoard implements IBoard, IQuadrant
{
    /**
     * the length of the board
     */
    private int length = 0;

    /**
     * the width of the board
     */
    private int width = 0;

    /**
     * the board object
     */
    IPiece [][] board = null;

    /**
     * the size of each quadrant
     */
    private int quadrantSize = 0;

    /**
     * Two argument constructor using length and width
     * quadrant size is defaulted to length / 2;
     *
     * @param l int
     * @param w int
     */
    public PentagoBoard(int l, int w)
    {
        length = l;
        width = w;
        board = new IPiece[length][width];
        quadrantSize = (int)l/2;
    }

    /**
     * Three argument constructor
     * @param l int
     * @param w int
     * @param qSize int
     */
    public PentagoBoard(int l, int w, int qSize)
    {
        this(l, w);
        setQuadrantSize(qSize);
    }

    /**
     * returns the length
     * @return int
     */
    public int getLength()
    {
        return length;
    }

    /**
     * returns the width of the board
     * @return int
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * sets the piece at the current piece location
     * @param point IPieceLocation
     * @param piece IPiece
     */
    public void setPiece(IPieceLocation point, IPiece piece)
    {
        int x = point.getX();
        int y = point.getY();

        board[x][y] = piece;
    }

    /**
     * get the piece at the current piece location
     * @param point IPieceLocation
     * @return IPiece
     */
    public IPiece getPiece(IPieceLocation point)
    {
        IPiece returnValue =  null;

        int x = point.getX();
        int y = point.getY();

        if( 0 <= x && x < length && 0 <= y && y < width)
        {
            returnValue = board[x][y];
        }

        return returnValue;
    }

    /**
     * returns the current size
     * @return int
     */
    public int getQuadrantSize()
    {
        return quadrantSize;
    }

    /**
     * sets the quadrant size
     * @param qSize int
     */
    public void setQuadrantSize(int qSize)
    {
        quadrantSize = qSize;
    }

    /**
     * Overridden to string argument
     * @return String
     */
    public String toString()
    {
        String returnValue = "";

        for(int i = 0; i < getLength(); i++)
        {
            for(int j = 0; j < getWidth(); j++)
            {
                if(board[i][j] != null)
                {
                    returnValue = returnValue + board[i][j].getPieceType() +
                                  "|";
                }
                else
                {
                    returnValue = returnValue + "_|";
                }
            }
            returnValue = returnValue + "\n";
        }

        return returnValue;
    }
}
