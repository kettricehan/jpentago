package pentago.Management;

import pentago.RuleEngine.IRuleEngine;
import pentago.RuleEngine.RuleEngine;
import pentago.Model.IModel;
import pentago.Model.Model;
import pentago.RuleEngine.RulePrioritizer;
import java.util.Comparator;
import pentago.Game.IBoard;
import pentago.RuleEngine.PentagoWinnerRule;
import pentago.Game.PentagoBoard;
import pentago.Game.PentagoPointMove;
import pentago.Game.PentagoRotationMove;
import pentago.Game.PentagoPieceLocation;
import pentago.Game.IPentagoRotation;

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
public class GameManager
{
    public GameManager()
    {
        Comparator prioritizer = new RulePrioritizer();
        IRuleEngine engine = new RuleEngine(prioritizer);
        engine.addRule(new PentagoWinnerRule());
        IBoard board = new PentagoBoard(6, 6, 3);
        IModel model = new Model(engine, board);

        PentagoPointMove move1 = new PentagoPointMove();
        move1.setX(5);
        move1.setY(5);
        move1.setPieceType(new Character('X'));
        model.submitMove(move1);
        model.getModelState();
        System.out.println(model.getBoard());

        move1 = new PentagoPointMove();
        move1.setX(1);
        move1.setY(1);
        move1.setPieceType(new Character('X'));
        model.submitMove(move1);
        model.getModelState();
        System.out.println(model.getBoard());


        move1 = new PentagoPointMove();
        move1.setX(2);
        move1.setY(1);
        move1.setPieceType(new Character('X'));
        model.submitMove(move1);
        model.getModelState();
        System.out.println(model.getBoard());
        
        move1 = new PentagoPointMove();
        move1.setX(2);
        move1.setY(2);
        move1.setPieceType(new Character('X'));
        model.submitMove(move1);
        model.getModelState();
        System.out.println(model.getBoard());

        move1 = new PentagoPointMove();
        move1.setX(3);
        move1.setY(3);
        move1.setPieceType(new Character('X'));
        model.submitMove(move1);
        model.getModelState();
        System.out.println(model.getBoard());

        move1 = new PentagoPointMove();
        move1.setX(4);
        move1.setY(4);
        move1.setPieceType(new Character('X'));
        model.submitMove(move1);
        model.getModelState();
        System.out.println(model.getBoard());
        

        PentagoRotationMove move4 = new PentagoRotationMove();
        move4.setQuadrant(new PentagoPieceLocation(0, 0));
        move4.setRotation(IPentagoRotation.RIGHT_ROTATION);
        model.submitMove(move4);
        
        model.getModelState();
        System.out.println(model.getBoard());

//
//
//
//
//        for (int i = 0; i < board.getLength(); i++)
//        {
//            for (int j = 0; j < board.getWidth(); j++)
//            {
//
//                PentagoPointMove move1 = new PentagoPointMove();
//                move1.setX(i);
//                move1.setY(j);
//                move1.setPieceType(new Character((char) (counter + 65)));
//                model.submitMove(move1);
//                counter++;
//            }
//        }
//
//        System.out.println(model.getBoard());
////
//        PentagoRotationMove move4 = new PentagoRotationMove();
//        move4.setQuadrant(new PentagoPieceLocation(0, 0));
//        move4.setRotation(IPentagoRotation.RIGHT_ROTATION);
//        model.submitMove(move4);
//
//        move4 = new PentagoRotationMove();
//        move4.setQuadrant(new PentagoPieceLocation(1, 0));
//        move4.setRotation(IPentagoRotation.RIGHT_ROTATION);
//        model.submitMove(move4);
//
//        move4 = new PentagoRotationMove();
//        move4.setQuadrant(new PentagoPieceLocation(0, 1));
//        move4.setRotation(IPentagoRotation.RIGHT_ROTATION);
//        model.submitMove(move4);
//
//        move4 = new PentagoRotationMove();
//        move4.setQuadrant(new PentagoPieceLocation(1, 1));
//        move4.setRotation(IPentagoRotation.RIGHT_ROTATION);
//        model.submitMove(move4);
//
//


    }

    public static void main(String args[])
    {
        GameManager manager = new GameManager();
    }


}
