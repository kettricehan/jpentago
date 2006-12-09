package pentago.Controller;

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

import pentago.Game.IMove;
import pentago.Game.IPlayer;
import pentago.Model.IState;

public interface IPentagoController
{    
    public void startGame();
    public void stopGame();
    public void queueMove(IMove move);
    public void undoMoves();
    public void submitMoves();    
    public void submitMove(IMove move);
}
