package pentago.RuleEngine;

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
import pentago.Model.IModel;

public interface IRule {

    /**
     * Returns the outcome when run on the given model
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome getOutcome(IModel model);

}
