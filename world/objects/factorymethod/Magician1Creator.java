package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.actions.TalkWithPeopleQuest;
import com.mist.actions.ZombieQuest;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class Magician1Creator implements ObjectCreator {

	NPC magician1;
	ActionHandler magician1Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		magician1 = new NPC(x, y, width, height, dir, "magician1");
		createActionHandler();
		magician1.setActionHandler(magician1Actions);
		return magician1;
	}
	
	private void createActionHandler(){
		magician1Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				if(player.currentQuest == null && currentAction == 1){
					player.currentQuest = new ZombieQuest();
				}
				if(player.currentQuest instanceof ZombieQuest && currentAction == 2){
					((ZombieQuest)player.currentQuest).step1firstTalk = true;
				}
				if(player.currentQuest instanceof ZombieQuest && currentAction == 5){
					((ZombieQuest)player.currentQuest).step3start = true;
				}
			}
			
			@Override
			public void update(Player player){
				if(player.currentQuest instanceof ZombieQuest && ((ZombieQuest)player.currentQuest).step2infoAboutZombies == true
						&& currentAction == 2){
					currentAction = 3;
				}
			}
		};
		
		magician1Actions.addAction(
				new Message().setTextMessage(1, "Yaw. Hello, buddy! We have some problems here.",
												"Hope, you can help us."), 0)
				.addAction(new Message().setTextMessage(2, "Me and my partner are stuck here because of the zombies."), 1)
				.addAction(new Message().setTextMessage(2, "I summoned stone gargolyes, so zombie were not able to go here."
															,"Ask my college about zombies and come back."), 2)
				.addAction(new Message().setTextMessage(4, "Are you ready? Alright! I will remove gargolyes."), 3)
				.addAction(new Message().setTextMessage(5, "*muttering something under his breath*"), 4)
				.addAction(new Message().setTextMessage(5, "Now fast! Go, go, go!!!"), 5);
	}


}
