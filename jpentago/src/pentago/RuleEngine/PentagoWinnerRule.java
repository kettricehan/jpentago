package pentago.RuleEngine;

import pentago.Model.IModel;

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

/**
 * The rule to determine if the game has a winner
 */

import pentago.Game.IBoard;
import pentago.Game.IPiece;
import pentago.Game.IPieceLocation;
import pentago.Game.PentagoPieceLocation;

/**
 * This rule determines if there is a winner for the pentago game
 */
public class PentagoWinnerRule implements IRule, IRulePriority
{
    /**
     * Max amount of successive pieces to win
     */
    protected int NUMBER_TO_WIN = 5;

    /**
     * this winner rule priority value
     */
    protected Object priority = null;

    /**
     * Default constructor
     */
    public PentagoWinnerRule()
    {
        super();
    }

    /**
     * Returns the outcome against the current game model
     * <br> the two types of outcomes it returns are the
     * WinnerOutcome and the NoWinnerOutcome
     * @param model IModel
     * @return IOutcome
     */
    public IOutcome getOutcome(IModel model)
    {
        /* defaults to no winner outcomes */
        IOutcome resultOutcome = new NoWinnerOutcome();

        /* get the board from the model*/
        IBoard board = model.getBoard();

        /* by default we have foudn no outcome*/
        boolean foundOutcome = false;

        /* piece allocation*/
        IPiece piece = null;

        /* for all the points within the board */
        for (int i = 0; i < board.getLength() && !foundOutcome; i++)
        {
            for (int j = 0; j < board.getWidth() && !foundOutcome; j++)
            {
                /* get a location object for the current i,j*/
                IPieceLocation loc = new PentagoPieceLocation(i, j);

                /* get the piece from the board */
                piece = board.getPiece(loc);

                if (piece != null)
                {
                    /* get the piece from the board */
                    Object pieceType = piece.getPieceType();

                    /* check to see if there are NUMBER_TO_WIN in a row
                     either vertically, horizontally, or diagonally (left or right) */
                    if (checkDiagonal(pieceType, loc, board) ||
                        checkVertical(pieceType, loc, board) ||
                        checkHorizontal(pieceType, loc, board))
                    {
                        resultOutcome = new WinnerOutcome();
                        foundOutcome = true;
                    }
                }
            }
        }

        /* return result */
        return resultOutcome;
    }

    /**
     * Checks to see if there is a win Diagonally
     * @param pieceType Object
     * @param loc IPieceLocation
     * @param board IBoard
     * @return boolean
     */
    private boolean checkDiagonal(Object pieceType, IPieceLocation loc,
                                  IBoard board)
    {
        /* get the current location */
        int x = loc.getX();
        int y = loc.getY();

        boolean match = true;
        IPieceLocation newLoc = null;
        IPiece newPiece = null;
        Object newPieceType = null;

        int i = x;
        int j = y;

        while(i < x + NUMBER_TO_WIN  && j < y + NUMBER_TO_WIN && match)
        {
            /* get the piece for the current location*/
                newLoc = new PentagoPieceLocation(i, j);
                newPiece = board.getPiece(newLoc);

                /* if the piece is null we have nothing to check against
                 so there couldnt be a win in this configuation*/
                if (newPiece != null)
                {
                    /* if there is a piece see if it equals
                       the piece */
                    newPieceType = newPiece.getPieceType();
                    if (!newPieceType.equals(pieceType))
                    {
                        match = false;
                    }
                }
                else
                {
                    match = false;
                }

            i++;
            j++;
        }

        if(!match)
        {


            i = x;
            j = y;


            while(i > x - NUMBER_TO_WIN  && j > y - NUMBER_TO_WIN && match)
            {
                /* get the piece for the current location*/
                    newLoc = new PentagoPieceLocation(i, j);
                    newPiece = board.getPiece(newLoc);

                    /* if the piece is null we have nothing to check against
                     so there couldnt be a win in this configuation*/
                    if (newPiece != null)
                    {
                        /* if there is a piece see if it equals
                           the piece */
                        newPieceType = newPiece.getPieceType();
                        if (!newPieceType.equals(pieceType))
                        {
                            match = false;
                        }
                    }
                    else
                    {
                        match = false;
                    }

                i--;
                j--;
            }

        }

        return match;
    }

    /**
     * Checks for a win vertically
     * @param pieceType Object
     * @param loc IPieceLocation
     * @param board IBoard
     * @return boolean
     */
    private boolean checkVertical(Object pieceType, IPieceLocation loc,
                                  IBoard board)
    {
        int x = loc.getX();
        int y = loc.getY();

        boolean match = true;
        IPieceLocation newLoc = null;
        IPiece newPiece = null;
        Object newPieceType = null;
        for (int i = x; i < x + NUMBER_TO_WIN && match; i++)
        {
            newLoc = new PentagoPieceLocation(i, y);
            newPiece = board.getPiece(newLoc);

            if (newPiece != null)
            {
                newPieceType = newPiece.getPieceType();
                if (!newPieceType.equals(pieceType))
                {
                    match = false;
                }
            }
            else
            {
                match = false;
            }

        }
        return match;
    }

    /**
     * Checks for a win horizontally
     * @param pieceType Object
     * @param loc IPieceLocation
     * @param board IBoard
     * @return boolean
     */
    private boolean checkHorizontal(Object pieceType, IPieceLocation loc,
                                    IBoard board)
    {
        int x = loc.getX();
        int y = loc.getY();

        boolean match = true;
        IPieceLocation newLoc = null;
        IPiece newPiece = null;
        Object newPieceType = null;

        for (int i = y; i < y + NUMBER_TO_WIN && match; i++)
        {
            newLoc = new PentagoPieceLocation(x, i);
            newPiece = board.getPiece(newLoc);

            if (newPiece != null)
            {
                newPieceType = newPiece.getPieceType();
                if (!newPieceType.equals(pieceType))
                {
                    match = false;
                }
            }
            else
            {
                match = false;
            }
        }

        return match;
    }

    /**
     * Retreive the rule priority
     * @return Object
     */
    public Object getPriority()
    {
        return priority;
    }

    /**
     * Sets the rule priority for the rule
     * @param priorityLevel Object
     */
    public void setPriority(Object priorityLevel)
    {
        priority = priorityLevel;
    }

    /**
     * Compares this rule against another rule priority
     * @param rulePriority IRulePriority
     * @return int
     */
    public int compare(IRulePriority rulePriority)
    {
        /* rule priorities here will be integers*/
        Object otherPriority = rulePriority.getPriority();
        Integer priorityInt = (Integer) getPriority();
        Integer comparePriorityInt = (Integer) otherPriority;

        return priorityInt.compareTo(comparePriorityInt);
    }

}
