package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class MemorySubScene extends GameSubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    boolean isStart;
    Entity startMenu;
    Entity arrow;
    Entity cardSelect;
    
    private Entity createTexte(String text) {
    	
   	 Text view = FXGL.getUIFactoryService().newText(text);
   	 view.setFill(Color.WHITE);
   	 //view.setTranslateY(RTAIpartyApp.HEIGHTSIZE - 2);
   	  
            
       return entityBuilder()
               .zIndex(5)
               .view(view)
               .build();
   }
    
    public static final boolean WIN		= true;
    public static final boolean LOOSE	= false;
    
    
    private Entity createStart() {
    	
      	 Text view = FXGL.getUIFactoryService().newText("C'est au tour de " + this.player.getName() + " sur le jeu de mémoire !" + "\n\nAppuyer sur la touche Entrée pour commencer", Color.WHITE, 30.0);
      	 view.setFill(Color.BLACK);
               
          return entityBuilder()
                  .zIndex(5)
                  .view(view)
                  .build();
      }
       
    
    
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
    			partyManager.nextPlayer(WIN);
    		}

    	}, KeyCode.E);
    	
    	
        getInput().addAction(new UserAction("Start") {
            @Override
            protected void onAction() {
            	
            	if(startMenu != null) {
            		startMenu.removeFromWorld();
            		arrow.removeFromWorld();
            		startMenu = null;
            	}
            	
                isStart = true;
            }
        }, KeyCode.ENTER);
        
        startMenu = createStart();
        startMenu.setX(450);
        startMenu.setY(50);
        
        this.getGameWorld().addEntity(startMenu);
        
        Entity Description = createTexte("Tour de jeu de " + this.player.getName() + "\nTour n°" + this.currentLap);
        Description.setX(70);
        Description.setY(30);
        
        this.getGameWorld().addEntity(Description);
        
        this.getGameWorld().spawn("decordodge",new SpawnData());
        
        Entity carteGBlond 	= this.getGameWorld().spawn("carteGBlond",new SpawnData());
        carteGBlond.setX(300);
        carteGBlond.setY(350);
        
        Entity carteGBrun 	= this.getGameWorld().spawn("carteGBrun",new SpawnData());
        carteGBrun.setX(500);
        carteGBrun.setY(350);
        
        Entity carteFBlond 	= this.getGameWorld().spawn("carteFBlonde",new SpawnData());
        carteFBlond.setX(700);
        carteFBlond.setY(350);
        
        Entity carteFBrune = this.getGameWorld().spawn("carteFBrune",new SpawnData());
        carteFBrune.setX(900);
        carteFBrune.setY(350);
        
        
        Entity arrow = this.getGameWorld().spawn("arrow",new SpawnData());
       this.arrow = arrow;
        
        switch (this.player.getTypePlayer()) {
			case Player.GARCON_BLOND:
				this.cardSelect = carteGBlond;
				 arrow.setX(310);
			     arrow.setY(200);
				break;
				
			case Player.GARCON_BRUN:
				this.cardSelect = carteGBrun;
				arrow.setX(510);
			     arrow.setY(200);
				break;
			
			case Player.FILLE_BLONDE:
				this.cardSelect = carteFBlond;
				arrow.setX(710);
			    arrow.setY(200);
				break;
				
			case Player.FILLE_BRUNE:
				this.cardSelect = carteFBrune;
				arrow.setX(910);
			    arrow.setY(200);
				break;
				
			default:
				break;
		}
        
    	System.out.println("JEU DE MEMOIRE; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
    
    
    private void LooseGame() {
    	System.out.println("fin de partie");
		getSceneService().popSubScene();
		partyManager.nextPlayer(LOOSE);
    }
}