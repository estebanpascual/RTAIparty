package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
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
 * @author GROUPE5
 * Scène secondaire pour le jeu d'esquive
 *
 */
public class DodgeSubScene extends GameSubScene {

	//Joueur actuellement entrain de jouer
    Player player;
    
    //Tour actuel
    int currentLap;
    
    //PartyManager
    PartyManager partyManager;
    
    //temps restant
    int timeCount;
    
    //Boolean pour indiquant que le jeu se lance après validation du joueur
    boolean isStart;
    
    //Boolean pour indiqué que la partie est fini
    boolean isFinish;
    
    //Libéllé de lancement de jeu
    Entity startMenu;
    
    //Entité correspondant 
    Entity gamePlayer;
    
    //MACRO de victoire et de défaite
    public static final boolean WIN		= true;
    public static final boolean LOOSE	= false;
    
    
    /**
     * @return
     * Fonction de création de la zone de limite de jeu
     */
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
    
    
    
    /**
     * @param text
     * @return
     * Fonction de création d'entité de texte
     */
    private Entity createTexte(String text) {
    	
    	 Text view = FXGL.getUIFactoryService().newText(text);
    	 view.setFill(Color.WHITE);
             
        return entityBuilder()
                .zIndex(5)
                .view(view)
                .build();
    }
    
    
    /**
     * @return
     * Fonction de création du texte de début de scène
     */
    private Entity createStart() {
    	
   	 Text view = FXGL.getUIFactoryService().newText("C'est au tour de " + this.player.getName() + " sur le jeu d'esquive !" + "\n\nAppuyer sur la touche Entrée pour commencer", Color.WHITE, 30.0);
   	 view.setFill(Color.BLACK);

       return entityBuilder()
               .zIndex(5)
               .view(view)
               .build();
   }
    
    
    /**
     * @return
     * Fonction qui retourne le joueur de la scène
     */
    public Entity getPlayer() {
        return this.getGameWorld().getSingleton(RTAIpartyType.DODGE_PLAYER);
    }
    
    /**
     * @return
     * FOnction qui retourne le component de l'entité du joueur
     */
    public DodgePlayerComponent getPlayerComponent() {
        return getPlayer().getComponent(DodgePlayerComponent.class);
    }
    
