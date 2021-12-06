package com.almasb.fxglgames.RTAIparty.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;

import javafx.geometry.Rectangle2D;

/**
 * @author GROUPE5
 * Classe permettant d'être attaché à une entitée
 * Permet de détecter le click sur la carte
 *
 */
public class MemoryCardComponent extends Component {

	public boolean checkClick(Input input) {
		
		Rectangle2D cooCard = new Rectangle2D(this.getEntity().getX(), this.getEntity().getY(), 150, 212);
        if (cooCard.contains(input.getMousePositionWorld())) {
        	return true;
        }
        
        return false;
	}
	
}
