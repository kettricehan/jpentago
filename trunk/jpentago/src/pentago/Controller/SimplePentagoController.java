package pentago.Controller;

import pentago.Game.IMove;
import pentago.Game.IPlayer;
import pentago.Model.IModel;
import pentago.Model.IState;
import java.util.*;
public class SimplePentagoController implements IPentagoController{
    IModel gameModel;
    ArrayList<IMove> moveQueue = new ArrayList<IMove>();
    
    public SimplePentagoController(IModel gameModel) {
        this.gameModel = gameModel;
    }
    public void allowPointPlacement(boolean allow) {
        // TODO Auto-generated method stub
        
    }

    public void allowQuadrantRotation(boolean allow) {
        // TODO Auto-generated method stub
        
    }

    public void changePlayer(IPlayer player) {
        // TODO Auto-generated method stub
        
    }

    public void queueMove(IMove move) {
        // TODO Auto-generated method stub
        moveQueue.add(move);
    }

    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    public void stopGame() {
        // TODO Auto-generated method stub
        
    }

    public void submitMove(IMove move) {
        // TODO Auto-generated method stub
        gameModel.submitMove(move);
    }

    public void submitMoves() {
        // TODO Auto-generated method stub
        Iterator<IMove> i = moveQueue.iterator();
        while(i.hasNext()) {
            IMove m = i.next();
            //submit to model
            submitMove(m);
            //remove from queue
            i.remove();
        }
    }

    public void undoMoves() {
        // TODO Auto-generated method stub
        moveQueue.clear();
    }

    public void updateState(IState state) {
        // TODO Auto-generated method stub
        
    }

}
