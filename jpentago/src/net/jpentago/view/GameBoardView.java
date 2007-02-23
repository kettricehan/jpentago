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

import java.net.URL;
import java.util.Comparator;

import pentago.Controller.IPentagoController;
import pentago.Controller.SimplePentagoController;
import pentago.Game.IBoard;
import pentago.Game.PentagoBoard;
import pentago.Model.IModel;
import pentago.Model.Model;
import pentago.ModelListener.IModelListener;
import pentago.ModelListener.JMEModelListener;
import pentago.RuleEngine.IRuleEngine;
import pentago.RuleEngine.PentagoWinnerRule;
import pentago.RuleEngine.RuleEngine;
import pentago.RuleEngine.RulePrioritizer;

import com.jme.app.SimpleGame;
import com.jme.image.Texture;
import com.jme.input.AbsoluteMouse;
import com.jme.input.FirstPersonHandler;
import com.jme.input.InputHandler;
import com.jme.input.MouseInput;
import com.jme.input.NodeHandler;
import com.jme.intersection.BoundingPickResults;
import com.jme.math.FastMath;
import com.jme.math.Quaternion;
import com.jme.math.Ray;
import com.jme.math.Vector2f;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Geometry;
import com.jme.scene.Node;
import com.jme.scene.SceneElement;
import com.jme.scene.Skybox;
import com.jme.scene.Text;
import com.jme.scene.batch.GeomBatch;
import com.jme.scene.state.AlphaState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.MaterialState;
import com.jme.scene.state.RenderState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;

public class GameBoardView extends SimpleGame {

    private Skybox sb = null;
    private MasterGameBoard game = null;
    
    private IRuleEngine engine;
    private IBoard board;
    private Model model;
    private IPentagoController controller;
    private IModelListener modelListener;
    private FirstPersonHandler cameraInputHandler;
    private NodeHandler boardRotateHandler;
    private BoundingPickResults pr;
    private AbsoluteMouse am;
    private AbsoluteMouse amGrabber;
    private TextureState tsPointer;
    private TextureState tsGrabber;
    private Text text;
    private Text crossHair;
    private boolean mouseDown;
    
    @Override
    protected void simpleInitGame() {
        // TODO Auto-generated method stub
        initGameModel();
        
        initMasterGameBoard();        
        
        initGameBoardController();
        
        initGameModelListener();
        
        initSkyBox();
        
        initText();
        
        initInput();
        
        rootNode.updateRenderState();
        rootNode.updateWorldBound();
        
    }
    
    private void initGameModelListener() {
        // Construct by passing in the MasterGameBoard node
        modelListener = new JMEModelListener(game);
        model.addObserver(modelListener);
        
    }

    private void initGameBoardController() {
        controller = new SimplePentagoController(model);
    }

    private void initGameModel()
    {
        Comparator prioritizer = new RulePrioritizer();
        IRuleEngine engine = new RuleEngine(prioritizer);
        engine.addRule(new PentagoWinnerRule());
        board = new PentagoBoard(6, 6, 3);
        model = new Model(engine, board);
        
        
    }

    private void initSkyBox() {

        /** Create a skybox to suround our world */
        sb = new Skybox("skybox", 200, 200, 200);
        URL monkeyLoc = this.getClass().getClassLoader().getResource(
                "jmetest/data/texture/clouds.png");
        TextureState ts = display.getRenderer().createTextureState();
        ts.setTexture(TextureManager.loadTexture(monkeyLoc, Texture.MM_LINEAR,
                Texture.FM_LINEAR));
        sb.setRenderState(ts);

        // Attach the skybox to our root node, and force the rootnode to show
        // so that the skybox will always show
        rootNode.attachChild(sb);
        rootNode.setCullMode(SceneElement.CULL_NEVER);
    }

    private void initMasterGameBoard() {

        game = new MasterGameBoard();
        game.setLocalTranslation(new Vector3f(0.0f, -4f, 0.0f));
        game.setLightCombineMode(LightState.OFF);     
        
        Quaternion xRot = new Quaternion();
        xRot.fromAngleAxis(FastMath.DEG_TO_RAD*90f, new Vector3f(1f, 0f, 0f));
        game.setLocalRotation(xRot);
        rootNode.attachChild(game);   
    }

    private void initInput() {
        /** Create a basic input controller. */
        cameraInputHandler = new FirstPersonHandler(cam, 50, 1);
        cameraInputHandler.setEnabled(false);
        
        boardRotateHandler = new NodeHandler(game, 10f, 5f);
        boardRotateHandler.setEnabled(false);
        
        input = new InputHandler();
        input.addToAttachedHandlers(cameraInputHandler);
        input.addToAttachedHandlers(boardRotateHandler);

        initAbsoluteMouse();
        pr = new BoundingPickResults();
        pr.setCheckDistance(true);
        rootNode.updateRenderState();

    }

