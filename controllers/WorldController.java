package com.mist.controllers;

import com.mist.world.World;

public class WorldController {
	
	//TODO: Управление с клавы и мыши
	//TODO: Связь входных значений с поведением объекта

	World world;
	
	public WorldController(World world){
		this.world = world;
	}
	
	public void update(float delta){
		world.dynamTest.update(delta);
	}
	
}
