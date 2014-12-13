package com.mist.actions;

public class TalkWithPeopleQuest implements Quest {

	public int step;
	public boolean talkWithGuardian = false;
	public boolean talkWithLudovic = false;
	
	public TalkWithPeopleQuest(){
		step = 0;
	}
	
	@Override
	public void updateGoal() {
		
	}

	public boolean checkGoals(){
		return talkWithGuardian&talkWithLudovic;
	}
	
}
