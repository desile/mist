package com.mist.world.objects.factorymethod;

import com.mist.world.objects.Barrier;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;

public class BarrierCreator implements ObjectCreator {

	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		return new Barrier(x, y, width, height, Direction.SOUTH, "barrier");
	}

}
