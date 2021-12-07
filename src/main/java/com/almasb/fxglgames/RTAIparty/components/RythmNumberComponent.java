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
	
	/**
	 * @param number Nombre assigné au component
	 */
	public void AssignNumber(int number) {
		this.number = number;
		this.isActive = false;
	}
	
	/**
	 * @param active Défini l'activation du numéro
	 */
	public void setActive(boolean active) {

		this.isActive = active;
		
		if(this.isActive) {
			this.getEntity().setOpacity(1);			
		}else {
			this.getEntity().setOpacity(0.5);
		}
		
	}
	
	/**
	 * @return Retourne si le Component est actif
	 */
	public boolean getIsActive() {
		return this.isActive;
	}
	
	/**
	 * @return Retourne le numéro assigné
	 */
	public int getNumber() {
		return this.number;
	}
	
	/**
	 * Fonction d'initialisation après avoir était relié à une entité
	 */
	public void init() {
		this.getEntity().setOpacity(0.50);
	}
}
