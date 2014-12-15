package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class NPC4Creator implements ObjectCreator {

	NPC npc4;
	ActionHandler npc4Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		npc4 = new NPC(x, y, width, height, dir, "npc_04");
		createActionHandler();
		npc4.setActionHandler(npc4Actions);
		return npc4;
	}
	
	private void createActionHandler(){
		npc4Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				
			}
		};
		
		npc4Actions.addAction(
				new Message().setTextMessage(0, "I've always wondered what is on the other side",
												"of that tunnel..."), 0);
	}


}
