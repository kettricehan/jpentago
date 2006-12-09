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
public class WinnerOutcome implements IOutcome
{
    public WinnerOutcome()
    {
        super();
    }

    /**
     * Compares this outcome against another to see which
     * is more important
     * @param rhs IOutcome
     * @return IOutcome
     */
    public IOutcome compareOutcomes(IOutcome rhs)
    {
        return this;
    }
}
