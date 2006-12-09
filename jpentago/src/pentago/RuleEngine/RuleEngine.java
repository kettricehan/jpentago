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

import pentago.RuleEngine.IRuleEngine;
import pentago.RuleEngine.IPriorityRuleEngine;
import pentago.RuleEngine.IRule;
import pentago.Model.IModel;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * This is the game rule engine. <br> It accepts rules and runs the rules
 * against the model. It then decides what the outcome of the rules are.
 */
public class RuleEngine implements IRuleEngine, IPriorityRuleEngine {

    /**
     * An arraylists of the rules for the engine
     */
    protected ArrayList rulesList = null;

    /**
     * A possible comparator if applicable
     */
    protected Comparator prioritizer = null;

    /**
     * Default constructor
     */
    public RuleEngine()
    {
        rulesList = new ArrayList();
    }

    /**
     *  One argument constructor
     * @param comparer Comparator
     */
    public RuleEngine(Comparator comparer)
    {
        this();
        prioritizer = comparer;
    }

    /**
     * Rules the specific rule against the model
     * @param rule IRule
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome runRule(IRule rule, IModel model) {
        return rule.getOutcome(model);
    }

    /**
     * Adds a rule into the rule engine
     * @param rule IRule
     */
    public void addRule(IRule rule)
    {
        rulesList.add(rule);
    }

    /**
     * Runs the rules stored in the rule engine against
     * the supplied model - rules are not applied in priority
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome runRules(IModel model) {

        /**
         * get the size of the rules
         */
        int ruleCount = rulesList.size();

        /**
         * the current and end result outcomes
         */
        IOutcome resultOutcome = null;
        IOutcome currentOutcome = null;

        /* determine the outcomes against the rules */
        for(int i = 0; i < ruleCount; i++)
        {
            /* run a rule against the rulelist */
            IRule rule = (IRule)rulesList.get(i);

            /* get the outcome from the rule*/
            currentOutcome = runRule(rule, model);

            /* get the resulting outcome from the rule*/
            if(resultOutcome == null)
            {
                resultOutcome = currentOutcome;
            }
            else
            {
                resultOutcome = resultOutcome.compareOutcomes(currentOutcome);
            }
        }

        /* return the resulting outcome*/
        return resultOutcome;
    }

    /**
     * Runs the rules in priority order using the seeded rule prioritizer if
     * set
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome runRulesByPriority(IModel model)
    {
        if(prioritizer != null)
        {
            java.util.Collections.sort(rulesList, getRulePrioritizer());
        }
        return runRules(model);
    }

    /**
     * Sets the rule prioritizer
     * @param prioritizer Comparator
     */
    public void setRulePrioritizer(Comparator prioritizer) {
        this.prioritizer = prioritizer;
    }

    /**
     * Returns the current rule prioritizer
     * @return Comparator
     */
    public Comparator getRulePrioritizer() {
        return prioritizer;
    }

}
