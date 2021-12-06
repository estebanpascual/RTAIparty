package ui.Menu;

import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxglgames.RTAIparty.Player;
import com.almasb.fxglgames.RTAIparty.RTAIpartyApp;
import com.almasb.fxglgames.RTAIparty.ScoreManager;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author GROUPE5
 * Classe du menu principal
 * Permet de paramétrer une nouvelle partie
 */
public class RTAIpartyMainMenu extends FXGLMenu {
	
	private SimpleObjectProperty<menuButton> selectedButton;
	private SimpleObjectProperty<menuButtonNbPlayer> selectedButtonNbPlayer;
	private ArrayList<Player> Players;
	private Text titleValue;
	private VBox BoxTitle;
	SpriteSelector mySpriteSelector;
	
	private int nbPlayer;
	private int choicePlayer;
	
	private ArrayList<Node> nodeScore;
	
	private ArrayList<Node> nodeMenu;
	
	private ArrayList<Node> nodePlayer;
	
	private TextField namePlayer;
	RTAIpartyApp app;
	
	private ArrayList <ArrayList<Player>> scoreBoard;
	private int indexScore;
	
	public RTAIpartyMainMenu(RTAIpartyApp app) throws IOException {
		super(MenuType.MAIN_MENU);
		this.app = app;
		
		app.scoreManager = new ScoreManager();
		this.nodeScore = new ArrayList<Node>();
		this.indexScore = 0;
		
		initMenu();
	}
	
