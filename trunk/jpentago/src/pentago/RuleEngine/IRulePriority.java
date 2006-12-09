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
public interface IRulePriority {

    /**
     * Returns this rules priority
     * @return Object
     */
    public Object getPriority();

    /**
     * sets the priority level of the rule priority
     * @param priorityLevel Object
     */
    public void setPriority(Object priorityLevel);

    /**
     * Compares this priority against another rule priority
     * @param rulePriority IRulePriority
     * @return int
     */
    public int compare(IRulePriority rulePriority);
}
