package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxglgames.RTAIparty.components.DodgePlayerComponent;
import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGL.*;


/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class DodgeSubScene extends SubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    
    
    public Entity getPlayer() {
        return getGameWorld().getSingleton(RTAIpartyType.DODGE_PLAYER);
    }
    
    public DodgePlayerComponent getPlayerComponent() {
        return getPlayer().getComponent(DodgePlayerComponent.class);
    }
    
 
    public DodgeSubScene(Player player, int currentLap, PartyManager partyManager) {
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
    	
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                getPlayerComponent().up();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                getPlayerComponent().down();
            }
        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                getPlayerComponent().left();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                getPlayerComponent().right();
            }
        }, KeyCode.RIGHT);
        
    	
        
        
    	Level level = getAssetLoader().loadLevel("RTAIparty_Dodge.txt", new TextLevelLoader(32, 32, ' '));
    	getGameWorld().setLevel(level);
    	



    	System.out.println("JEU ESQUIVE; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
}