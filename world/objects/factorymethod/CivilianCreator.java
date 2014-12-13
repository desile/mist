package com.mist.world.objects.factorymethod;

import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.actions.Quest;
import com.mist.actions.TalkWithPeopleQuest;
import com.mist.world.objects.Civilian;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Player;

public class CivilianCreator implements ObjectCreator {

	Civilian civilian;
	ActionHandler civilianActions;
	
	@Override
	public GameObject create(float x, float y, float width, float height,
			Direction dir) {
		civilian = new Civilian(x, y, width, height, dir, "guardian2");
		createActionHandler();
		
		civilian.setActionHandler(civilianActions);
		return civilian;
	}
	
	private void createActionHandler(){
		civilianActions = new ActionHandler(){
			@Override
			public void dialogLogic(Player player){
				//если квест взят
				if(player.currentQuest instanceof TalkWithPeopleQuest){
					//и выполнен
					if(((TalkWithPeopleQuest)player.currentQuest).checkGoals()){
						player.currentQuest = null;
					}
				}
				
				//если квеста нету
				if(player.currentQuest == null && currentAction == 1){
					player.currentQuest = new TalkWithPeopleQuest();
				}
			}
			
			@Override
			public void update(Player player){//запущен всегда в игровом цикле
				if(player.currentQuest instanceof TalkWithPeopleQuest){
					//после выполнения квеста переключит сообщение с 3 на 5.
					if(((TalkWithPeopleQuest)player.currentQuest).checkGoals() && currentAction == 3){
						currentAction = 5;
					}
				}

			}
		};
		civilianActions.addAction(
							new Message().setTextMessage(1,"Hello, stranger! It is very unusal to see",
						   "adventurer in this place. Can I help you?"),0)
						   	.addAction(new Message().setTextMessage(3, "Go and talk with the people in the village."), 1)
						   	.addAction(new Message().setTextMessage(0, "Little annoying, huh..."), 2)
						    .addAction(new Message().setTextMessage(3, "GO AND TALK!"),3)
							.addAction(new Message().setTextMessage(4,"Nothing to you anymore."),4)
							.addAction(new Message().setTextMessage(4, "Great job!"), 5);
		
	}

}
