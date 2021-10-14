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
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
//import com.almasb.fxglgames.pacman.components.PlayerComponent;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
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
    
    RTAIpartyFactory RTAIpartyFactoryEntity = new RTAIpartyFactory();

    // seconds
    public static final int TIME_PER_LEVEL = 100;

//    public Entity getPlayer() {
//        return getGameWorld().getSingleton(PLAYER);
//    }

//    public PlayerComponent getPlayerComponent() {
//        return getPlayer().getComponent(PlayerComponent.class);
//    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(MAP_SIZE * BLOCK_SIZE);
        settings.setHeight(MAP_SIZE * BLOCK_SIZE);
        settings.setTitle("RTAIparty");
        settings.setVersion("1.0");
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
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

        getGameWorld().addEntityFactory(RTAIpartyFactoryEntity);

        Level level = getAssetLoader().loadLevel("RTAIparty_level0.txt", new TextLevelLoader(32, 32, ' '));
        getGameWorld().setLevel(level);
        
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
