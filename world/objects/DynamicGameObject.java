package com.mist.world.objects;

import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject {
	
	public enum State{
		NONE, WALKING, STAND, DEAD, CAST, FIGHT
	}
	
	protected final float MIN_SPEED = 1f;
	protected Direction direction;
	protected State state;

	public DynamicGameObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		direction = Direction.SOUTH;
		state = State.NONE;
	}
	
	public DynamicGameObject(Vector2 position, Vector2 rec){
		super(position, rec);
		direction = Direction.SOUTH;
		state = State.NONE;
	}
	
	public DynamicGameObject(Vector2 position, Vector2 rec, Direction dir){
		super(position, rec);
		direction = dir;
		state = State.NONE;
	}

}
