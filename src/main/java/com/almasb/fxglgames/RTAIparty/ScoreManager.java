package com.almasb.fxglgames.RTAIparty;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author GROUPE5
 *
 * Classe permettant la gestion du chargement et de la sauvegarde des scores
 */
@SuppressWarnings("unchecked")
public class ScoreManager {
	
	//Liste des parties contenant les joueurs
	private ArrayList <ArrayList<Player>> scoreBoard;
	
	/**
	 * Constructeur de la classe
	 */
	public ScoreManager() {
		this.scoreBoard = new ArrayList <ArrayList<Player>>();
	}
	
	/**
	 * @param players Listes des joueurs à sauvegarder
	 * @throws IOException Retourne une erreur d'écriture de fichier
	 * @throws ParseException Retourne une erreur d'écriture de fichier
	 */
	public void saveGameScore(ArrayList<Player> players) throws IOException, ParseException {
		
		//récupération de l'emplacement du dossier de data de l'utilisateur
	    String dataFolder = System.getenv("LOCALAPPDATA");

	    //On récupère toute les parties précédentes pour les reformater dans la nouvelle sauvegarde
	    JSONArray GameList = viewGameScore();
	    
	    JSONObject GameObject = new JSONObject();
		JSONArray playersList = new JSONArray();
		
		for(int i = 0; i < players.size(); i++) {

			JSONObject playerDetails = new JSONObject();
			playerDetails.put("name", players.get(i).getName());
			playerDetails.put("winCount", players.get(i).getWinCount());
			playerDetails.put("isLosing", players.get(i).isLosing());
			playerDetails.put("typePlayer", players.get(i).getTypePlayer());
			
			JSONObject playerObject = new JSONObject();
			playerObject.put("player", playerDetails);
			
			playersList.add(playerObject);
		}

		GameObject.put("Game", playersList);
		GameList.add(GameObject);
		
		Files.createDirectories(Paths.get(dataFolder + "/RTAIparty/"));
		
		//écriture du fichier json
        try (FileWriter file = new FileWriter(dataFolder + "/RTAIparty/save.json")) {

        	file.write(GameList.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
	
	/**
	 * @return Retourne la liste en JSON de toute les parties actuellement stocké dans le fichier
	 * @throws IOException Retourne une erreur de lecteur de fichier
	 * @throws org.json.simple.parser.ParseException Retourne une erreur de lecteur de fichier
	 */
	public JSONArray viewGameScore() throws IOException, org.json.simple.parser.ParseException {
		
		this.scoreBoard = new ArrayList <ArrayList<Player>>();
		String dataFolder = System.getenv("LOCALAPPDATA");
		

	    JSONParser jsonParser = new JSONParser();
	    JSONArray Games = new JSONArray();

	    try (FileReader reader = new FileReader(dataFolder + "/RTAIparty/save.json"))
	    {

	        Object obj = jsonParser.parse(reader);
	
	        Games = (JSONArray) obj;
//	        System.out.println(Games);
	         
	        Games.forEach( game -> parseGameObject( (JSONObject) game ) );
	        
	        
	    } catch (Exception ignore) {
	    	return new JSONArray();
	    }
	    
//	    for(int i = 0; i < scoreBoard.size(); i++) {
//	    	System.out.println("Score party "+i+"]");
//	    	for(int j = 0; j < scoreBoard.get(i).size(); j++) {
//	    		System.out.println("	Nom) " + scoreBoard.get(i).get(j).getName());
//	    		System.out.println("	Score) " + scoreBoard.get(i).get(j).getWinCount());
//	    	}
//	    }
	    
	    return Games;
	}
	
	
	/**
	 * @param game Prend paramètre un résultat de partie formater en JSON
	 */
	private void parseGameObject(JSONObject game) 
    {
		
		JSONArray GameObject = (JSONArray) game.get("Game");
        
		ArrayList<Player> tempPlayers = new ArrayList<Player>();
		
		
        GameObject.forEach(player -> {
          
        	
        	JSONObject playerObj = (JSONObject)player;
//        	System.out.println(playerObj);
        	
        	JSONObject playerObj2 = (JSONObject)playerObj.get("player");
//        	System.out.println(playerObj2);
          
        	String name = (String) playerObj2.get("name");    
//        	System.out.println(name);
        	
        	Long typePlayer = (Long) playerObj2.get("typePlayer");    
//        	System.out.println(typePlayer);
        	        
        	Long winCount = (Long) playerObj2.get("winCount");  
//        	System.out.println(winCount);
        	        
        	boolean isLosing = (boolean) playerObj2.get("isLosing");  
//        	System.out.println(isLosing);
          
          Player tempPlayer = new Player(name, typePlayer.intValue(), winCount.intValue(), isLosing);
          tempPlayers.add(tempPlayer);
          
        	
        });


        scoreBoard.add(tempPlayers);
    }
	
	/**
	 * @return Retourne la liste des parties
	 */
	public ArrayList <ArrayList<Player>> getBoard(){
		return this.scoreBoard;
	}
}
