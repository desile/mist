package com.mist.actions;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mist.world.objects.Player;

public interface Action {

	public void render(OrthographicCamera uiCamera);
	
	public int action(Player player);
	
}
