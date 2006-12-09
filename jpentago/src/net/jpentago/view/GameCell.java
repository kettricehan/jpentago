package net.jpentago.view;

import java.net.URL;

import jmetest.TutorialGuide.HelloKeyInput;

import com.jme.bounding.OrientedBoundingBox;
import com.jme.image.Texture;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.TriMesh;
import com.jme.scene.shape.Box;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;

public class GameCell extends Node {

    private float cellWidth;
    private float cellHeight;
    private TriMesh cell;
    private TriMesh cellTop;
    private TextureState cellTexture;
    private TextureState cellTopTexture;
    private AlphaState cellTopAlphaState;
    
    private DisplaySystem display = DisplaySystem.getDisplaySystem();
    
    public GameCell(float cellWidth, float cellHeight) {
        this.cellWidth=cellWidth;
        this.cellHeight=cellHeight;
        
        initGameCell();
        
        this.updateRenderState();
    }

    public void setName(String cellName) {
        cell.setName(cellName);
    }
    
    private void initGameCell() {
        cell = getCellMesh();
        cell.setRenderState(getCellTexture());
        
        //attach to node
        this.attachChild(cell);
        /*
        cellTop = getCellTopMesh();
        
        cellTop.setRenderState(getCellTopTexture());
        cellTop.setRenderState(getCellTopAlphaState());
        //translate to the top of the square
        cellTop.setLocalTranslation(new Vector3f(0f, cellHeight/2, 0f));
        //attach to node
        this.attachChild(cellTop);
        */
        
       
        
    }

    
    private AlphaState getCellTopAlphaState() {
        if (cellTopAlphaState==null) {
                
            // Create an alpha state.
            AlphaState as = display.getRenderer().createAlphaState();
            // Blend between the source and destination functions.
            as.setBlendEnabled(true);
            // Set the source function setting.
            as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
            // Set the destination function setting.
            as.setDstFunction(AlphaState.SB_ONE_MINUS_SRC_ALPHA);
            // Enable the test (?  yea, I don't know ?)
            as.setTestEnabled(true);
            // Set the test function to TF_GREATER.
            as.setTestFunction(AlphaState.TF_GREATER);
            // Assign the alpha state to the powerEnclosure's render state.
            cellTopAlphaState = as;

        }
        return cellTopAlphaState;
    }

    private TextureState getCellTexture() {
        if(cellTexture == null) {
            URL textureLoc=this.getClass().getClassLoader().getResource("resources/textures/wood_43.jpg");
            // Get my TextureState
            TextureState ts =  display.getRenderer().createTextureState();
            // Get my Texture
            Texture t=TextureManager.loadTexture(textureLoc,Texture.MM_LINEAR, Texture.FM_LINEAR);
            // Set a wrap for my texture so it repeats
            t.setWrap(Texture.WM_WRAP_S_WRAP_T);
            // Set the texture to the TextureState
            ts.setTexture(t);
            cellTexture = ts;
        }
        return cellTexture;
    }
    private TextureState getCellTopTexture() {
        if(cellTopTexture == null) {
            //URL textureLoc=this.getClass().getClassLoader().getResource("resources/textures/circleMark.png");
            URL textureLoc=this.getClass().getClassLoader().getResource("resources/textures/as_ex_lightning.png");
            
            // Get my TextureState
            TextureState ts =  display.getRenderer().createTextureState();
            // Get my Texture
            Texture t=TextureManager.loadTexture(textureLoc,Texture.MM_LINEAR, Texture.FM_LINEAR, 20f, false);        
            
            t.setApply(Texture.AM_ADD);
            t.setCombineFuncRGB(Texture.ACF_ADD_SIGNED);
            t.setCombineSrc0RGB(Texture.ACS_TEXTURE);
            t.setCombineOp0RGB(Texture.ACO_SRC_COLOR);
            t.setCombineSrc1RGB(Texture.ACS_PREVIOUS);
            t.setCombineOp1RGB(Texture.ACO_SRC_COLOR);
            t.setCombineScaleRGB(1.0f);
            t.setWrap(Texture.WM_BCLAMP_S_BCLAMP_T);
            t.setRotation(new Quaternion(10f,10f,10f,10f));
            
            ts.setCorrection(TextureState.CM_PERSPECTIVE);
            ts.setTexture(t);
            cellTopTexture = ts;
        }
        
        return cellTopTexture;
    }
    private TriMesh getCellMesh() {
        TriMesh mesh = new Box("cell", new Vector3f(0f, 0f, 0f), cellWidth, cellHeight/2, cellHeight);
        mesh.setModelBound(new OrientedBoundingBox());
        mesh.updateModelBound();
        
        return mesh;
    }
    
    private TriMesh getCellTopMesh() {
        TriMesh mesh = new Quad("cellTopQuad", cellWidth, cellHeight);
        
        mesh.rotateUpTo(new Vector3f(0f, 0f, 1.0f));
        mesh.setModelBound(new OrientedBoundingBox());
        mesh.updateModelBound();
        return mesh;
    }

}
