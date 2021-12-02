package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxglgames.RTAIparty.components.RythmNumberComponent;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.List;
import java.util.Random;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class RythmSubScene extends GameSubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    public static final boolean WIN		= true;
    public static final boolean LOOSE	= false;
    
    int timeCount;
    boolean isStart;
    boolean isFinish;
    Entity startMenu;
    Entity actualNumber;
    
    
    private Entity createTexte(String text) {
    	
   	 Text view = FXGL.getUIFactoryService().newText(text);
   	 view.setFill(Color.BLACK);
            
       return entityBuilder()
               .zIndex(5)
               .view(view)
               .build();
   }
   
   
   private Entity createStart() {
   	
  	 Text view = FXGL.getUIFactoryService().newText("C'est au tour de " + this.player.getName() + " sur le jeu de rythme !" + "\nAppuyer sur la touche Entrée pour commencer", Color.WHITE, 20.0);
  	 view.setFill(Color.BLACK);
           
      return entityBuilder()
              .zIndex(5)
              .view(view)
              .build();
  }
   
   
    public RythmSubScene(Player player, int currentLap, PartyManager partyManager) {
    	super(RTAIpartyApp.WIDTHSIZE, RTAIpartyApp.HEIGHTSIZE);
    	this.player = player;
    	this.currentLap = currentLap;
    	this.partyManager = partyManager;
    	this.isStart = false;
    	this.timeCount = 15;
    	
    	
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
    	
    	this.getInput().addAction(new UserAction("number1") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 1) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT1);
    	
    	
    	
    	this.getInput().addAction(new UserAction("number2") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 2) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT2);
    	
    	
    	this.getInput().addAction(new UserAction("number3") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 3) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT3);
    	
    	
    	this.getInput().addAction(new UserAction("number4") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 4) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT4);
    	
    	this.getInput().addAction(new UserAction("number5") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 5) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT5);
    	
    	
    	this.getInput().addAction(new UserAction("number6") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 6) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT6);
    	
    	
    	this.getInput().addAction(new UserAction("number7") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 7) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT7);
    	
    	
    	this.getInput().addAction(new UserAction("number8") {
            @Override
            protected void onAction() {
            	
            	if(isStart && !isFinish) {
            		if(actualNumber != null) {
            			if(actualNumber.getComponent(RythmNumberComponent.class).getNumber() == 8) {
            				actualNumber.getComponent(RythmNumberComponent.class).setActive(false);
            			}else {
            				LooseGame();
            			}            			
            		}
            	}
            	
            }
        }, KeyCode.DIGIT8);
    	
    	
    	this.getInput().addAction(new UserAction("Start") {
            @Override
            protected void onAction() {
            	
            	if(startMenu != null) {
            		startMenu.removeFromWorld();
            		startMenu = null;
            	}
            	
                isStart = true;
            }
        }, KeyCode.ENTER);
    	
    	
    	
    	this.getGameWorld().spawn("decor",new SpawnData(0, 0));
    	
    	Entity num1 = this.getGameWorld().spawn("numero1", new SpawnData());
    	num1.setX(250);
    	num1.setY(120);
    	
    	Entity num2 = this.getGameWorld().spawn("numero2", new SpawnData());
    	num2.setX(450);
    	num2.setY(120);
    	
    	Entity num3 = this.getGameWorld().spawn("numero3", new SpawnData());
    	num3.setX(650);
    	num3.setY(120);
    	
    	Entity num4 = this.getGameWorld().spawn("numero4", new SpawnData());
    	num4.setX(850);
    	num4.setY(120);
    	
    	Entity num5 = this.getGameWorld().spawn("numero5", new SpawnData());
    	num5.setX(250);
    	num5.setY(400);
    	
    	Entity num6 = this.getGameWorld().spawn("numero6", new SpawnData());
    	num6.setX(450);
    	num6.setY(400);
    	
    	Entity num7 = this.getGameWorld().spawn("numero7", new SpawnData());
    	num7.setX(650);
    	num7.setY(400);
    	
    	Entity num8 = this.getGameWorld().spawn("numero8", new SpawnData());
    	num8.setX(850);
    	num8.setY(400);
    	
    	List<Entity> EntityNumber = getGameWorld().getEntitiesByComponent(RythmNumberComponent.class);
    	for(int i = 0; i < EntityNumber.size(); i++) {
    		EntityNumber.get(i).getComponent(RythmNumberComponent.class).init();
    	}
    	
    	
    	Entity TimeRemaining = createTexte("Temps restant : " + String.valueOf(timeCount));
    	TimeRemaining.setX(10);
    	TimeRemaining.setY(125);
    	
    	Entity Description = createTexte("Tour de jeu de " + this.player.getName() + "\nTour n°" + this.currentLap);
    	Description.setX(10);
    	Description.setY(70);
    	
        startMenu = createStart();
        startMenu.setX(10);
        startMenu.setY(670);

        this.getGameWorld().addEntity(TimeRemaining);
        this.getGameWorld().addEntity(Description);
        this.getGameWorld().addEntity(startMenu);
        
        
        Random randomGenerator = new Random();
        List<Entity> EntityNumberRandom = getGameWorld().getEntitiesByComponent(RythmNumberComponent.class);
        
        this.getTimer().runAtInterval(() -> {
        	if(this.isStart && !isFinish) {
        		
        		if(actualNumber != null) {
        			if(actualNumber.getComponent(RythmNumberComponent.class).getIsActive()) {
        				LooseGame();
        			}
        		}
        		
        		
        		
        		for(int i = 0; i < EntityNumberRandom.size(); i++) {
        			EntityNumberRandom.get(i).getComponent(RythmNumberComponent.class).setActive(false);
            	}
        		
        		int index = randomGenerator.nextInt(EntityNumberRandom.size());
        		EntityNumber.get(index).getComponent(RythmNumberComponent.class).setActive(true);
        		this.actualNumber = EntityNumber.get(index);
        	}
        }, Duration.millis(4000 / this.currentLap));
        
        
        
        this.getTimer().runAtInterval(() -> {
        	if(this.isStart && !isFinish) {
	        	this.timeCount--;
	        	 Text view = FXGL.getUIFactoryService().newText("Temps restant : " + String.valueOf(this.timeCount));
	        	 view.setFill(Color.BLACK);
	        	 
	        	TimeRemaining.getViewComponent().clearChildren();
	        	TimeRemaining.getViewComponent().addChild(view);
	        	
	        	if(timeCount == 0) {
	        		WinGame();
	        	}
        	}
        }, Duration.millis(1000));
    	
    	System.out.println("JEU DE RYTHME; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
    
    private void LooseGame() {
    	isFinish = true;
		this.getGameWorld().spawn("loose",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
		
		this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(LOOSE);
    		
    	}, Duration.millis(2000));
		
    }
    
    private void WinGame() {
    	isFinish = true;
		this.getGameWorld().spawn("win",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
		
		this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(WIN);
    		
    	}, Duration.millis(2000));
		
    }
}