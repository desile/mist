package com.mist.controllers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mist.world.World;
import com.mist.world.objects.Barrier;
import com.mist.world.objects.Crystal;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.DynamicGameObject.State;
import com.mist.world.objects.Zombie;

public class WorldController {
	
	//TODO: Управление с клавы и мыши
	//TODO: Связь входных значений с поведением объекта

	World world;
	
	public WorldController(World world){
		this.world = world;
	}
	
	public void update(float delta, Vector2 globalCoordinates){
		world.hero.update(delta,globalCoordinates);
		updateObjects(delta);
	}
	
	private void updateObjects(float delta){
		for(GameObject obj : world.objectHandler.objects){
			obj.update(delta);
			obj.actionHandler.update(world.hero);
			if(obj instanceof Zombie){
				((Zombie)obj).update(delta,world.hero,world);
			}
			if(obj instanceof Crystal){
				((Crystal)obj).update(delta,world.hero,world);
			}
			if(obj instanceof Barrier){
				((Barrier)obj).update(delta,world.hero,world);
			}
		}
	}
	
}
