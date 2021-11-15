package ui.Menu;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.layout.HBox;


public class SpriteSelector{
	private ArrayList<SpriteElement> tabSpriteElement;
	public SpriteElement SpriteElementSelected;
	private HBox box;
	
	SpriteSelector(){
		this.tabSpriteElement = new ArrayList<SpriteElement>();
		this.box = new HBox(10);
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("garcon_blond.png"), "garçon blond"));
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("garcon_brun.png"), "garçon brun"));
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("fille_blonde.png"), "fille blonde"));
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("fille_brune.png"), "fille brune"));
		
		for(int i = 0; i < tabSpriteElement.size(); i++) {
			this.box.getChildren().add(tabSpriteElement.get(i).getTexture());
			this.box.getChildren().add(tabSpriteElement.get(i).getCursorSelect());
		}
	}
	
	public void selectSpriteElement(SpriteElement SpriteElementSelected){
		
		UnselectAll();
		if(!SpriteElementSelected.AlreadyChoose()) {
			this.SpriteElementSelected = SpriteElementSelected;
			this.SpriteElementSelected.Select();
		}
		
	}
	
	public void UnselectAll() {
		for(int i = 0; i < tabSpriteElement.size(); i++) {
			tabSpriteElement.get(i).Unselect();
		}
	}
	
	public HBox getBox() {
		return this.box;
	}
	
}