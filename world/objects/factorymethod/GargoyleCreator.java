package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.actions.ZombieQuest;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Gargoyle;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class GargoyleCreator implements ObjectCreator {
	
	Gargoyle gargoyle;
	ActionHandler gargoyleActions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		gargoyle = new Gargoyle(x, y, width, height, dir, "gargoyle");
		createActionHandler();
		gargoyle.setActionHandler(gargoyleActions);
		return gargoyle;
	}
	
	private void createActionHandler(){
		gargoyleActions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){

			}
			
			@Override
			public void update(Player player){
				try{
					if(((ZombieQuest)player.currentQuest).step3start == true){//условие связанное с квестом
						currentAction = 1;
					}
				}catch(NullPointerException e){
					
				}
			}
		};
		
		gargoyleActions.addAction(
				new Message().setTextMessage(0, "Grrrrrmmmm!!!"), 0)
				.addAction(new Message().setTextMessage(1, "Grrrrrmmmm!!!"), 1);
	}

}
