package ui.Menu;

import com.almasb.fxgl.texture.Texture;

import javafx.scene.shape.Polygon;

public class SpriteElement {
    	Texture spritePlayer;
    	private boolean choose;
    	private SpriteSelector selector;
    	private Polygon CursorSelect;
    	private String name;
    	
    	SpriteElement(SpriteSelector SpriteSelector, Texture texturePlayer, String name){
    		
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
    	
    	public void Unselect() {
    		this.CursorSelect.setVisible(false);
    	}
    	
    	public void Select() {
    		this.CursorSelect.setVisible(true);
    	}
    	
    	public Texture getTexture() {
    		return this.spritePlayer;
    	}
    	
    	public Polygon getCursorSelect() {
    		return this.CursorSelect;
    	}
    	
    	public String getValue() {
    		return this.name;
    	}
    	
    	public void validSprite() {
    		this.choose = true;
    		this.spritePlayer.setOpacity(0.3);
    	}
    	
    	public boolean AlreadyChoose() {
    		return this.choose;
    	}
    }