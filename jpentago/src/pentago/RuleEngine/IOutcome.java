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
public interface IOutcome {

    /**
     * Compares outcomes
     * @param rhs IOutcome
     * @return IOutcome
     */
    public IOutcome compareOutcomes(IOutcome rhs);

}
