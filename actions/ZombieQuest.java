package com.mist.actions;

public class ZombieQuest implements Quest {

	public int crystalsActivated;

	public boolean step1firstTalk = false;
	public boolean step2infoAboutZombies = false;
	public boolean step3start = false;
	
	
	
	
	public ZombieQuest(){
		crystalsActivated = 0;
	}
	
	@Override
	public void updateGoal() {
		crystalsActivated++;
	}

}
