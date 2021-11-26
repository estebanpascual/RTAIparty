package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.app.scene.GameSubScene;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class MemorySubScene extends GameSubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    
    public MemorySubScene(Player player, int currentLap, PartyManager partyManager) {
    	super(RTAIpartyApp.WIDTHSIZE, RTAIpartyApp.HEIGHTSIZE);
    	this.player = player;
    	this.currentLap = currentLap;
    	this.partyManager = partyManager;
    	
    	this.getGameWorld().addEntityFactory(new RTAIpartyFactory());
    	
    	Input input = getInput();
    	
    	input.addAction(new UserAction(new String("fin")) {
    		@Override
    		protected void onActionBegin() {
    			System.out.println("fin de partie");
    			getSceneService().popSubScene();
    			partyManager.nextPlayer();
    		}

    	}, KeyCode.E);


    	System.out.println("JEU DE MEMOIRE; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
}