package com.mist.controllers;

import com.mist.world.World;

public class WorldController {
	
	//TODO: ���������� � ����� � ����
	//TODO: ����� ������� �������� � ���������� �������

	World world;
	
	public WorldController(World world){
		this.world = world;
	}
	
	public void update(float delta){
		world.dynamTest.update(delta);
	}
	
}
