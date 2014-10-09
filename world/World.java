package com.mist.world;

import com.badlogic.gdx.Gdx;
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
	
	public GameObject testTex;
	public DynamicGameObject dynamTest;
	public DynamicGameObject test2;
	public DynamicGameObject test3;
	public DynamicGameObject test4;
	
	public Player hero;
	
	public MapHandler mapHandler;
	
	public World(){
		mapHandler = new MapHandler();
		
		testTex = new GameObject(-50,-50,0,0,"testMale");
		dynamTest = new DynamicGameObject(100,80,100,100,Direction.SOUTH,"testMale");
		test2 = new DynamicGameObject(100, 30, 50,50,Direction.WEST,"testMale");
		test3 = new DynamicGameObject(100, -20, 50,50,Direction.EAST,"testMale");
		test4 = new DynamicGameObject(100, -70, 50,50,Direction.NORTH,"testMale");
		
		hero = new Player(100, 120, 50,50,Direction.SOUTH);
	}
}
