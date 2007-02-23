/*
 * Copyright (c) 2003-2006 JPentago
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'JPentago' nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


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

                GameCell cell = new GameCell(this, cellWidth, cellHeight);
                cell.setName("cell("+i+","+j+")");
                System.out.println(cell.getName());
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
