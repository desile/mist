package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class NPC1Creator implements ObjectCreator {

	NPC npc1;
	ActionHandler npc1Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		npc1 = new NPC(x, y, width, height, dir, "npc_01");
		createActionHandler();
		npc1.setActionHandler(npc1Actions);
		return npc1;
	}
	
	private void createActionHandler(){
		npc1Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				
			}
		};
		
		npc1Actions.addAction(
				new Message().setTextMessage(0, "Hello, adventurer. Creator is waiting.",
												"Now go. See? Im making lovely place for you."), 0)
				.addAction(new Message().setTextMessage(1, "Crap... You are alive... Ow.",
												"I mean... Greeeetings! Glad to see you"), 1);
	}

}
