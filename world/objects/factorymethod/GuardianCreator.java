package com.mist.world.objects.factorymethod;

import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Guardian;

public class GuardianCreator implements ObjectCreator {

	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		return new Guardian(x, y, width, height, dir, "guardian1");
	}
	
	/*public GameObject create(float x, float y, float width, float height,
			Direction dir, int id_sprite){
		String spritename;
				switch(id_sprite){
				case 1:
					spritename = "guardian1";
					break;
				case 2:
					spritename = "guardian2";
					break;
				case 3:
					spritename = "guardian3";
					break;
				default:
					spritename = "guardian1";
				}
		return new Guardian(x, y, width, height, dir, spritename);
		
	}*/



}
