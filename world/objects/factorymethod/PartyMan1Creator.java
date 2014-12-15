package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;


public class PartyMan1Creator implements ObjectCreator {

	NPC partyMan1;
	ActionHandler partyMan1Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		partyMan1 = new NPC(x, y, width, height, dir, "partyman2");
		createActionHandler();
		partyMan1.setActionHandler(partyMan1Actions);
		return partyMan1;
	}
	
	private void createActionHandler(){
		partyMan1Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				
			}
		};
		
		partyMan1Actions.addAction(
				new Message().setTextMessage(0, "Hey, look at yourself. Here is decent society"), 0);
	}

}
