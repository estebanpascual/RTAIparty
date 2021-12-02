package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxglgames.RTAIparty.components.DodgePlayerComponent;
import com.almasb.fxglgames.RTAIparty.components.DodgeProjectileComponent;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.List;


/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class DodgeSubScene extends GameSubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    int timeCount;
    boolean isStart;
    boolean isFinish;
    Entity startMenu;
    Entity gamePlayer;
    
    public static final boolean WIN		= true;
    public static final boolean LOOSE	= false;
    
    private Entity createPhysicsEntity() {

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(0.7f).restitution(0.3f));
        
        
        Rectangle rectangle = new Rectangle(900, 300);

        rectangle.setFill(Color.LIGHTBLUE);

        
        return entityBuilder()
                .zIndex(2)
                .viewWithBBox(rectangle)
                .with(new CollidableComponent(true))
                .with(physics)
                .build();
    }
    
    
    
    private Entity createTexte(String text) {
    	
    	 Text view = FXGL.getUIFactoryService().newText(text);
    	 view.setFill(Color.WHITE);
    	 //view.setTranslateY(RTAIpartyApp.HEIGHTSIZE - 2);
    	  
             
        return entityBuilder()
                .zIndex(5)
                .view(view)
                .build();
    }
    
    
    private Entity createStart() {
    	
   	 Text view = FXGL.getUIFactoryService().newText("C'est au tour de " + this.player.getName() + " sur le jeu d'esquive !" + "\n\nAppuyer sur la touche Entrée pour commencer", Color.WHITE, 30.0);
   	 view.setFill(Color.BLACK);
   	 //view.setTranslateY(RTAIpartyApp.HEIGHTSIZE - 2);
   	  
       return entityBuilder()
               .zIndex(5)
               .view(view)
               .build();
   }
    
    
    public Entity getPlayer() {
        return this.getGameWorld().getSingleton(RTAIpartyType.DODGE_PLAYER);
    }
    
    public DodgePlayerComponent getPlayerComponent() {
        return getPlayer().getComponent(DodgePlayerComponent.class);
    }
    
    @Override
    public void onUpdate(double tpf) {
    	List<Entity> EntityProjectile = getGameWorld().getEntitiesByComponent(DodgeProjectileComponent.class);
    	for(int i = 0; i < EntityProjectile.size(); i++) {
    		EntityProjectile.get(i).getComponent(DodgeProjectileComponent.class).move();
    		if(this.gamePlayer != null) {
    			if(EntityProjectile.get(i).getComponent(DodgeProjectileComponent.class).checkCollision(this.gamePlayer)) {
    				this.gamePlayer = null;
    				LooseGame();
    			} 
    		}
    	}
    }
    
    
    public DodgeSubScene(Player player, int currentLap, PartyManager partyManager) {
    	
    	super(RTAIpartyApp.WIDTHSIZE, RTAIpartyApp.HEIGHTSIZE);
    	this.player = player;
    	this.currentLap = currentLap;
    	this.partyManager = partyManager;
    	this.isStart = false;
    	this.isFinish = false;
    	
    	this.getInput().addAction(new UserAction("Fin") {
            @Override
            protected void onAction() {
        		System.out.println("fin de partie");
    			getSceneService().popSubScene();
    			partyManager.nextPlayer(LOOSE);
            }
        }, KeyCode.E);
    	
    	
    	
    	this.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().up();            		
            	}
            }
        }, KeyCode.UP);

    	this.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().down();
            	}
            }
        }, KeyCode.DOWN);

    	this.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().left();            		
            	}
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().right();            		
            	}
            }
        }, KeyCode.RIGHT);
        
        getInput().addAction(new UserAction("Start") {
            @Override
            protected void onAction() {
            	
            	if(startMenu != null) {
            		startMenu.removeFromWorld();
            		startMenu = null;
            	}
            	
                isStart = true;
            }
        }, KeyCode.ENTER);
        
        
        this.getGameWorld().addEntityFactory(new RTAIpartyFactory());
        this.getGameWorld().spawn("decordodge",new SpawnData(0, 0));
        this.gamePlayer = this.getGameWorld().spawn("playerDodge",new SpawnData(RTAIpartyApp.WIDTHSIZE / 2, RTAIpartyApp.HEIGHTSIZE / 2));
        
        
        Entity tri = createPhysicsEntity();
        tri.setVisible(false);
        tri.setX(190);
        tri.setY(300);
        
        this.timeCount = 15;
        
        Entity TimeRemaining = createTexte("Temps restant : " + String.valueOf(timeCount));
        TimeRemaining.setX(70);
        TimeRemaining.setY(85);
        
        Entity Description = createTexte("Tour de jeu de " + this.player.getName() + "\nTour n°" + this.currentLap);
        Description.setX(70);
        Description.setY(30);
        
        startMenu = createStart();
        startMenu.setX(450);
        startMenu.setY(50);
        
        this.getGameWorld().addEntity(tri);
        this.getGameWorld().addEntity(TimeRemaining);
        this.getGameWorld().addEntity(Description);
        this.getGameWorld().addEntity(startMenu);
        
        getPlayerComponent().addLimit(tri);
        getPlayerComponent().setSpeed(this.currentLap);
        
        
        
        var pView = texture("garcon_blond_dodge.png");
        switch (this.player.getTypePlayer()) {
			case Player.GARCON_BLOND:
				pView = texture("garcon_blond_dodge.png");
				break;
				
			case Player.GARCON_BRUN:
				pView = texture("garcon_brun_dodge.png");
				break;
			

			case Player.FILLE_BLONDE:
				pView = texture("fille_blonde_dodge.png");
				break;
				
			case Player.FILLE_BRUNE:
				pView = texture("fille_brune_dodge.png");
				break;
				
			default:
				break;
		}
        
        getPlayer().getViewComponent().clearChildren();
        getPlayer().getViewComponent().addChild(pView);
        
        
        
        this.getTimer().runAtInterval(() -> {
        	if(this.isStart && !isFinish) {
	        	Entity projectile = this.getGameWorld().spawn("projectileDodge",new SpawnData(0,0));
	            projectile.getComponent(DodgeProjectileComponent.class).init(this.currentLap);
        	}
        }, Duration.millis(1000 / this.currentLap));
        
        
        
        this.getTimer().runAtInterval(() -> {
        	if(this.isStart && !isFinish) {
	        	this.timeCount--;
	        	 Text view = FXGL.getUIFactoryService().newText("Temps restant : " + String.valueOf(this.timeCount));
	        	 view.setFill(Color.WHITE);
	        	 
	        	TimeRemaining.getViewComponent().clearChildren();
	        	TimeRemaining.getViewComponent().addChild(view);
	        	
	        	if(timeCount == 0) {
	        		WinGame();
	        	}
        	}	
        }, Duration.millis(1000));
        
