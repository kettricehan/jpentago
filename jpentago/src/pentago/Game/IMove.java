package pentago.Game;

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

public interface IMove {

    /**
     * Actually does the move on the model
     * @param model IModel
     * @return boolean
     */
    public boolean makeMove(IModel model);
}
