package com.mist.world;

import com.badlogic.gdx.math.Vector2;
import com.mist.world.objects.Direction;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Player;

public class World {
	
	//TODO: Выбрать лучшую позицию камеры
	//TODO: Начать проектирование мира
	
	//TODO: Организовать систему подачи объектов в WorldRenderer
	
	public DynamicGameObject testRect;
	public GameObject testTex;
	public Player dynamTest;
	
	public World(){
		testRect = new DynamicGameObject(0,0,100,100);
		testTex = new GameObject(-50,-50,0,0);
		dynamTest = new Player(100,100,100,100,Direction.SOUTH);
	}
}
