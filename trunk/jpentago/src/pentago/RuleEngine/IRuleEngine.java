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
import pentago.RuleEngine.IRule;
import pentago.Model.IModel;

public interface IRuleEngine {

    /**
     * Runs the given rule against the given model
     * @param rule IRule
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome runRule(IRule rule, IModel model);

    /**
     * adds a rule to the rule engine
     * @param rule IRule
     */
    public void addRule(IRule rule);

    /**
     * Runs the rules in the ruleEngine
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome runRules(IModel model);
}
