package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxglgames.RTAIparty.components.MemoryCardComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

/**
 * @author GROUPE57
 * Scène secondaire pour le jeu de mémoire
 */
public class MemorySubScene extends GameSubScene {

	//Joueur actuellement entrain de jouer
    Player player;
    
    //Tour actuel
    int currentLap;
    
    //PartyManager
    PartyManager partyManager;
    
    //Boolean pour indiquant que le jeu se lance après validation du joueur
    boolean isStart;
    
    //Boolean pour indiqué que la partie est fini
    boolean isFinish;
    
    //Libéllé de lancement de jeu
    Entity startMenu;
    
    //Entité de la flèche
    Entity arrow;
    
    //carte du joueur
    Entity cardSelect;
    
    //Activation de l'action du joueur
    boolean playerTurn;
    
    //Nombre de mélange de carte
    int nbTurn;
    
    //Liste des cartes
    List<Entity> EntityCards;
    
    Entity GBlond;
    Entity GBrun;
    Entity FBlonde;
    Entity FBrune;
    
    /**
     * @param text Texte qui sera contenu dans notre entité
     * @return Retourn l'entité contenant le texte
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
     * Macro de victoire
     */
    public static final boolean WIN		= true;
    
    /**
     * Macro de défaite
     */
    public static final boolean LOOSE	= false;
    
    /**
     * @return Retourne l'entité contenant le texte du début de jeu
     * Fonction de création du texte de début de scène
     */
    private Entity createStart() {
    	
      	 Text view = FXGL.getUIFactoryService().newText("C'est au tour de " + this.player.getName() + " sur le jeu de mémoire !" + "\n\nAppuyer sur la touche Entrée pour commencer", Color.WHITE, 30.0);
      	 view.setFill(Color.BLACK);
               
          return entityBuilder()
                  .zIndex(5)
                  .view(view)
                  .build();
      }
       
    
    
