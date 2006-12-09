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
public interface IPentagoRotation {

    /**
     * Constants for the left rotation
     */
    public static Object LEFT_ROTATION = new Integer(1);

    /**
     * Constants for the right rotation
     */
    public static Object RIGHT_ROTATION = new Integer(-1);

    /**
     * Sets the quadrant by x,y coordinate...i.e...if this is the boarde
     * _______________________
     * |         |           |
     * |  0,0    | 0,1       |
     * |_________|___________|
     * |         |           |
     * |  1,0    | 1,1       |
     * |_________|___________|
     * @param quadrant IPieceLocation
     */
    public void setQuadrant(IPieceLocation quadrant);

    /**
     * sets the rotation -
     * <br>
     * use IPentagoRotation.LEFT_ROTATION or IPentagoRotation.RIGHT_ROTATION
     * @param rotation Object
     */
    public void setRotation(Object rotation);

    /**
     * returns the piece location
     * @return IPieceLocation
     */
    public IPieceLocation getQuadrant();

    /**
     * Returns the rotation object
     * @return Object
     */
    public Object getRotation();

}
