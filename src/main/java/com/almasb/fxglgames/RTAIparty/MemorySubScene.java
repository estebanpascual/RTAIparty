package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class MemorySubScene extends SubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    
    public MemorySubScene(Player player, int currentLap, PartyManager partyManager) {
    	this.player = player;
    	this.currentLap = currentLap;
    	this.partyManager = partyManager;
    	
    	Input input = getInput();
    	
    	input.addAction(new UserAction(new String("fin")) {
    		@Override
    		protected void onActionBegin() {
    			System.out.println("fin de partie");
    			partyManager.nextPlayer();
    		}

    	}, KeyCode.E);
    	
    	
    	Level level = getAssetLoader().loadLevel("RTAIparty_Memory.txt", new TextLevelLoader(32, 32, ' '));
    	getGameWorld().setLevel(level);


    	System.out.println("JEU DE MEMOIRE; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
}