	public void initMenu() {
		
    	this.nbPlayer = 0;
    	this.choicePlayer = 1;
		this.Players = new ArrayList<Player>();
		this.mySpriteSelector = new SpriteSelector();
		getContentRoot().getChildren().removeAll(nodeScore);
		this.nodeScore = new ArrayList<Node>();
		this.nodeMenu = new ArrayList<Node>();
		this.nodePlayer = new ArrayList<Node>();
		
    	//ajout du fond
    	Texture background = FXGL.getAssetLoader().loadTexture("background/classe.png");
    	getContentRoot().getChildren().addAll(background);
    	
    	//ajout du tableau
    	Texture board = FXGL.getAssetLoader().loadTexture("tableau.png");
    	board.setX(getAppWidth()/4);
    	board.setY(getAppHeight()/5);
    	getContentRoot().getChildren().addAll(board);
    	
    	
    	//ajout des tables
    	for(int i = 0; i < 3; i++) {
    		Texture chair = FXGL.getAssetLoader().loadTexture("table.png");
    		chair.setX(250 + i * 250);
    		chair.setY(450);
    		
    		getContentRoot().getChildren().addAll(chair);
    	}
    	
    	
    	//création du menu de sélection
    	menuButton newGameButton = new menuButton("Nouvelle partie", "Lancer une nouvelle partie", () -> numberOfPlayer());
    	menuButton newScoreButton = new menuButton("Score", "Voir le tableau des scores", () -> {
			try {
				scoreBoard();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    	
    	this.selectedButton = new SimpleObjectProperty<>(newGameButton);

    	//création du séprateur du menu
    	Rectangle customSeparator = new Rectangle(400, 2);
    	customSeparator.setFill(new LinearGradient(0,0.5, 1, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITESMOKE), new Stop(1, Color.TRANSPARENT)));
    	
    	//création du texte de description du menu
    	var textDescription = getUIFactoryService().newText("", Color.WHITESMOKE, 20.0);
    	
    	//on applique la description de l'élement sélectionné au libellé deS description
    	textDescription.textProperty().bind(
    		Bindings.createStringBinding(() -> selectedButton.get().description, selectedButton)
    	);
    	
    	//création de l'affichage
    	var box = new VBox(15, newGameButton, newScoreButton, customSeparator, textDescription);
    	this.nodeMenu.add(box);
    	box.setTranslateX(490);
    	box.setTranslateY(230);
    	box.setAlignment(Pos.CENTER_LEFT);
    	this.titleValue = getUIFactoryService().newText("RTAI PARTY!", Color.WHITESMOKE, 30.0);
    	this.BoxTitle =  new VBox(10,this.titleValue);
    	this.BoxTitle.setTranslateX(530);
    	this.BoxTitle.setTranslateY(170);
    	
    	
    	getContentRoot().getChildren().addAll(box, this.BoxTitle);
	}
	
	private void scoreBoard() throws IOException, ParseException {
		
		app.scoreManager.viewGameScore();
		scoreBoard = app.scoreManager.getBoard();
		
		Score();
	}

	private void numberOfPlayer() {
		this.titleValue.setText("NOMBRE DE JOUEUR");
		this.BoxTitle.setTranslateX(480);
		FadeTransition ft = new FadeTransition(Duration.millis(50), getContentRoot().getChildren().get(5));
	     ft.setFromValue(1.0);
	     ft.setToValue(0);
	     ft.setCycleCount(1);
	     ft.setOnFinished(e -> getContentRoot().getChildren().removeAll(nodeMenu));
	     ft.play();
	     
		ScaleTransition st = new ScaleTransition(Duration.millis(900), getContentRoot());
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(2);
        st.setToY(2);
        st.play();
        
        TranslateTransition tr = new TranslateTransition(Duration.millis(900), getContentRoot());
        tr.setToY(getContentRoot().getTranslateY() + 150);
        tr.setToX(getContentRoot().getTranslateX() + 2);
        tr.play();

        
    	this.selectedButtonNbPlayer = new SimpleObjectProperty<>();
      //création du menu de sélection
        menuButtonNbPlayer buttonNbPlayer2 = new menuButtonNbPlayer("2", () -> choiceNbPlayer(2), this.selectedButtonNbPlayer);
        menuButtonNbPlayer buttonNbPlayer3 = new menuButtonNbPlayer("3", () -> choiceNbPlayer(3), this.selectedButtonNbPlayer);
        menuButtonNbPlayer buttonNbPlayer4 = new menuButtonNbPlayer("4", () -> choiceNbPlayer(4), this.selectedButtonNbPlayer);
        
    	this.selectedButtonNbPlayer = new SimpleObjectProperty<>(buttonNbPlayer2);
         
    	var box = new HBox(150, buttonNbPlayer2, buttonNbPlayer3, buttonNbPlayer4);
    	nodePlayer.add(box);
    	box.setTranslateX(470);
    	box.setTranslateY(260);
    	box.setAlignment(Pos.BASELINE_CENTER);
    	getContentRoot().getChildren().addAll(box);
    	
	}
	
	private void Score() {
		this.titleValue.setText("TABLEAU DES SCORES");
		this.BoxTitle.setTranslateX(450);
		FadeTransition ft = new FadeTransition(Duration.millis(5), getContentRoot().getChildren().get(5));
	     ft.setFromValue(1.0);
	     ft.setToValue(0);
	     ft.setCycleCount(1);
	     ft.setOnFinished(e -> getContentRoot().getChildren().removeAll(nodeMenu));
	     ft.play();
	     
		ScaleTransition st = new ScaleTransition(Duration.millis(900), getContentRoot());
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(2);
        st.setToY(2);
        st.play();	
        
        TranslateTransition tr = new TranslateTransition(Duration.millis(900), getContentRoot());
        tr.setToY(getContentRoot().getTranslateY() + 150);
        tr.setToX(getContentRoot().getTranslateX() + 2);
        tr.play();
        
        
        refreshScore(0);

        
    	
	}
	
	private void refreshScore(int otherScore) {
		
		this.indexScore = this.indexScore + otherScore;
		SimpleObjectProperty<menuButtonNbPlayer> scoreSelect = new SimpleObjectProperty<>();
		
		if(!scoreBoard.isEmpty()) {
			
			getContentRoot().getChildren().removeAll(nodeScore);
			this.nodeScore = new ArrayList<Node>();
			
			//boucle sur tout les joueurs de la partie
			for(int i = 0; i < scoreBoard.get(this.indexScore).size(); i++) {
				
				//affichage du score
				Text textePlace = new Text((i+1) + ") " + scoreBoard.get(this.indexScore).get(i).getName() + " avec " + scoreBoard.get(this.indexScore).get(i).getWinCount() + " point gagné");
				textePlace.setX(400);
				textePlace.setY(230 + (50 * i));
				textePlace.setFill(Color.WHITE);
				
				Texture spritePlayer = FXGL.getAssetLoader().loadTexture("garcon_blond.png");
				
				switch (scoreBoard.get(this.indexScore).get(i).getTypePlayer()) {
				case Player.GARCON_BLOND:
					spritePlayer = FXGL.getAssetLoader().loadTexture("garcon_blond.png");
					break;
					
				case Player.GARCON_BRUN:
					spritePlayer = FXGL.getAssetLoader().loadTexture("garcon_brun.png");
					break;
					
				case Player.FILLE_BLONDE:
					spritePlayer = FXGL.getAssetLoader().loadTexture("fille_blonde.png");
					break;
					
				case Player.FILLE_BRUNE:
					spritePlayer = FXGL.getAssetLoader().loadTexture("fille_brune.png");
					break;
					
				default:
					break;
				}
				
				spritePlayer.setX(365);
				spritePlayer.setY(200 + (50 * i));
				
				this.nodeScore.add(textePlace);
				this.nodeScore.add(spritePlayer);
				
			}
			
			
			//création du menu de sélection
			if(this.indexScore < scoreBoard.size() - 1) {
				menuButtonNbPlayer nextScore = new menuButtonNbPlayer(">", () -> refreshScore(+1), scoreSelect);        	
				nextScore.setTranslateX(850);
				nextScore.setTranslateY(340);
				this.nodeScore.add(nextScore);
			}
			
			if(this.indexScore != 0) {
				menuButtonNbPlayer prevScore = new menuButtonNbPlayer("<", () -> refreshScore(-1), scoreSelect);        	
				prevScore.setTranslateX(650);
				prevScore.setTranslateY(340);
				this.nodeScore.add(prevScore);
			}
			
		}
		
		
        menuButtonNbPlayer initMenu = new menuButtonNbPlayer("Retour", () -> {
			
        	ScaleTransition st = new ScaleTransition(Duration.millis(5), getContentRoot());
	        st.setFromX(2);
	        st.setFromY(2);
	        st.setToX(1);
	        st.setToY(1);
	        st.play();
	        
	        TranslateTransition tr = new TranslateTransition(Duration.millis(5), getContentRoot());
	        tr.setToY(getContentRoot().getTranslateY() - 150);
	        tr.setToX(getContentRoot().getTranslateX() - 2);
	        tr.play();
	        
        	initMenu();
        	
        	}, scoreSelect);        	
        initMenu.setTranslateX(710);
        initMenu.setTranslateY(340);
    	this.nodeScore.add(initMenu);
        
        
        getContentRoot().getChildren().addAll(this.nodeScore);
        
	}
	
	private void choiceNbPlayer(int nbPlayer) {
		
		System.out.println(nbPlayer);
		this.nbPlayer = nbPlayer;
		playerSettings();
		FadeTransition ft = new FadeTransition(Duration.millis(100), getContentRoot().getChildren().get(6));
	     ft.setFromValue(1.0);
	     ft.setToValue(0);
	     ft.setCycleCount(1);
	     ft.setOnFinished(e -> getContentRoot().getChildren().removeAll(nodePlayer));
	     ft.play();
		
	}
	
	
	private void playerSettings() {
		this.titleValue.setText("Création du J"+ this.choicePlayer);
		this.BoxTitle.setTranslateX(520);
		
	     Label labelNamePlayer = new Label("PSEUDO :");
	     labelNamePlayer.setTextFill(Color.WHITE);    
	     this.namePlayer = new TextField();
	     
	     Label labelChoiceSprite = new Label("PERSONNAGE :");
	     labelChoiceSprite.setTextFill(Color.WHITE);    

	     Button btnValid = new Button("Valider");
	     
	     
	     btnValid.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	    	nextPlayerSetting(mySpriteSelector);
	    	    }
	    	});
	     
