package com.mist.world;

import java.util.ArrayList;

import com.mist.world.objects.Direction;
import com.mist.world.objects.Enemy;
import com.mist.world.objects.GameObject;

public class ObjectHandler{
	
	public ArrayList<GameObject> objects;
	
	public ObjectHandler(){
		objects = new ArrayList<GameObject>();
		initObjects();
	}
	
	private void initObjects(){
		objects.add(new Enemy(500, 500, 32, 32, Direction.SOUTH, "testMale2"));
		objects.add(new Enemy(160, 200, 32, 32, Direction.NORTH, "testMale2"));
		objects.add(new Enemy(160, 250, 32, 32, Direction.WEST, "testMale2"));
	}
	
}