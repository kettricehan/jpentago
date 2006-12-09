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
public class PentagoPieceLocation implements IPieceLocation {

    protected int x = 0;
    protected int y = 0;

    /**
     * default constructor
     */
    public PentagoPieceLocation() {
        super();
    }

    /**
     * two argument constructor - sets piece location x and y
     * @param x int
     * @param y int
     */
    public PentagoPieceLocation(int x, int y)
    {
        setX(x);
        setY(y);
    }

    /**
     * sets the x of the location
     * @param x int
     */
    public void setX(int x) {
      this.x = x;
    }

    /**
     * gets the x of the location
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * sets the y location
     * @param y int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * returns the y location
     * @return int
     */
    public int getY() {
        return y;
    }
}
