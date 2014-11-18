package com.mist.world.objects.factorymethod;

import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;

public interface ObjectCreator {

	public GameObject create(float x, float y, float width, float height, Direction dir);
	
}
