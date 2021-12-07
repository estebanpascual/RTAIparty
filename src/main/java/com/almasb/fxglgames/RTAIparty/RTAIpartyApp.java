/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.almasb.fxglgames.RTAIparty;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import ui.RTAISceneFactory;
import java.util.ArrayList;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;


/**
 * 
 * @author GROUPE5
 * Classe principal contenant le jeu
 *
 */
public class RTAIpartyApp extends GameApplication {

	/**
	 * taille des block de base
	 */
	public static final int BLOCK_SIZE = 32;
    
	
    /**
     * //taille de la largeur de la fenêtre
     */
    public static final int WIDTHSIZE = 1280;
    
    /**
     * taille de la hauteur de la fenêtretaille de la hauteur de la fenêtre
     */
    public static final int HEIGHTSIZE = 720;
    
    /**
     * PartyManager de l'application
     */
    public PartyManager partyManager;
    
    /**
     * ScoreManager de l'application
     */
    public ScoreManager scoreManager;

    
    @Override
    //Initialisation des paramètres de la fenêtre
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTHSIZE);
        settings.setHeight(HEIGHTSIZE);
        settings.setTitle("RTAIparty");
        settings.setVersion("1.0");
        //Activation du menu principal
        settings.setMainMenuEnabled(true);
        
        //Permission pour la modification de la taille de fenêtre
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
        
        //Ajout du créateur de scène dans les paramètres
        SceneFactory SceneFactoryEntity = new RTAISceneFactory(this);
        settings.setSceneFactory(SceneFactoryEntity);
    }

    @Override
    protected void initInput() {
    	
    }
   

    @Override
    protected void initGameVars(Map<String, Object> vars) {

    }
    
    
    
    @Override
    protected void initGame() {

    }
    
    /**
     * Retour au menu principal
     */
    public void goMenu() {
    	getGameController().gotoMainMenu();
    }
    
    
    /**
     * @param players Liste des joueurs a ajouter au manager
     * 
     * Fonction de lancement d'une partie
     */
    public void startGame(ArrayList<Player> players) {
    	//création du PartyManager
    	this.partyManager = new PartyManager(players);
    	
    	//lancement du jeu
    	this.partyManager.startGame();
    	
    }

    
    @Override
    protected void initPhysics() {

    }

    
    @Override
    protected void initUI() {

    }
    
    @Override
    protected void onUpdate(double tpf) {    	
    	//System.out.println("update !");
    }
    
    /**
     * @param args Argument du main
     */
    public static void main(String[] args) {
        launch(args);
    }
}
