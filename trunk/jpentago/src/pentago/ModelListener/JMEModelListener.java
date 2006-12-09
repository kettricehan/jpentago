package pentago.ModelListener;

import java.util.Observable;
import java.util.Observer;

import net.jpentago.view.MasterGameBoard;

import pentago.Game.IPlayer;
import pentago.Model.IState;

public class JMEModelListener implements Observer, IModelListener {

    private MasterGameBoard game;
    
    public JMEModelListener(MasterGameBoard game) {
        this.game = game;
    }

    public void onStateChange(IState state) {
        // TODO Auto-generated method stub

    }

    public void onTurnChange(IPlayer currentPlayer) {
        // TODO Auto-generated method stub

    }

    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        if(arg instanceof IPlayer) {
            onTurnChange((IPlayer)arg);
        }
        else if(arg instanceof IState) {
            onStateChange((IState)arg);
        }
    }

}
