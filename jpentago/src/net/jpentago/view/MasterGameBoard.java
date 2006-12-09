package net.jpentago.view;

import java.util.ArrayList;
import java.util.Iterator;

import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.shape.Sphere;

public class MasterGameBoard extends Node{
    
    ArrayList<GameBoard> gameBoards = new ArrayList<GameBoard>();
    
    private static final int DEFAULT_GAMEBOARD_ROWS = 2;
    private static final int DEFAULT_GAMEBOARD_COLS = 2;
    
    public MasterGameBoard() {
        initGameBoards(DEFAULT_GAMEBOARD_ROWS, DEFAULT_GAMEBOARD_COLS);
        initDebugInfo();
        
    }
    /**
     * Sets the given game piece in the appropriate cell.
     * @param cell
     * @param piece
     */
    public void setPiece(GameCell cell, GamePiece piece) {
        Iterator<GameBoard> i = gameBoards.iterator();
        int index;
        GameBoard gb;
        while(i.hasNext()) {
            gb = i.next();
            index = gb.getChildIndex(cell);
            if(index >= 0) {
                gb.setPiece(cell, piece);
            }
        }
    }
    
    /**
     * Clear out all pieces on the gameboards.
     *
     */
    public void clearPieces() {
        Iterator<GameBoard> i = gameBoards.iterator();
        int index;
        GameBoard gb;
        while(i.hasNext()) {
            gb = i.next();
            gb.clearPieces();
        }
    }
    
    private void initDebugInfo() {
        Sphere s = new Sphere("DebugSphere", 16, 16 ,1.0f );
        this.attachChild(s);
    }
    private void initGameBoards(int rows, int cols)
    {
        GameBoard g;
        float dx;
        float dz;
        Vector3f transVector;
        for(int i=0; i<rows; i++) {
           for(int j=0; j<cols; j++) {              
               g = new GameBoard();
               float boardWidth = g.getBoardWidth()+g.getCellWidth();
               float boardHeight = g.getBoardHeight()+g.getCellHeight();
               //Translate boards so that MasterGameBoard is centered at 0,0,0
               dx = i*(boardWidth) - rows*boardWidth/2;
               dz = j*(boardHeight) - cols*boardHeight/2;
               transVector = new Vector3f(dx, 0.0f, dz);
               System.out.println("Board location=" + transVector);
               g.setLocalTranslation(transVector); 
               
               gameBoards.add(g);
               this.attachChild(g);
           }
        }
    }

    
    
}
