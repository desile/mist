package com.mist.world.objects.factorymethod;

import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Ludovic;

public class LudovicCreator implements ObjectCreator {

	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		return new Ludovic(x, y, width, height, dir, "Ludovic");
	}

}
