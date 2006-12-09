package pentago.ModelListener;

import java.util.Observer;

import pentago.Game.IPlayer;
import pentago.Model.IState;

public interface IModelListener extends Observer{

    public void onTurnChange(IPlayer currentPlayer);
    public void onStateChange(IState state);    
    
}
