package ui.Menu;

import java.util.ArrayList;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.layout.HBox;


/**
 * @author GROUPE5
 * 
 * Classe permettant de stocker les sprites du menu
 */
public class SpriteSelector{
	private ArrayList<SpriteElement> tabSpriteElement;
	/**
	 * Sprite actuellement séléctionné
	 */
	public SpriteElement SpriteElementSelected;
	private HBox box;
	
	/**
	 * Constructeur de la classe
	 */
	SpriteSelector(){
		
		//Ajout de la liste des sprites du jeu
		this.tabSpriteElement = new ArrayList<SpriteElement>();
		this.box = new HBox(10);
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("garcon_blond.png"), "garçon blond", 1));
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("garcon_brun.png"), "garçon brun", 2));
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("fille_blonde.png"), "fille blonde", 3));
		tabSpriteElement.add(new SpriteElement(this, FXGL.getAssetLoader().loadTexture("fille_brune.png"), "fille brune", 4));
		
		for(int i = 0; i < tabSpriteElement.size(); i++) {
			this.box.getChildren().add(tabSpriteElement.get(i).getTexture());
			this.box.getChildren().add(tabSpriteElement.get(i).getCursorSelect());
		}
	}
	
	/**
	 * @param SpriteElementSelected Retourne le sprite sélectionné par le joueur
	 */
	public void selectSpriteElement(SpriteElement SpriteElementSelected){
		
		UnselectAll();
		if(!SpriteElementSelected.AlreadyChoose()) {
			this.SpriteElementSelected = SpriteElementSelected;
			this.SpriteElementSelected.Select();
		}
		
	}
	
	/**
	 * Déselectionne tout les sprites
	 */
	public void UnselectAll() {
		for(int i = 0; i < tabSpriteElement.size(); i++) {
			tabSpriteElement.get(i).Unselect();
		}
	}
	
	/**
	 * @return Retourne la box contenant les sprites
	 */
	public HBox getBox() {
		return this.box;
	}
	
}