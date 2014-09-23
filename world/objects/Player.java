package com.mist.world.objects;

import com.badlogic.gdx.math.Vector2;

public class Player extends DynamicGameObject {

	
	
	public Player(Vector2 position, Vector2 rec, Direction dir) {
		super(position, rec, dir);
		this.state = State.STAND;
	}

}
