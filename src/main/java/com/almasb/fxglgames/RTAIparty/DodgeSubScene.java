package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxglgames.RTAIparty.components.DodgePlayerComponent;
import com.almasb.fxglgames.RTAIparty.components.DodgeProjectileComponent;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class DodgeSubScene extends GameSubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    
    private Entity createPhysicsEntity() {
        // 1. create and configure physics component
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(0.7f).restitution(0.3f));
        
        
        Rectangle rectangle = new Rectangle(900, 300);
        //Setting the properties of the ellipse


        rectangle.setFill(Color.LIGHTBLUE);

        
        return entityBuilder()
                .zIndex(2)
                .viewWithBBox(rectangle)
                .with(new CollidableComponent(true))
                .with(physics)
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
    	}
    	
    }
    
    
    public DodgeSubScene(Player player, int currentLap, PartyManager partyManager) {
    	
    	super(RTAIpartyApp.WIDTHSIZE, RTAIpartyApp.HEIGHTSIZE);
    	this.player = player;
    	this.currentLap = currentLap;
    	this.partyManager = partyManager;
    	
    	
    	Input input = this.getInput();
    	
    	input.addAction(new UserAction(new String("fin")) {
    		@Override
    		protected void onActionBegin() {
    			System.out.println("fin de partie");
    			getSceneService().popSubScene();
    			partyManager.nextPlayer();
    			
    		}

    	}, KeyCode.E);
    	
    	this.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                getPlayerComponent().up();
            }
        }, KeyCode.UP);

    	this.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                getPlayerComponent().down();
            }
        }, KeyCode.DOWN);

    	this.getInput().addAction(new UserAction("Left") {
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
        
    	

//    	Level level = getAssetLoader().loadLevel("RTAIparty_Dodge.txt", new TextLevelLoader(32, 32, ' '));
//    	getGameWorld().setLevel(level);
        
        this.getGameWorld().addEntityFactory(new RTAIpartyFactory());
        this.getGameWorld().spawn("decordodge",new SpawnData(0, 0));
        this.getGameWorld().spawn("playerDodge",new SpawnData(RTAIpartyApp.WIDTHSIZE / 2, RTAIpartyApp.HEIGHTSIZE / 2));
        
        
        Entity tri = createPhysicsEntity();
        tri.setVisible(false);
        tri.setX(190);
        tri.setY(300);
        

        this.getGameWorld().addEntity(tri);
        
        getPlayerComponent().addLimit(tri);
        getPlayerComponent().setSpeed(this.currentLap);
        
        this.getTimer().runAtInterval(() -> {
        	Entity projectile = this.getGameWorld().spawn("projectileDodge",new SpawnData(0,0));
            projectile.getComponent(DodgeProjectileComponent.class).init(this.currentLap);
        }, Duration.millis(1000 / this.currentLap));
        
    	System.out.println("JEU ESQUIVE; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
}