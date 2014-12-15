package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class NPC2Creator implements ObjectCreator {

	NPC npc2;
	ActionHandler npc2Actions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		npc2 = new NPC(x, y, width, height, dir, "npc_02");
		createActionHandler();
		npc2.setActionHandler(npc2Actions);
		return npc2;
	}
	
	private void createActionHandler(){
		npc2Actions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				
			}
		};
		
		npc2Actions.addAction(
				new Message().setTextMessage(0, "Arrgh-brarh-brarh. Dont touch my gold!!!"), 0);
	}

}
