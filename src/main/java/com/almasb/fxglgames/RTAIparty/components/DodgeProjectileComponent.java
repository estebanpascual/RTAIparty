package com.almasb.fxglgames.RTAIparty.components;

import java.util.Random;

import com.almasb.fxgl.entity.component.Component;

public class DodgeProjectileComponent extends Component {
    
	private static final int DOWNSPAWN 	= 0;
	private static final int UPSPWAN 	= 1;
	private static final int RIGHTSPAWN	= 2;
	private static final int LEFTSPAWN	= 3;
	
	private int spawn;
	private int lap;
	
	public void init(int lap) {
		
		this.spawn = (int)(Math.random() * 4);
		this.lap = lap;

		///System.out.println(this.spawn);
		
		switch (this.spawn) {
		case DOWNSPAWN:
			Random random = new Random();
			getEntity().setPosition(random.nextInt(300 + 300) + 300, 700);
			
			break;
		
		case UPSPWAN:
			Random random1 = new Random();
			getEntity().setPosition(random1.nextInt(300 + 300) + 300, 0);
		
			break;
			
		case RIGHTSPAWN:
			Random random2 = new Random();
			getEntity().setPosition(1200, random2.nextInt(300 + 300) + 300);
		
			break;
			
		case LEFTSPAWN:
			Random random3 = new Random();
			getEntity().setPosition(10, random3.nextInt(300 + 300) + 300);
			
			break;
			
		default:
			break;
		}
		
	}
	
    public void move() {
//    	getEntity().translateX(2);
//    	getEntity().translateY(2);
    	Random random = new Random();
    	
    	switch (this.spawn) {
		case DOWNSPAWN:
			getEntity().translate(((-1) + random.nextInt((1 - (-1)) + 1)) , -1 * (this.lap));
			
			break;
		
		case UPSPWAN:
			getEntity().translate(((-1) + random.nextInt((1 - (-1)) + 1)) , 1 * (this.lap));
			
			break;
			
		case RIGHTSPAWN:
			getEntity().translate(-1 * this.lap, ((-1) + random.nextInt((1 - (-1)) + 1)) );
		
			break;
			
		case LEFTSPAWN:
			getEntity().translate(1 * this.lap, ((-1) + random.nextInt((1 - (-1)) + 1)) );
			
			break;
			
		default:
			break;
		}
    	
    	getEntity().setRotation(getEntity().getRotation() + 2);
    }

    
}