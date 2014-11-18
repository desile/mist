package com.mist.world;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.NPC;
import com.mist.world.objects.GameObject;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ObjectHandler{
	
	public ArrayList<GameObject> objects;
	
	public ObjectHandler(){
		objects = new ArrayList<GameObject>();
		initObjects();
	}
	
	private void initObjects(){
		objects.add(new NPC(500, 500, 32, 32, Direction.SOUTH, "testMale2").setActionHandler(
					new ActionHandler().addAction(
							new Message().setTextMessage(1,"Hello, stranger! It is very unusal to see",
														   "adventurer in this place. Can I help you?"),0)
									   .addAction(new Message().setTextMessage(2, "Hello, again. Nice to meet you."), 1)
									   .addAction(new Message().setTextMessage(0, "Little annoying, huh..."), 2)
				));
		objects.add(new NPC(160, 200, 32, 32, Direction.NORTH, "testMale2").setActionHandler(
				new ActionHandler().addAction(
						new Message().setTextMessage(0,"Hello, stranger! It seems to be a very",
													   "difficult journey. Dont you think so?"),0)
			));
		objects.add(new NPC(172, 222, 32, 32, Direction.WEST, "testMale2").setActionHandler(
				new ActionHandler().addAction(
						new Message().setTextMessage(0,"Waka-maka-fone"),0)
			));
	}
	
	public GameObject searchObject(Vector2 point){
		for(GameObject object : objects){
			if(object.getBounds().contains(point.x, point.y) && object.canInteraction()){
				return object;
			}
		}
		return null;
	}
	
}