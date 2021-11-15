package ui.Menu;

import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class menuButtonNbPlayer extends StackPane{
	private String name;
	private Runnable action;
	private Text text;
	public SimpleObjectProperty<menuButtonNbPlayer> selectedButtonNbPlayer;
	
	public menuButtonNbPlayer (String name, Runnable action, SimpleObjectProperty<menuButtonNbPlayer> selectedButtonNbPlayer) {
		this.name = name;
		this.action = action;
		this.selectedButtonNbPlayer = selectedButtonNbPlayer;
		this.text = getUIFactoryService().newText(this.name, Color.WHITE, 30.0);
		this.text.fillProperty().bind(
			Bindings.when(focusedProperty()).then(Color.NAVAJOWHITE).otherwise(Color.WHITE)
		);
		
		setOnKeyPressed(e -> {
				if(e.getCode() == KeyCode.ENTER) {
					this.action.run();
				}
			}
		);
		
		focusedProperty().addListener((observable, oldvalue, isSelected)->{
			if (isSelected) {
				selectedButtonNbPlayer.setValue(this);
			}
		});
		
		setAlignment(Pos.BASELINE_CENTER);
		setFocusTraversable(true);
		
		getChildren().addAll(this.text);
		
	}
}