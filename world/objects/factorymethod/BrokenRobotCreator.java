package com.mist.world.objects.factorymethod;

import com.mist.world.objects.BrokenRobot;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;

public class BrokenRobotCreator implements ObjectCreator {

	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		return new BrokenRobot(x, y, width, height, dir, "?");
	}

}
