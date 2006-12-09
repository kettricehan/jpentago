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
import pentago.Game.IPiece;

public interface IPentagoPoint {

    public int getQuadrant();
    public void setQuadrant(int q);
    public int getX();
    public void setX(int x);
    public int getY();
    public void getY(int y);
    public void setPiece(IPiece piece);
    public IPiece getPiece();
}
