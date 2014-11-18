package com.mist.world.objects;

public class NPC extends DynamicGameObject {
	
	public NPC(float x, float y, float width, float height, Direction dir,
			String textureName) {
		super(x, y, width, height, dir, textureName);
		interaction = true;
	}

}
