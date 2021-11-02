package ui;


import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxglgames.RTAIparty.RTAIpartyApp;


public class RTAISceneFactory extends SceneFactory {
	
	RTAIpartyApp app;
	
	public RTAISceneFactory(RTAIpartyApp app) {
		this.app = app;
	}
	
	@Override
	public FXGLMenu newMainMenu() {
        return new RTAIpartyMainMenu(this.app);
    }
}
