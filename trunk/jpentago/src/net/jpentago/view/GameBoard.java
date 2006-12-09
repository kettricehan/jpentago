package net.jpentago.view;

import java.util.ArrayList;
import java.util.Iterator;

import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Spatial;

public class GameBoard extends Node {

    private static final float DEFAULT_CELL_WIDTH = 1.0f;
    private static final float DEFAULT_CELL_HEIGHT = 1.0f;
    private static final int DEFAULT_CELL_COLS = 3;
    private static final int DEFAULT_CELL_ROWS = 3;
    
    private float cellWidth = DEFAULT_CELL_WIDTH;
    private float cellWidthBuffer = 2.5f;
    private float cellHeight = DEFAULT_CELL_HEIGHT;
    private float cellHeightBuffer = 2.5f;
    
    private int cellRows = DEFAULT_CELL_ROWS;
    private int cellCols = DEFAULT_CELL_COLS;
    
    private ArrayList<GameCell> gameCells = new ArrayList<GameCell>();
    private Node gamePieces = new Node("GamePieces");
    
    public GameBoard() {
        initGameBoard();
    }
    
    private void initGameBoard() {
        for(int i=0; i<cellRows; i++) {
            for(int j=0; j<cellCols; j++) {
                System.out.println("cell("+i+","+j+")");
                GameCell cell = new GameCell(cellWidth, cellHeight);
                //translate so that cells oriented with center cell is the center of gameboard
                cell.setLocalTranslation(new Vector3f(i*(cellWidth*cellWidthBuffer), 0f,  j*(cellHeight*cellHeightBuffer)));
                gameCells.add(cell);
                this.attachChild(cell);
            }
        }
    }
    
    public float getBoardWidth() {
        // TODO Auto-generated method stub
        return cellRows *(cellWidth*cellWidthBuffer);
    }
    
    public float getBoardHeight() {
        // TODO Auto-generated method stub
        return cellCols * (cellHeight*cellHeightBuffer);
    }

    public float getCellHeight() {
        // TODO Auto-generated method stub
        return cellHeight;
    }
    public float getCellWidth() {
        // TODO Auto-generated method stub
        return cellWidth;
    }
    /**
     * Sets the given piece to match the position of the given cell.
     * @param cell
     * @param piece
     */
    public void setPiece(GameCell cell, GamePiece piece) {
        piece.setLocalTranslation(new Vector3f(cell.getLocalTranslation()));
        piece.setLocalRotation(new Quaternion(cell.getLocalRotation()));
        gamePieces.attachChild(piece);
    }
    
    /**
     * Clear out all pieces on the gameboard.
     *
     */
    public void clearPieces() {
        Iterator<Spatial> i = gamePieces.getChildren().iterator();
        
        while(i.hasNext()) {
            Spatial s = i.next();
            s.removeFromParent();       
        }
    }

}
