package ui.Menu;

import com.almasb.fxgl.texture.Texture;

import javafx.scene.shape.Polygon;

/**
 * @author GROUPE5
 * 
 * Classe permettant de stocker les informations des sprites pour le choix des joueurs
 */
public class SpriteElement {
    	Texture spritePlayer;
    	private boolean choose;
    	private SpriteSelector selector;
    	private Polygon CursorSelect;
    	private String name;
    	private int typePlayer;
    	
    	SpriteElement(SpriteSelector SpriteSelector, Texture texturePlayer, String name, int typePlayer){
    		
    		this.typePlayer = typePlayer;
    		this.selector = SpriteSelector;
    		this.spritePlayer = texturePlayer;
    		this.CursorSelect = new Polygon(
    	            0d, 0d,
    	            (10 * Math.tan(10)), 10,
    	            -(10 * Math.tan(10)), 10
    	    );

    		this.CursorSelect.setTranslateX(-32);
    		this.CursorSelect.setTranslateY(35);
    		this.CursorSelect.setVisible(false);
    		this.name = name;
    		spritePlayer.setOnMouseClicked(event -> {
    			this.selector.selectSpriteElement(this);
    		});
    	}
    	
//    	public boolean AlreadyChoose() {
//    		return choose;
//    	}
    	
    	/**
    	 * Fonction a appelé pour désélectionner le sprite
    	 */
    	public void Unselect() {
    		this.CursorSelect.setVisible(false);
    	}
    	
    	/**
    	 * Fonction a appelé pour sélectionner le sprite
    	 */
    	public void Select() {
    		this.CursorSelect.setVisible(true);
    	}
    	
    	/**
    	 * @return Retourne la texture du sprite
    	 */
    	public Texture getTexture() {
    		return this.spritePlayer;
    	}
    	
    	/**
    	 * @return Retourne le curseur du sprite
    	 */
    	public Polygon getCursorSelect() {
    		return this.CursorSelect;
    	}
    	
    	/**
    	 * @return Retourne le type de sprite
    	 */
    	public int getTypePlayer() {
    		return this.typePlayer;
    	}
    	
    	/**
    	 * @return Retourne le nom du sprite
    	 */
    	public String getValue() {
    		return this.name;
    	}
    	
    	/**
    	 * Fonction a appelé pour indiquer que le sprite est choisis
    	 */
    	public void validSprite() {
    		this.choose = true;
    		this.spritePlayer.setOpacity(0.3);
    	}
    	
    	/**
    	 * @return Retourne si le sprite est déjà choisis
    	 */
    	public boolean AlreadyChoose() {
    		return this.choose;
    	}
    }