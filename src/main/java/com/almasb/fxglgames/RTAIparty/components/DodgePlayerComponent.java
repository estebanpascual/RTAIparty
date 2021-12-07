package com.almasb.fxglgames.RTAIparty.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

/**
 * @author GROUPE5
 * Classe permettant d'être attaché à une entitée
 * Permet le déplacement du joueur
 *
 */
public class DodgePlayerComponent extends Component {
	
	private int speedPixel;
	private Entity limit;
	
	
	enum MoveDirection {
        UP, RIGHT, DOWN, LEFT
	}

    /**
     * Fonction de mouvement vers le haut
     */
    public void up() {
    	getEntity().translateY(-2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateY(2 * this.speedPixel);		
    	}
    	
    }

    /**
     * Fonction de mouvement vers le bas
     */
    public void down() {
    	getEntity().translateY(2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateY(-2 * this.speedPixel);
    	}
    }

    /**
     * Fonction de mouvement vers la gauche
     */
    public void left() {
    	getEntity().translateX(-2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateX(2 * this.speedPixel);
    	}
    	
    }

    /**
     * Fonction de mouvement vers la droite
     */
    public void right() {
    	getEntity().translateX(2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateX(-2 * this.speedPixel);
    	}
    }
    
    /**
     * @param limit Paramètre d'entité de zone
     * Ajoute la zone de limite du joueur
     */
    public void addLimit(Entity limit) {
    	this.limit = limit;
    }
    
    /**
     * @param speed Paramètre de nombre de pixel
     * Ajoute le nombre de pixel de déplacement
     */
    public void setSpeed(int speed) {
    	this.speedPixel = speed;
    }

}
