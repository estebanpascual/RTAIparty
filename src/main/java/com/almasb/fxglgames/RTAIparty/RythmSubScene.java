package com.almasb.fxglgames.RTAIparty;


import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.particle.ParticleSystem;
import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class RythmSubScene extends SubScene {

    Player player;
    int currentLap;
    PartyManager partyManager;
    
    public RythmSubScene(Player player, int currentLap, PartyManager partyManager) {
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
    	
    	
    	/*Level level = getAssetLoader().loadLevel("RTAIparty_Rythm.txt", new TextLevelLoader(32, 32, ' '));
    	getGameWorld().setLevel(level);*/
    	spawn("decor",new SpawnData(0, 0));
    	Entity num1 = spawn("numero1", new SpawnData());
    	num1.setX(270);
    	num1.setY(120);
    	
    	System.out.println("JEU DE RYTHME; \n joueur : "+ this.player.getName() + "\n difficulté : " + this.currentLap);
    	
    }
}