    /**
     * @param player Joueur actuel
     * @param currentLap Tour actuel
     * @param partyManager Manager de la partie
     * 
     * Constructeur de la scène
     */
    public MemorySubScene(Player player, int currentLap, PartyManager partyManager) {
    	super(RTAIpartyApp.WIDTHSIZE, RTAIpartyApp.HEIGHTSIZE);
    	this.player = player;
    	this.currentLap = currentLap;
    	this.partyManager = partyManager;
    	
    	this.playerTurn = false;
    	this.nbTurn = 0;
    	
    	//ajout de la factory
    	this.getGameWorld().addEntityFactory(new RTAIpartyFactory());
    	
    	//enregistrement de l'input en cours
    	Input input = getInput();
    	
    	input.addAction(new UserAction("clic") {
    		@Override
    		protected void onActionBegin() {
    			
    			//si c'est le tour du joueur
    			if(playerTurn) {
    				//on boucle sur les cartes existantes
    				for(int i = 0; i < EntityCards.size(); i++) {
    					//on regarde si un click c'est produit sur une carte
        				boolean bCheckClick = EntityCards.get(i).getComponent(MemoryCardComponent.class).checkClick(input);
        				if(bCheckClick) {
        					//si le click était sur la carte qui appartenait au joueur
        					if(EntityCards.get(i) == cardSelect) {
        						System.out.println("Gagné");
        						DiscoverCard();
        						WinGame();
        					}else {
        						//si le click était une des cartes qui n'appartenait pas au joueur
        						System.out.println("Perdu");
        						DiscoverCard();
        						LooseGame();
        					}
        				}
        				
        			}
    			}
    		
    		}
    	}, MouseButton.PRIMARY);
    	
    	
    	//lancement du mini-jeux lors de l'appuit sur la touche entrée
        getInput().addAction(new UserAction("Start") {
            @Override
            protected void onAction() {
            	
            	if(startMenu != null) {
            		startMenu.removeFromWorld();
            		arrow.removeFromWorld();
            		startMenu = null;
            		CoverCard();
            		ChangePlace();
            	}
            	
                isStart = true;
            }
        }, KeyCode.ENTER);
        
        //création du libéllé de menu
        startMenu = createStart();
        startMenu.setX(450);
        startMenu.setY(50);
        
        //ajout de l'entité dans la scène
        this.getGameWorld().addEntity(startMenu);
        
        //création des info sur la scène
        Entity Description = createTexte("Tour de jeu de " + this.player.getName() + "\nTour n°" + this.currentLap);
        Description.setX(70);
        Description.setY(30);
        
        this.getGameWorld().addEntity(Description);
        
        
        //création du décor
        this.getGameWorld().spawn("decordodge",new SpawnData());
        
        
        //création et ajout des cartes sur la scène
        Entity carteGBlond 	= this.getGameWorld().spawn("carteGBlond",new SpawnData());
        carteGBlond.setX(300);
        carteGBlond.setY(350);
        
        Entity carteGBrun 	= this.getGameWorld().spawn("carteGBrun",new SpawnData());
        carteGBrun.setX(500);
        carteGBrun.setY(350);
        
        Entity carteFBlonde 	= this.getGameWorld().spawn("carteFBlonde",new SpawnData());
        carteFBlonde.setX(700);
        carteFBlonde.setY(350);
        
        Entity carteFBrune = this.getGameWorld().spawn("carteFBrune",new SpawnData());
        carteFBrune.setX(900);
        carteFBrune.setY(350);
        EntityCards = new ArrayList<Entity>();
        EntityCards.add(carteGBlond);
        EntityCards.add(carteGBrun);
        EntityCards.add(carteFBlonde);
        EntityCards.add(carteFBrune);
        
        this.GBlond 	= carteGBlond;
        this.GBrun 		= carteGBrun;
        this.FBlonde 	= carteFBlonde;
        this.FBrune		= carteFBrune;
        
        //création de la flèche indiquant la carte du joueur
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
				this.cardSelect = carteFBlonde;
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
    
    /**
     * fonction permettant d'interchanger deux cartes en fonction du nombre de tour qu'on souhaite
     */
    private void ChangePlace() {
    	
    	this.getTimer().runAtInterval(() -> {
    		
    		Random randomGenerator = new Random();
        	int index = randomGenerator.nextInt(EntityCards.size());
        	int index2 = randomGenerator.nextInt(EntityCards.size());
        	
           	FXGL.animationBuilder(this)
            .duration(Duration.seconds(3 / this.currentLap))
            .translate(EntityCards.get(index))
            .from(EntityCards.get(index).getPosition())
            .to(EntityCards.get(index2).getPosition())
            .buildAndPlay();
           	
           	FXGL.animationBuilder(this)
           	.onFinished(()->{
           		this.nbTurn++;
               	if(this.nbTurn == (2 * this.currentLap)) {
               		System.out.println("Mélange des cartes finalisé");
               		playerTurn = true;
               	}
            })
            .duration(Duration.seconds(3 / this.currentLap))
            .translate(EntityCards.get(index2))
            .from(EntityCards.get(index2).getPosition())
            .to(EntityCards.get(index).getPosition())
            .buildAndPlay();
           	
           	
           	

    	}, Duration.seconds(3 / this.currentLap), 2 * this.currentLap);
    	
    	
    	
       	
    }
    
    
    /**
     * Fonction permettant de masquer les cartes
     */
    private void CoverCard() {
		for(int i = 0; i < this.EntityCards.size(); i++) {
			this.EntityCards.get(i).getViewComponent().clearChildren();
			var coverSprite = texture("versocarte.png");
			this.EntityCards.get(i).getViewComponent().addChild(coverSprite);
		}
    }
    
    /**
     * Fonction permettant de démasquer les cartes
     */
    private void DiscoverCard() {
    	this.GBlond.getViewComponent().clearChildren();
    	this.GBlond.getViewComponent().addChild(texture("cartegarconblond.png"));
    	
    	this.GBrun.getViewComponent().clearChildren();
    	this.GBrun.getViewComponent().addChild(texture("cartegarconbrun.png"));
    	
    	this.FBrune.getViewComponent().clearChildren();
    	this.FBrune.getViewComponent().addChild(texture("cartefillebrune.png"));
    	
    	this.FBlonde.getViewComponent().clearChildren();
    	this.FBlonde.getViewComponent().addChild(texture("cartefilleblonde.png"));
    	
    	
    }
    
    
    /**
     * Fonction executant le code de défaite
     */
    private void LooseGame() {
    	isFinish = true;
        this.getGameWorld().spawn("loose",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
    	
    	this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(LOOSE);
    		
    	}, Duration.millis(2000));
    	
    }
    
    /**
     * Fonction executant le code de victoire
     */
    private void WinGame() {
    	
    	isFinish = true;
		this.getGameWorld().spawn("win",new SpawnData(0, RTAIpartyApp.HEIGHTSIZE / 2));
		
		this.getTimer().runOnceAfter(()->{
    		
    		getSceneService().popSubScene();
    		partyManager.nextPlayer(WIN);
    		
    	}, Duration.millis(2000));
    	

    }
    
}