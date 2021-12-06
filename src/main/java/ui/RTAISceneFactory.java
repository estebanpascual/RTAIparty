package ui;


import java.io.IOException;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxglgames.RTAIparty.RTAIpartyApp;

import ui.Menu.RTAIpartyMainMenu;


/**
 * @author GROUPE5
 * SceneFacotry personnalisé pour ajouté le menu principal
 *
 */
public class RTAISceneFactory extends SceneFactory {
	
	RTAIpartyMainMenu MainMenu;
	RTAIpartyApp app;
	
	public RTAISceneFactory(RTAIpartyApp app) {
		this.app = app;
	}
	
	@Override
	public FXGLMenu newMainMenu() {
		try {
			MainMenu = new RTAIpartyMainMenu(this.app);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return MainMenu;
    }
}
