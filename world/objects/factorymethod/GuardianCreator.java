package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.actions.TalkWithPeopleQuest;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Guardian;
import com.mist.world.objects.Player;

public class GuardianCreator implements ObjectCreator {

	Guardian guardian;
	ActionHandler guardianActions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		guardian =new Guardian(x, y, width, height, dir, "guardian1");
		
		createActionHandler();
		guardian.setActionHandler(guardianActions);
		
		return guardian;
	}
	
	private void createActionHandler(){
		guardianActions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				if(player.currentQuest instanceof TalkWithPeopleQuest){
					((TalkWithPeopleQuest)player.currentQuest).talkWithGuardian = true;
				}
				if(player.currentQuest == null){
					currentAction = 0;
				}
			}
		};
		
		guardianActions.addAction(
				new Message().setTextMessage(1,"Hi... you came...","with... a deal?..."),0)
				.addAction(new Message().setTextMessage(2, "Oh... you... from him?"), 1)
				.addAction(new Message().setTextMessage(2, "Go..."), 2);	
	}


}
