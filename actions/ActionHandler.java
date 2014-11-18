package com.mist.actions;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mist.world.objects.Player;

/*
 * TODO:
 * Построить временную шкалу вызова функций и методов.
 */

public class ActionHandler {

	private ArrayList<Action> actions;
	private int currentAction;
	private int new_action;
	
	public ActionHandler() {
		actions = new ArrayList<Action>();
		currentAction = 0;
		new_action = -1;
	}
	
	public void action(Player player){
		new_action = actions.get(currentAction).action(player);
	}
	
	//TODO: ??? Изредка возникающая ошибка переполнения массива
	public void render(OrthographicCamera uiCamera){
		actions.get(currentAction).render(uiCamera);
	}
	
	//загуглить: уловить момент когда изменяется переменная (ссылка)
	public void update(){//TODO: вызвать где-нибудь апдэйт экшна
		if(Gdx.input.justTouched()){
			System.out.println(new_action);
			if(new_action == -1) //просто переходим к следующему элементу массива
				currentAction++;
			else{
				currentAction = new_action;
			}
		}
	}
	
	public ActionHandler addAction(Action action){
		actions.add(action);
		return this;
	}
	
	public ActionHandler addAction(Action action, int numOfAction){
		actions.add(numOfAction, action);
		return this;
	}
		
}
