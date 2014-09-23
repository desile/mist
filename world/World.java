package com.mist.world;

import com.badlogic.gdx.math.Vector2;
import com.mist.world.objects.Direction;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.Player;

public class World {
	public DynamicGameObject testRect;
	
	public World(){
		testRect = new DynamicGameObject(new Vector2(0,0),new Vector2(100,100));
	}
}
