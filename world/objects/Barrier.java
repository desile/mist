package com.mist.world.objects;

import com.mist.actions.ZombieQuest;
import com.mist.world.World;

public class Barrier extends DynamicGameObject {

	
	public Barrier(float x, float y, float width, float height, Direction dir,
			String textureName) {
		super(x, y, width, height, dir, textureName);
		obstruction = true;
	}
	
	public void update(float dt,Player player, World world){
		if(player.currentQuest instanceof ZombieQuest){
			if(((ZombieQuest)player.currentQuest).crystalsActivated == 5){
				direction = Direction.NORTH;
				initIMG();
				obstruction = false;
				backRender = true;
			}
		}	

	}
}
