package pentago.Game;

import pentago.Model.IState;

public class PentagoState implements IState {

    Object state;
    public PentagoState(Object state) {
        // TODO Auto-generated constructor stub
        setState(state);
    }

    public Object getState() {
        // TODO Auto-generated method stub
        return state;
    }

    public void setState(Object state) {
        this.state = state;

    }

}
