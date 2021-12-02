package com.almasb.fxglgames.RTAIparty.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class DodgePlayerComponent extends Component {
	
	private int speedPixel;
	private Entity limit;
	
	public DodgePlayerComponent() {
		System.out.println(this.isPaused());
	}
	
	enum MoveDirection {
        UP, RIGHT, DOWN, LEFT
	}

    public void up() {
    	getEntity().translateY(-2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateY(2 * this.speedPixel);		
    	}
    	
    }

    public void down() {
    	getEntity().translateY(2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateY(-2 * this.speedPixel);
    	}
    }

    public void left() {
    	getEntity().translateX(-2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateX(2 * this.speedPixel);
    	}
    	
    }

    public void right() {
    	getEntity().translateX(2 * this.speedPixel);
    	if(!(limit.getBoundingBoxComponent().isCollidingWith(this.getEntity().getBoundingBoxComponent()))) {
    		getEntity().translateX(-2 * this.speedPixel);
    	}
    }
    
    public void addLimit(Entity limit) {
    	this.limit = limit;
    }
    
    public void setSpeed(int speed) {
    	this.speedPixel = speed;
    }

    @Override
    public void onUpdate(double tpf) {
    	System.out.println("test");
    }
}
