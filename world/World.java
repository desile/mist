package com.mist.world;

import com.badlogic.gdx.math.Vector2;
import com.mist.world.objects.Direction;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Player;

public class World {
	
	//TODO: Выбрать лучшую позицию камеры
	//TODO: Начать проектирование мира
	
	public DynamicGameObject testRect;
	public GameObject testTex;
	public Player dynamTest;
	
	public World(){
		testRect = new DynamicGameObject(new Vector2(0,0),new Vector2(100,100));
		//player = new Player(new Vector2(0,0),new Vector2(100,100), Direction.SOUTH);
		testTex = new GameObject(new Vector2(-50, -50), new Vector2(50, 50));
		dynamTest = new Player(new Vector2(100,100),new Vector2(100,100),Direction.SOUTH);
	}
}
