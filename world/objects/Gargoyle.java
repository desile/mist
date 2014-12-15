package com.mist.world.objects;

import com.mist.world.objects.DynamicGameObject.State;

public class Gargoyle extends NPC {

	public Gargoyle(float x, float y, float width, float height, Direction dir,
			String textureName) {
		super(x, y, width, height, dir, textureName);
		velocity = 2.4f;
		obstruction = true;
	}

	@Override
	public void update(float dt){
		animation.update(dt);
		if(actionHandler.currentAction == 1 && bounds.x < 5000){
			if(state!=State.WALKING)setState(State.WALKING);
			bounds.x += velocity;
		}
	}
	
}
