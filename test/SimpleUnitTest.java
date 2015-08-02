package com.mist.test;

import org.hamcrest.Matcher;
import org.hamcrest.core.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

import com.badlogic.gdx.math.Vector2;
import com.mist.actions.ZombieQuest;
import com.mist.game.MistGame;
import com.mist.world.World;
import com.mist.world.objects.Barrier;
import com.mist.world.objects.Direction;
import com.mist.world.objects.Player;
import com.mist.world.objects.DynamicGameObject.State;

public class SimpleUnitTest {

	private final int CRYSTALS_REQUIRED = 5;
	
	private Player player;
	private ZombieQuest zombieQuest;
	private Barrier barrier;
		
	@Before
	public void setup(){
		player = new Player(new Vector2(0, 0), new Vector2(32, 32), Direction.SOUTH, null);
		zombieQuest = new ZombieQuest();
		barrier = new Barrier(new Vector2(10, 10), new Vector2(32, 32), Direction.SOUTH, null);
		player.currentQuest = zombieQuest;
	}
	
	@Test
	public void breakBarrier(){
		
		assertThat(barrier.backRender, IsEqual.equalTo(false)); //Объект существует в обычном состоянии
		
		for(int i = 1; i <= CRYSTALS_REQUIRED; i++){//собираются кристалы для разрушения барьера
			zombieQuest.updateGoal();
			barrier.update(player);
		
			if(i==CRYSTALS_REQUIRED) assertThat(barrier.backRender, IsEqual.equalTo(true)); //Объект разрушен и "существует" на фоне
			else assertThat(barrier.backRender, IsEqual.equalTo(false));
		}
	}
	
	@Test
	public void actionStatementAndInteractions(){
		assertThat(player.getState(), IsEqual.equalTo(State.STAND));
		player.setGoalObject(barrier);
		barrier.clicked = true;
		player.interactWithGoal();
		assertThat(player.getState(), IsEqual.equalTo(State.ACTION));
		barrier.clicked = false;
		assertThat(player.interactWithGoal(), IsEqual.equalTo(false));
		barrier.clicked = true;
		barrier.setCoordinate(100, 100);
		assertThat(player.interactWithGoal(), IsEqual.equalTo(false));
	}
	
	
	
}
