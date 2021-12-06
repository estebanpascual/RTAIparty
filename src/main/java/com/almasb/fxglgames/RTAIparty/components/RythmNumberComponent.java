package com.almasb.fxglgames.RTAIparty.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * @author GROUPE5
 * Classe permettant d'être attaché à une entitée
 * Permet d'activer et désactiver un numéro
 *
 */
public class RythmNumberComponent extends Component {
	
	int number;
	boolean isActive;
	
	public void AssignNumber(int number) {
		this.number = number;
		this.isActive = false;
	}
	
	public void setActive(boolean active) {

		this.isActive = active;
		
		if(this.isActive) {
			this.getEntity().setOpacity(1);			
		}else {
			this.getEntity().setOpacity(0.5);
		}
		
	}
	
	public boolean getIsActive() {
		return this.isActive;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void init() {
		this.getEntity().setOpacity(0.50);
	}
}
