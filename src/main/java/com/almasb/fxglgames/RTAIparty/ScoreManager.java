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

@SuppressWarnings("unchecked")
public class ScoreManager {
	
	private ArrayList <ArrayList<Player>> scoreBoard;
	
	public ScoreManager() {
		this.scoreBoard = new ArrayList <ArrayList<Player>>();
	}
	
	public void saveGameScore(ArrayList<Player> players) throws IOException, ParseException {
		
	    String dataFolder = System.getenv("LOCALAPPDATA");

	    JSONArray GameList = viewGameScore();
	    JSONObject GameObject = new JSONObject();
		JSONArray playersList = new JSONArray();
		
		for(int i = 0; i < players.size(); i++) {
			//System.out.println(players.get(i).getName());
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
		
        //Write JSON file
        try (FileWriter file = new FileWriter(dataFolder + "/RTAIparty/save.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(GameList.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
	
	public JSONArray viewGameScore() throws IOException, org.json.simple.parser.ParseException {
		
		this.scoreBoard = new ArrayList <ArrayList<Player>>();
		String dataFolder = System.getenv("LOCALAPPDATA");
		
		//JSON parser object to parse read file
	    JSONParser jsonParser = new JSONParser();
	    JSONArray Games = new JSONArray();

	    try (FileReader reader = new FileReader(dataFolder + "/RTAIparty/save.json"))
	    {
	        //Read JSON file
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
	
	public ArrayList <ArrayList<Player>> getBoard(){
		return this.scoreBoard;
	}
}