    @Override
    public void onUpdate(double tpf) {
    	
    	//Enregistrement de tout les projectiles dans une liste
    	List<Entity> EntityProjectile = getGameWorld().getEntitiesByComponent(DodgeProjectileComponent.class);
    	
    	//Boucle sur la liste des projectiles
    	for(int i = 0; i < EntityProjectile.size(); i++) {
    		
    		//Mouvement des projectiles
    		EntityProjectile.get(i).getComponent(DodgeProjectileComponent.class).move();
    		if(this.gamePlayer != null) {
    			//Si le joueur est en collision avec un projectile
    			if(EntityProjectile.get(i).getComponent(DodgeProjectileComponent.class).checkCollision(this.gamePlayer)) {
    				this.gamePlayer = null;
    				LooseGame();
    			} 
    		}
    	}
    }
    
    
    /**
     * @param player
     * @param currentLap
     * @param partyManager
     * 
     * Constructeur de la scène
     */
    public DodgeSubScene(Player player, int currentLap, PartyManager partyManager) {
    	
    	super(RTAIpartyApp.WIDTHSIZE, RTAIpartyApp.HEIGHTSIZE);
    	this.player = player;
    	this.currentLap = currentLap;
    	this.partyManager = partyManager;
    	this.isStart = false;
    	this.isFinish = false;

    	//En cas d'appui de flèche du haut
    	this.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().up();            		
            	}
            }
        }, KeyCode.UP);

    	
    	//En cas d'appui de flèche du bas
    	this.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().down();
            	}
            }
        }, KeyCode.DOWN);

    	//En cas d'appui de flèche de gauche
    	this.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().left();            		
            	}
            }
        }, KeyCode.LEFT);

    	
    	//En cas d'appui de flèche de droite
        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
            	if(isStart && !isFinish) {
            		getPlayerComponent().right();            		
            	}
            }
        }, KeyCode.RIGHT);
        
        
      //En cas d'appui de touche entrée
        getInput().addAction(new UserAction("Start") {
            @Override
            protected void onAction() {
            	
            	//si le menu du jeu est existant on le supprime
            	if(startMenu != null) {
            		startMenu.removeFromWorld();
            		startMenu = null;
            	}
            	
            	//on active le boolean du jeu
                isStart = true;
            }
        }, KeyCode.ENTER);
        
        
        //Ajout de la factory pour la création d'entitées
        this.getGameWorld().addEntityFactory(new RTAIpartyFactory());
        
        //Création du decor
        this.getGameWorld().spawn("decordodge",new SpawnData(0, 0));
        
        //Création du joueur
        this.gamePlayer = this.getGameWorld().spawn("playerDodge",new SpawnData(RTAIpartyApp.WIDTHSIZE / 2, RTAIpartyApp.HEIGHTSIZE / 2));
        
        //Création de la zone de jeu
        Entity tri = createPhysicsEntity();
        tri.setVisible(false);
        tri.setX(190);
        tri.setY(300);
        
        //Définition du temps restant
        this.timeCount = 15;
        
        //Création de l'entité affichant le temps restant
        Entity TimeRemaining = createTexte("Temps restant : " + String.valueOf(timeCount));
        TimeRemaining.setX(70);
        TimeRemaining.setY(85);
        
        //création de l'entité affichant les info du jeu
        Entity Description = createTexte("Tour de jeu de " + this.player.getName() + "\nTour n°" + this.currentLap);
        Description.setX(70);
        Description.setY(30);
        
        //création du menu de déut de mini-jeux
        startMenu = createStart();
        startMenu.setX(450);
        startMenu.setY(50);
        
        //ajout des éléments à la scène
        this.getGameWorld().addEntity(tri);
        this.getGameWorld().addEntity(TimeRemaining);
        this.getGameWorld().addEntity(Description);
        this.getGameWorld().addEntity(startMenu);
        
        //ajout de la limite de zone de jeu au joueur
        getPlayerComponent().addLimit(tri);
        
        //définition de la vitesse du joueur par rappoort à la difficulté en cours
        getPlayerComponent().setSpeed(this.currentLap);
        
        
        //changement de sprite du joueur en fonction de la sélection du joueur actuel
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
        
        //supression du sprite actuel
        getPlayer().getViewComponent().clearChildren();
        //mise à jour du sprite
        getPlayer().getViewComponent().addChild(pView);
        
        
        //execution de timer de création de projectile
        this.getTimer().runAtInterval(() -> {
        	//la création se lance si la partie est lancé et si elle n'est pas fini
        	if(this.isStart && !isFinish) {
        		//Création du projectile
	        	Entity projectile = this.getGameWorld().spawn("projectileDodge",new SpawnData(0,0));
	        	//Initialisation du component
	            projectile.getComponent(DodgeProjectileComponent.class).init(this.currentLap);
        	}
        }, Duration.millis(1000 / this.currentLap));
        
        
        //execution du timer du temps restant
        this.getTimer().runAtInterval(() -> {
        	if(this.isStart && !isFinish) {
        		//soustraction du temps toute les 1 secondes
	        	this.timeCount--;
	        	
	        	//mise à jour de l'affichage du temps restant
	        	 Text view = FXGL.getUIFactoryService().newText("Temps restant : " + String.valueOf(this.timeCount));
	        	 view.setFill(Color.WHITE);
	        	 
	        	TimeRemaining.getViewComponent().clearChildren();
	        	TimeRemaining.getViewComponent().addChild(view);
	        	
	        	if(timeCount == 0) {
	        		WinGame();
	        	}
        	}	
        }, Duration.millis(1000));
        
        
    	System.out.println("JEU ESQUIVE; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
    
    /**
     * Fonction permetant de finir la partie en défaite
     */
    private void LooseGame() {
    	
    	System.out.println("fin de partie");
    	
    	//supression de tout les projectiles sur la map
    	List<Entity> EntityProjectile = getGameWorld().getEntitiesByComponent(DodgeProjectileComponent.class);
    	for(int i = 0; i < EntityProjectile.size(); i++) {
    		EntityProjectile.get(i).removeFromWorld();
    	}
    	
    	//activation du boolean de fin de mini-jeux
    	isFinish = true;
    	
    	//création du libéllé de défaite
        this.getGameWorld().spawn("loose",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
    	
        //indiquer au partyManager la fin du tour du joueur après 2 secondes
    	this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(LOOSE);
    		
    	}, Duration.millis(2000));
    	

    }
    
    /**
     * Fonction permetant de finir la partie en victoire
     */
    private void WinGame() {
    	
    	System.out.println("fin de partie");

    	//supression de tout les projectiles sur la map
    	List<Entity> EntityProjectile = getGameWorld().getEntitiesByComponent(DodgeProjectileComponent.class);
    	for(int i = 0; i < EntityProjectile.size(); i++) {
    		EntityProjectile.get(i).removeFromWorld();
    	}
    	
    	//activation du boolean de fin de mini-jeux
    	isFinish = true;
    	
    	//création du libéllé de victoire
        this.getGameWorld().spawn("win",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
    	
        //indiquer au partyManager la fin du tour du joueur après 2 secondes
    	this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(WIN);
    		
    	}, Duration.millis(2000));
    }
}