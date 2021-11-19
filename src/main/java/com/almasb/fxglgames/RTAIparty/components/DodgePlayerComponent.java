package com.almasb.fxglgames.RTAIparty.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.CellMoveComponent;

public class DodgePlayerComponent extends Component {
	
	private int speedPixel;
	
	enum MoveDirection {
        UP, RIGHT, DOWN, LEFT
	}

    public void up() {
    	getEntity().setY(getEntity().getY() - 2);
        //nextMoveDir = MoveDirection.UP;
    }

    public void down() {
    	getEntity().setY(getEntity().getY() + 2);
        //nextMoveDir = MoveDirection.DOWN;
    }

    public void left() {
    	getEntity().setX(getEntity().getX() - 2);
    }

    public void right() {
    	getEntity().setX(getEntity().getX() + 2);
    }

    @Override
    public void onUpdate(double tpf) {
    	
    	
    }
}
