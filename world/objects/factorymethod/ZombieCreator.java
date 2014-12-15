package com.mist.world.objects.factorymethod;

import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Zombie;

public class ZombieCreator implements ObjectCreator {

	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		return new Zombie(x,y,width,height,dir,"Zombie1");
	}
	
}
