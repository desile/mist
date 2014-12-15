package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class PartyMan2Creator implements ObjectCreator {

	NPC partyMan2;
	ActionHandler partyMan2Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		partyMan2 = new NPC(x, y, width, height, dir, "partyman1");
		createActionHandler();
		partyMan2.setActionHandler(partyMan2Actions);
		return partyMan2;
	}
	
	private void createActionHandler(){
		partyMan2Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				
			}
		};
		
		partyMan2Actions.addAction(
				new Message().setTextMessage(0, "Booo, what is this tramp?"), 0);
	}

}
