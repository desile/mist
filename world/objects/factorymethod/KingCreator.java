package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.NPC;
import com.mist.world.objects.Player;

public class KingCreator implements ObjectCreator {

	NPC architect;
	ActionHandler architectActions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		architect = new NPC(x, y, width, height, dir, "architect");
		createActionHandler();
		architect.setActionHandler(architectActions);
		return architect;
	}
	
	private void createActionHandler(){
		architectActions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				if(currentAction == 4){
					player.setCoordinate(192, 330);
				}
			}
		};
		
		architectActions.addAction(
				new Message().setTextMessage(1, "Oh. I was waiting for you! Come here!"), 0)
				.addAction(new Message().setTextMessage(2, "You are gonna test my project!",
															"But there is no reason to worry."), 1)
				.addAction(new Message().setTextMessage(3, "If something does happen to you,",
															"We will find new one"), 2)
				.addAction(new Message().setTextMessage(4, "People are easily replaceable, you know.",
															"So good luck. May the force be with you."),3)
				.addAction(new Message().setTextMessage(4, "People are easily replaceable, you know.",
						"So good luck. May the force be with you."),4);
	}

}