    private void initAbsoluteMouse() {
        // Create a new mouse. Restrict its movements to the display screen.
        am = new AbsoluteMouse("The Mouse", display.getWidth(), display
                .getHeight());
        amGrabber = new AbsoluteMouse("The Grabber Mouse", display.getWidth(),
                display.getHeight());

        am.setUsingDelta(true);
        amGrabber.setUsingDelta(true);
        // Get a picture for my mouse.
        tsPointer = display.getRenderer().createTextureState();
        tsGrabber = display.getRenderer().createTextureState();
        URL cursorLoc;
        URL cursorGrabberLoc;
        cursorLoc = GameBoardView.class.getClassLoader().getResource(
                "data/cursor/cursor1.png");
        cursorGrabberLoc = GameBoardView.class.getClassLoader().getResource(
                "data/cursor/cursor_grab.png");
        System.out.println(cursorLoc.getPath());
        Texture t = TextureManager.loadTexture(cursorLoc, Texture.MM_LINEAR,
                Texture.FM_LINEAR);
        Texture tGrabber = TextureManager.loadTexture(cursorGrabberLoc,
                Texture.MM_LINEAR, Texture.FM_LINEAR);

        tsPointer.setTexture(t);
        tsGrabber.setTexture(tGrabber);

        am.setRenderState(tsPointer);

        amGrabber.setRenderState(tsGrabber);
        am.setHotSpotOffset(new Vector3f(-am.getImageWidth() + 6, am
                .getImageHeight() / 2 - 8, 0));
        // Make the mouse's background blend with what's already there
        AlphaState as = display.getRenderer().createAlphaState();
        as.setBlendEnabled(true);
        as.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as.setTestEnabled(true);
        as.setTestFunction(AlphaState.TF_GREATER);

        AlphaState as2 = display.getRenderer().createAlphaState();
        as2.setBlendEnabled(true);
        as2.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        as2.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        as2.setTestEnabled(true);
        as2.setTestFunction(AlphaState.TF_GREATER);

        am.setRenderState(as);
        amGrabber.setRenderState(as2);
        // Get the mouse input device and assign it to the AbsoluteMouse
        // Move the mouse to the middle of the screen to start with
        am.setLocalTranslation(new Vector3f(display.getWidth() / 6, display
                .getHeight() / 6, 0));

        // Assign the mouse to an input handler
        am.registerWithInputHandler(input);
        rootNode.attachChild(amGrabber);
        rootNode.updateRenderState();
        amGrabber.removeFromParent();
        rootNode.attachChild(am);

    }

    @Override
    protected void simpleUpdate() {
        MouseInput mInput = MouseInput.get();
        
        if(mInput.isButtonDown(0)) {
            if(!mouseDown) {
                mouseDown = true;
                mousePickUpdate();
            }
        }
        else {
            mouseDown = false;
        }
        
    }
    private void initText() {
        text = new Text("LabelText", "HELLO");
        crossHair = new Text("Crosshair", "+");

        text.setLocalTranslation(new Vector3f(1, 60, 0));

        fpsNode.attachChild(crossHair);
        fpsNode.attachChild(text);
        fpsNode.updateRenderState();

    }  


    private void mousePickUpdate() {
        Vector2f screenPos = new Vector2f();
        // Get the position that the mouse is pointing to
        screenPos.set(am.getHotSpotPosition().x, am.getHotSpotPosition().y);
        // Get the world location of that X,Y value
        Vector3f worldCoords = display.getWorldCoordinates(screenPos, 0);
        Vector3f worldCoords2 = display.getWorldCoordinates(screenPos, 1);
        //System.out.println(worldCoords);
        // Create a ray starting from the camera, and going in the direction
        // of the mouse's location
        Ray mouseRay = new Ray(worldCoords, worldCoords2.subtractLocal(
                worldCoords).normalizeLocal());
        // Does the mouse's ray intersect the box's world bounds?
        pr.clear();
        rootNode.findPick(mouseRay, pr);
        StringBuffer pickStatus = new StringBuffer();
        boolean updateRenderStateNeeded = false;
        
        for (int i = 0; i < pr.getNumber(); i++) {
            GeomBatch targetMesh = pr.getPickData(i).getTargetMesh();
            Geometry parentGeom = targetMesh.getParentGeom();
            Node parentNode = parentGeom.getParent();
            
            //Only include the : to append additional collisions
            pickStatus.append((i>0 ? " : " : "") + parentNode.getName());
            //Handle GameCell click
            if(parentNode instanceof GameCell) {
                handleGameCellClick((GameCell)parentNode);
            }
            //Handle GameBoard click
            if(parentNode instanceof GameBoard) {
                handleGameBoardClick((GameBoard)parentNode);
            }

        }
        text.print("MousePick: " + pickStatus);

        if (updateRenderStateNeeded) {
            rootNode.updateRenderState();
        }
    }
    
    private void handleGameBoardClick(GameBoard board2) {
        // TODO Handle rotation of the GameBoard
        
    }

    private void handleGameCellClick(GameCell cell) {
        // TODO handle clicking of a game cell
  
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {    
        SimpleGame app = new GameBoardView();
        app.setDialogBehaviour(SimpleGame.FIRSTRUN_OR_NOCONFIGFILE_SHOW_PROPS_DIALOG);
        app.start();

    }

}
