package com.mist.world;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.mist.actions.ActionHandler;
import com.mist.actions.Message;
import com.mist.world.objects.Direction;
import com.mist.world.objects.NPC;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.factorymethod.BrokenRobotCreator;
import com.mist.world.objects.factorymethod.CivilianCreator;
import com.mist.world.objects.factorymethod.GuardianCreator;
import com.mist.world.objects.factorymethod.LudovicCreator;
import com.mist.world.objects.factorymethod.ObjectCreator;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ObjectHandler{
	
	public ArrayList<GameObject> objects;
	
	private HashMap<String, ObjectCreator> creators = new HashMap<String, ObjectCreator>();
	
	public ObjectHandler(){
		objects = new ArrayList<GameObject>();
		
		creators.put("BrokenRobot", new BrokenRobotCreator());
		creators.put("Civilian", new CivilianCreator());
		creators.put("Ludovic", new LudovicCreator());
		creators.put("Guardian", new GuardianCreator());
		
		initObjects();
	}
	
	private void initObjects(){
		objects.add(creators.get("Civilian").create(500, 500, 32, 32, Direction.SOUTH).setActionHandler(
					new ActionHandler().addAction(
							new Message().setTextMessage(1,"Hello, stranger! It is very unusal to see",
														   "adventurer in this place. Can I help you?"),0)
									   .addAction(new Message().setTextMessage(2, "Hello, again. Nice to meet you."), 1)
									   .addAction(new Message().setTextMessage(0, "Little annoying, huh..."), 2)
				));
		objects.add(creators.get("Ludovic").create(160, 200, 32, 32, Direction.NORTH).setActionHandler(
				new ActionHandler().addAction(
						new Message().setTextMessage(0,"Hello, stranger! It seems to be a very",
													   "difficult journey. Dont you think so?"),0)
			));
		objects.add(creators.get("Guardian").create(172, 222, 32, 32, Direction.WEST).setActionHandler(
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