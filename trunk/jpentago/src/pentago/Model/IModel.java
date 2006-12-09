package pentago.Model;

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
import java.util.Observable;

import pentago.RuleEngine.IRuleEngine;
import pentago.Model.IState;
import pentago.Game.IMove;
import pentago.Game.IBoard;

public interface IModel {

     public IRuleEngine getRuleEngine();
     public void setRuleEngine(IRuleEngine ruleEngine);
     public IState getModelState();
     public boolean submitMove(IMove move);
     public IBoard getBoard();
     public void setBoard(IBoard board);
}
