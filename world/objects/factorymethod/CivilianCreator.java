package com.mist.world.objects.factorymethod;

import com.mist.world.objects.Civilian;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;

public class CivilianCreator implements ObjectCreator {

	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		return new Civilian(x, y, width, height, dir, "guardian2");
	}

}
