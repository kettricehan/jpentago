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
import java.util.Comparator;

public interface IPriorityRuleEngine {

    /**
     * Runs the rules by the given priority
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome runRulesByPriority(IModel model);

    /**
     * sets the rule prioritizer
     * @param prioritizer Comparator
     */
    public void setRulePrioritizer(Comparator prioritizer);

    /**
     * Returns the rule prioritizer
     * @return Comparator
     */
    public Comparator getRulePrioritizer();
}
