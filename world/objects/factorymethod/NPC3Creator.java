package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class NPC3Creator implements ObjectCreator {

	NPC npc3;
	ActionHandler npc3Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		npc3 = new NPC(x, y, width, height, dir, "npc_03");
		createActionHandler();
		npc3.setActionHandler(npc3Actions);
		return npc3;
	}
	
	private void createActionHandler(){
		npc3Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				
			}
		};
		
		npc3Actions.addAction(
				new Message().setTextMessage(1, "Blah-blah-blah... ",
												"The Creator was too lazy to make dialogs for me. "), 0)
				.addAction(new Message().setTextMessage(0, "So I will never make friends."), 1);
	}

}
