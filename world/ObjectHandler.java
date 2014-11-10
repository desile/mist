package com.mist.world;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
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
		objects.add(new Enemy(172, 222, 32, 32, Direction.WEST, "testMale2"));
	}
	
	public GameObject searchObject(Vector2 point){
		for(GameObject object : objects){
			if(object.getBounds().contains(point.x, point.y) && object.canInteraction()){
				return object;
			}
		}
		return null;
	}
	
}