	     HBox boxNamePlayer = new HBox(10, labelNamePlayer, namePlayer);
	     boxNamePlayer.setAlignment(Pos.BASELINE_CENTER);
	     
	     HBox boxChoiceSprite = new HBox(10, labelChoiceSprite, mySpriteSelector.getBox());
	     boxChoiceSprite.setAlignment(Pos.BASELINE_CENTER);
	     
	     var box = new VBox(25, boxNamePlayer, boxChoiceSprite, btnValid);
	    	box.setTranslateX(450);
	    	box.setTranslateY(220);
	    	box.setAlignment(Pos.CENTER);
	  
	    	
	    	getContentRoot().getChildren().addAll(box);
	}
	
	private void nextPlayerSetting(SpriteSelector mySpriteSelector) {
		if(mySpriteSelector.SpriteElementSelected == null) {
			return;
		}
		
		System.out.println(mySpriteSelector.SpriteElementSelected.getValue());
		
		this.Players.add(new Player(this.namePlayer.getText(), mySpriteSelector.SpriteElementSelected.getTypePlayer()));
		mySpriteSelector.SpriteElementSelected.validSprite();
		this.choicePlayer++;
		mySpriteSelector.UnselectAll();
		
		if(this.choicePlayer - 1 < this.nbPlayer) {
			mySpriteSelector.SpriteElementSelected = null;
			getContentRoot().getChildren().remove(getContentRoot().getChildren().size()-1);	
			playerSettings();
		}else {
			getContentRoot().getChildren().remove(getContentRoot().getChildren().size()-1);
			
			//lancement du jeu
			this.fireNewGame();
			this.app.startGame(Players);
			getContentRoot().getChildren().clear();
			
			ScaleTransition st = new ScaleTransition(Duration.millis(10), getContentRoot());
	        st.setFromX(2);
	        st.setFromY(2);
	        st.setToX(1);
	        st.setToY(1);
	        st.play();
	        
	        TranslateTransition tr = new TranslateTransition(Duration.millis(10), getContentRoot());
	        tr.setToY(getContentRoot().getTranslateY() - 150);
	        tr.setToX(getContentRoot().getTranslateX() - 2);
	        tr.play();

			initMenu();
		}
	}
	
	
	//extends du button de menu
	private class menuButton extends StackPane{
		private String name;
		String description;
		private Runnable action;
		private Text text;
		private Rectangle selector;
		
		public menuButton (String name, String description, Runnable action) {
			this.name = name;
			this.action = action;
			this.description = description;
			this.text = getUIFactoryService().newText(this.name, Color.WHITE, 30.0);
			this.text.fillProperty().bind(
				Bindings.when(focusedProperty()).then(Color.NAVAJOWHITE).otherwise(Color.WHITE)
			);

			this.selector = new Rectangle(6, 17, Color.WHITE);
			this.selector.setTranslateX(-20);
			this.selector.setTranslateY(-2);
			this.selector.visibleProperty().bind(focusedProperty());
			
			setOnKeyPressed(e -> {
					if(e.getCode() == KeyCode.ENTER) {
						this.action.run();
					}
				}
			);
			
			focusedProperty().addListener((observable, oldvalue, isSelected)->{
				if (isSelected) {
					selectedButton.setValue(this);
				}
			});
			
			setAlignment(Pos.CENTER_LEFT);
			setFocusTraversable(true);
			
			getChildren().addAll(this.selector, this.text);
			
		}

	}

}