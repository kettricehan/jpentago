package pentago.RuleEngine;

import java.util.Comparator;

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

import pentago.RuleEngine.IRulePriority;

public class RulePrioritizer implements Comparator {

    /* constants for running the comparison */
    private final int LEFT_HAND_GREATER = -1;
    private final int RIGHT_HAND_GREATER = 1;
    private final int EQUIVOLENT = 0;

    /**
     * Default constructor
     */
    public RulePrioritizer()
    {

    }
    /**
     * returns an integer values based upon the comparison
     * of the rules
     * @param lhs Object
     * @param rhs Object
     * @return int
     */
    public int compare(Object lhs, Object rhs)
     {
        int returnValue = 0;

        /**
         * If both the left and right hand side are have rule priorities
         * then compare the priorites
         */
        if(lhs instanceof IRulePriority && rhs instanceof IRulePriority)
        {
            IRulePriority lhsPriority = (IRulePriority)lhs;
            IRulePriority rhsPriority = (IRulePriority)rhs;

            returnValue = lhsPriority.compare(rhsPriority);
        }
        /* if the left hadn side is prioritized and the right is not,
         then the left has priority over the right*/
        else if(lhs instanceof IRulePriority && ! ( rhs instanceof IRulePriority))
        {
           returnValue = LEFT_HAND_GREATER;
        }
        /* if the right hand side has priority and the left does not, then the
         right takes priority*/
        else if( !(lhs instanceof IRulePriority) && rhs instanceof IRulePriority)
        {
            returnValue = RIGHT_HAND_GREATER;
        }
        /* if neither have a priority, then they are basically equivolent*/
        else
        {
            returnValue = EQUIVOLENT;
        }

        return returnValue;
     }
}
