package com.mist.world.objects;

import com.badlogic.gdx.math.Intersector;
import com.mist.actions.ZombieQuest;
import com.mist.world.World;

public class Crystal extends DynamicGameObject {

	private boolean isActivated = false;
	
	public Crystal(float x, float y, float width, float height, Direction dir,
			String textureName) {
		super(x, y, width, height, dir, textureName);
	}
	
	private boolean activateByPlayer(Player player){
		if(Intersector.overlaps(this.bounds, player.bounds)){
			direction = Direction.NORTH;
			initIMG();
			isActivated = true;
			System.out.println("CONTACT!!");
			return true;
		}
		else{
			return false;
		}
	}
	
	public void update(float dt,Player player, World world){
		if(isActivated==false && activateByPlayer(player)){
			if(player.currentQuest instanceof ZombieQuest)
				((ZombieQuest)player.currentQuest).crystalsActivated++;
		}
	}

}
