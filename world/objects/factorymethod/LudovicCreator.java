package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.actions.TalkWithPeopleQuest;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Ludovic;
import com.mist.world.objects.Player;

public class LudovicCreator implements ObjectCreator {

	Ludovic ludovic;
	ActionHandler ludovicActions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		ludovic =  new Ludovic(x, y, width, height, dir, "Ludovic");
		createActionHandler();
		ludovic.setActionHandler(ludovicActions);
		return ludovic;
	}

	private void createActionHandler(){
		ludovicActions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				if(player.currentQuest instanceof TalkWithPeopleQuest){
					((TalkWithPeopleQuest)player.currentQuest).talkWithLudovic = true;
				}
				if(player.currentQuest == null){
					currentAction = 0;
				}
			}
		};
		ludovicActions.addAction(
				new Message().setTextMessage(1,"Hello, stranger! It seems to be a very",
						   "difficult journey. Dont you think so?"),0)
						   .addAction(new Message().setTextMessage(1, "Good Luck!"), 1);
	}
	
}
