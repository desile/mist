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
	
	public MapHandler mapHandler;
	public ObjectHandler objectHandler;
	
	public World(){
		mapHandler = new MapHandler();
		objectHandler = new ObjectHandler();
		
		hero = new Player(100, 120, 50,50,Direction.SOUTH,this);
	}
}
