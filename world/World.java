package com.mist.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	public Player test2;
	public Player test3;
	public Player test4;
	
	public World(){
		testRect = new DynamicGameObject(0,0,100,100);
		testTex = new GameObject(-50,-50,0,0);
		dynamTest = new Player(100,80,100,100,Direction.SOUTH);
		test2 = new Player(100, 30, 50,50,Direction.WEST);
		test3 = new Player(100, -20, 50,50,Direction.EAST);
		test4 = new Player(100, -70, 50,50,Direction.NORTH);
		
	}
}