//        this.getPhysicsWorld().notifySensorCollisionBegin(RTAIpartyType.DODGE_PLAYER, RTAIpartyType.DODGE_PROJECTILE, (p, e) -> onCollisionDodge());
//        onCollision();
        
        this.getPhysicsWorld().addCollisionHandler(new CollisionHandler(RTAIpartyType.DODGE_PLAYER, RTAIpartyType.DODGE_PROJECTILE) {
		      public void onCollisionBegin(Entity a, Entity b) {

		      }
		      public void onCollision(Entity a, Entity b) {
		    	  LooseGame();
		      }
		      public void onCollisionEnd(Entity a, Entity b) {

		      }
        });
        
        
    	System.out.println("JEU ESQUIVE; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
    
    private void LooseGame() {
    	
    	System.out.println("fin de partie");
    	List<Entity> EntityProjectile = getGameWorld().getEntitiesByComponent(DodgeProjectileComponent.class);
    	for(int i = 0; i < EntityProjectile.size(); i++) {
    		EntityProjectile.get(i).removeFromWorld();
    	}
    	
    	isFinish = true;
        this.getGameWorld().spawn("loose",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
    	
    	this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(LOOSE);
    		
    	}, Duration.millis(2000));
    	

    }
    
    private void WinGame() {
    	
    	System.out.println("fin de partie");

    	List<Entity> EntityProjectile = getGameWorld().getEntitiesByComponent(DodgeProjectileComponent.class);
    	for(int i = 0; i < EntityProjectile.size(); i++) {
    		EntityProjectile.get(i).removeFromWorld();
    	}
    	
    	isFinish = true;
        this.getGameWorld().spawn("win",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
    	
    	this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(WIN);
    		
    	}, Duration.millis(2000));
    	

    }
}