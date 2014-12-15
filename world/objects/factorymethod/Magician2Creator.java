package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.actions.ZombieQuest;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class Magician2Creator implements ObjectCreator {

	NPC magician2;
	ActionHandler magician2Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		magician2 = new NPC(x, y, width, height, dir, "magician2");
		createActionHandler();
		magician2.setActionHandler(magician2Actions);
		return magician2;
	}
	
	private void createActionHandler(){
		magician2Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				if(player.currentQuest instanceof ZombieQuest && currentAction == 4){
					((ZombieQuest)player.currentQuest).step2infoAboutZombies = true;
				}
			}
			
			public void update(Player player){
				if(player.currentQuest instanceof ZombieQuest && currentAction == 0){
					currentAction = 1;
				}
			}
		};
		
		magician2Actions.addAction(
				new Message().setTextMessage(0, "Huh? Another martyr?"), 0)
				.addAction(new Message().setTextMessage(2, "So, I heard you can help us. Listen here."), 1)
				.addAction(new Message().setTextMessage(3, "Zombies are almost blind and stupid.",
															"Try to keep your distance and, if something happens, run."), 2)
				.addAction(new Message().setTextMessage(4, "You need activate five crystals in the hall on the right,",
															"after that barier will be destroyed"), 3)
				.addAction(new Message().setTextMessage(4, "Good luck, adventurer!"), 4);
	}

}
