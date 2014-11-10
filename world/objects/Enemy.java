package com.mist.world.objects;

public class Enemy extends DynamicGameObject {
	
	

	public Enemy(float x, float y, float width, float height, Direction dir,
			String textureName) {
		super(x, y, width, height, dir, textureName);
		interaction = true;
	}

}
