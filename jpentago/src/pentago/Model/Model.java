package pentago.Model;

import java.util.Observable;

import pentago.RuleEngine.IRuleEngine;
import pentago.Game.IMove;
import pentago.Game.IBoard;
import pentago.Game.PentagoState;
import pentago.RuleEngine.IOutcome;

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
public class Model extends Observable implements IModel
{
    protected IOutcome currentOutcome = null;
    /**
     * The rule engine for this model
     */
    protected IRuleEngine ruleEngine = null;

    /**
     * The model has to have a board
     */
     protected IBoard board = null;

     /**
     * default construcut
     * @param ruleEngine IRuleEngine
     */
    public Model(IRuleEngine engine, IBoard brd)
    {
        ruleEngine = engine;
        board = brd;
    }

    /**
     * Returns the rule engine
     * @return IRuleEngine
     */
    public IRuleEngine getRuleEngine()
    {
        return ruleEngine;
    }

    /**
     * Sets the rule engine
     * @param engine IRuleEngine
     */
    public void setRuleEngine(IRuleEngine engine)
    {
        ruleEngine = engine;
    }

    /**
     * gets the current state of the model
     * @return IState
     */
    public IState getModelState()
    {
        currentOutcome = ruleEngine.runRules(this);
        IState state = new PentagoState(this);
        return state;
    }
    
    /**
     * Submits a move to the model..returns true if move was successful
     * @param move IMove
     * @return boolean
     */
    public boolean submitMove(IMove move)
    {
       boolean success = move.makeMove(this);
       if(success) {
           currentOutcome = ruleEngine.runRules(this);
           IState state = new PentagoState(this);
           setChangeNotify(state);
       }
       return success;
    }
    private void setChangeNotify(Object event){
        this.setChanged();
        this.notifyObservers(event);
    }
    /**
     * returns a reference to the board
     * @return IBoard
     */
    public IBoard getBoard()
    {
        return board;
    }

    /**
     * Sets the internal board reference
     * @param brd IBoard
     */
    public void setBoard(IBoard brd)
    {
        board = brd;
    }
}
