package com.almasb.fxglgames.RTAIparty;


import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getSceneService;

import java.util.ArrayList;
import java.util.Collections;

import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EndgameSubScene extends GameSubScene {
	
    private Entity createTexte(String text) {
    	
   	 Text view = FXGL.getUIFactoryService().newText(text);
   	 view.setFill(Color.WHITE);
            
       return entityBuilder()
               .zIndex(5)
               .view(view)
               .build();
   }

    
	

    
	public EndgameSubScene(ArrayList<Player> players, PartyManager partyManager) {
		super(RTAIpartyApp.WIDTHSIZE, RTAIpartyApp.HEIGHTSIZE);
		
		
	    this.getInput().addAction(new UserAction("goMenu") {
	        @Override
	        protected void onAction() {
	        	getSceneService().popSubScene();
	        	RTAIpartyApp app = FXGL.<RTAIpartyApp>getAppCast();
	        	app.goMenu();
	        }
	    }, KeyCode.ENTER);
	    
	    
		this.getGameWorld().addEntityFactory(new RTAIpartyFactory());
        
		this.getGameWorld().spawn("decorclasse",new SpawnData(0, 0));
        Entity tableau = this.getGameWorld().spawn("decortableau",new SpawnData(0, 0));
        
        tableau.setX(RTAIpartyApp.WIDTHSIZE/4);
        tableau.setY(RTAIpartyApp.HEIGHTSIZE/5);
        
		ScaleTransition st = new ScaleTransition(Duration.millis(10), getContentRoot());
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(2);
        st.setToY(2);
        st.play();
        
        TranslateTransition tr = new TranslateTransition(Duration.millis(10), getContentRoot());
        tr.setToY(getContentRoot().getTranslateY() + 150);
        tr.setToX(getContentRoot().getTranslateX() + 2);
        tr.play();

        
        
        Entity texteTitle = createTexte("Résultat de la partie");
        texteTitle.setX(550);
        texteTitle.setY(185);
        this.getGameWorld().addEntity(texteTitle);
        
        Collections.sort(players);
        
        for(int i = 0; i < players.size(); i++) {
        	Entity textePlace = createTexte((i+1) + ") " + players.get(i).getName() + " avec " + players.get(i).getWinCount() + " point gagné");
        	textePlace.setX(400);
        	textePlace.setY(230 + (50 * i));
        	
        	switch (players.get(i).getTypePlayer()) {
			case Player.GARCON_BLOND:
				Entity EndGBlond = this.getGameWorld().spawn("EndGBlond",new SpawnData(0, 0));
				EndGBlond.setX(365);
				EndGBlond.setY(200 + (50 * i));
				break;
				
			case Player.GARCON_BRUN:
				Entity EndGBrun = this.getGameWorld().spawn("EndGBrun",new SpawnData(0, 0));
				EndGBrun.setX(365);
				EndGBrun.setY(200 + (50 * i));
				break;
			
			case Player.FILLE_BLONDE:
				Entity EndFBlonde = this.getGameWorld().spawn("EndFBlonde",new SpawnData(0, 0));
				EndFBlonde.setX(365);
				EndFBlonde.setY(200 + (50 * i));
				break;
				
			case Player.FILLE_BRUNE:
				Entity EndFBrune = this.getGameWorld().spawn("EndFBrune",new SpawnData(0, 0));
				EndFBrune.setX(365);
				EndFBrune.setY(200 + (50 * i));
				break;
				
			default:
				break;
		}
        	
        	this.getGameWorld().addEntity(textePlace);
    	}
        
	}

}
