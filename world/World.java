package com.mist.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mist.world.objects.Direction;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Player;

public class World {
	
	//TODO: Выбрать лучшую позицию камеры
	//TODO: Начать проектирование мира
	
	//TODO: Организовать систему подачи объектов в WorldRenderer
	
	public GameObject testTex;
	
	public Player hero;
	
	final int StartX = 3252;
	final int StartY = 240;
	
	public MapHandler mapHandler;
	public ObjectHandler objectHandler;
	
	public World(){
		mapHandler = new MapHandler();
		objectHandler = new ObjectHandler();
		
		hero = new Player(3252, 240, 50,50,Direction.SOUTH,this);
	}
}
