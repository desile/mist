package com.mist.world.objects.factorymethod;

import com.mist.world.objects.Crystal;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;

public class CrystalCreator implements ObjectCreator {

	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		// TODO Auto-generated method stub
		return new Crystal(x, y, width, height, Direction.SOUTH, "QuestCrystal");
	}

}
