package com.mist.controllers;

import com.badlogic.gdx.math.Vector2;
import com.mist.world.World;

public class WorldController {
	
	//TODO: Управление с клавы и мыши
	//TODO: Связь входных значений с поведением объекта

	World world;
	
	public WorldController(World world){
		this.world = world;
	}
	
	public void update(float delta, Vector2 globalCoordinates){
		world.dynamTest.update(delta);
		world.test2.update(delta);
		world.test3.update(delta);
		world.test4.update(delta);
		world.hero.update(delta,globalCoordinates);
	}
	
}
