package com.mist.world.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {

	protected Vector2 position;
	protected Rectangle bounds;
	
	public GameObject(Vector2 position, Vector2 rec){ //links constructor
		this.position = position;
		this.bounds = new Rectangle(position.x - rec.x, position.y - rec.y, rec.x, rec.y);
	}
	
	public GameObject (float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
	}
	
}
