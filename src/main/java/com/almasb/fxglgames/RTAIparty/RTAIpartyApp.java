/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.almasb.fxglgames.RTAIparty;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import ui.RTAISceneFactory;
import ui.RTAIpartyMainMenu;

import java.util.ArrayList;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;


/**
 * This is a basic demo of Pacman.
 *
 * Assets taken from opengameart.org
 * (Carlos Alface 2014 kalface@gmail.com, http://c-toy.blogspot.pt/).
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class RTAIpartyApp extends GameApplication {


	public static final int BLOCK_SIZE = 32;

    public static final int MAP_SIZE = 21;

    public static final int WIDTHSIZE = 1280;
    public static final int HEIGHTSIZE = 720;
    
    public PartyManager partyManager;
    
    // seconds
    public static final int TIME_PER_LEVEL = 100;
    
    
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(MAP_SIZE * BLOCK_SIZE);
        settings.setHeight(MAP_SIZE * BLOCK_SIZE);
        settings.setWidth(WIDTHSIZE);
        settings.setHeight(HEIGHTSIZE);
        settings.setTitle("RTAIparty");
        settings.setVersion("1.0");
        settings.setMainMenuEnabled(true);
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
        
        RTAISceneFactory SceneFactoryEntity = new RTAISceneFactory(this);
        settings.setSceneFactory(SceneFactoryEntity);
    }

    @Override
    protected void initInput() {
    	
    	Input input = getInput();
    	
    	input.addAction(new UserAction("clic") {
    		@Override
    		protected void onActionBegin() {
    			
    			System.out.println("un clic");
    		}
    	}, MouseButton.PRIMARY);
        
    	
    	input.addAction(new UserAction(new String("test")) {
    		@Override
    		protected void onActionBegin() {
    			System.out.println("un appui de touche e");
    		}
    	}, KeyCode.E);
    }
   
 
    @Override
    protected void initGameVars(Map<String, Object> vars) {
//        vars.put("score", 0);
//        vars.put("coins", 0);
//        vars.put("teleport", 0);
//        vars.put("time", TIME_PER_LEVEL);
    }
    
    
    
    @Override
    protected void initGame() {
    	
    }
    
    public void startGame(ArrayList<Player> players) {
    	this.partyManager = new PartyManager(players);
    	this.partyManager.startGame();
    	
    }

	public static void newParty() {

//    	
//    	System.out.println(getGameScene().getRoot().getChildren());
    	
    }

    public static void scoreBoard() {
    	System.out.println("Tableau des scores !");
    	//getGameWorld().addEntityFactory(RTAIpartyFactoryEntity);
    	//getGameScene().getRoot().getChildren().clear();
//    	getGameScene().getRoot().getChildren().remove(5);
//    	getGameWorld().addEntityFactory(RTAIpartyFactoryEntity);
//    	Level level = getAssetLoader().loadLevel("RTAIparty_level0.txt", new TextLevelLoader(32, 32, ' '));
//    	getGameWorld().setLevel(level);
//    	
//    	System.out.println(getGameScene().getRoot().getChildren());
    	
    }
    
    @Override
    protected void initPhysics() {

    }

    
    @Override
    protected void initUI() {

    }
    
    @Override
    protected void onUpdate(double tpf) {    	
    	//System.out.println("update !");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
