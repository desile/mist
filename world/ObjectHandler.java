package com.mist.world;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.mist.world.objects.Direction;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.factorymethod.*;

public class ObjectHandler{
	
	public ArrayList<GameObject> objects;
	
	private HashMap<String, ObjectCreator> creators = new HashMap<String, ObjectCreator>();
	
	public ObjectHandler(){
		objects = new ArrayList<GameObject>();
		
		creators.put("BrokenRobot", new BrokenRobotCreator());
		creators.put("Civilian", new CivilianCreator());
		creators.put("Ludovic", new LudovicCreator());
		creators.put("Guardian", new GuardianCreator());
		creators.put("Zombie", new ZombieCreator());
		
		creators.put("npc1", new NPC1Creator());
		creators.put("npc2", new NPC2Creator());
		creators.put("npc3", new NPC3Creator());
		creators.put("npc4", new NPC4Creator());
		creators.put("PartyMan1", new PartyMan1Creator());
		creators.put("PartyMan2", new PartyMan2Creator());
		creators.put("Architect", new KingCreator());
		
		creators.put("Magician1", new Magician1Creator());
		creators.put("Magician2", new Magician2Creator());
		creators.put("Gargoyle", new GargoyleCreator());
		
		creators.put("Crystal", new CrystalCreator());
		creators.put("Barrier", new BarrierCreator());
		
		initObjects();
	}
	
	private void initObjects(){
		//objects.add(creators.get("Civilian").create(500, 500, 32, 32, Direction.SOUTH));
		//objects.add(creators.get("Ludovic").create(160, 200, 32, 32, Direction.NORTH));
		//objects.add(creators.get("Guardian").create(172, 222, 32, 32, Direction.WEST));
		//objects.add(creators.get("Zombie").create(500, 440, 32, 32, Direction.SOUTH));
		
		//GreenLocation
		objects.add(creators.get("npc1").create(3200, 448, 32, 32, Direction.SOUTH));
		objects.add(creators.get("npc2").create(3320, 1090, 32, 32, Direction.SOUTH));
		objects.add(creators.get("npc3").create(2960, 1900, 32, 32, Direction.SOUTH));
		objects.add(creators.get("npc4").create(3660, 2100, 32, 32, Direction.NORTH));
		objects.add(creators.get("PartyMan1").create(3372, 1885, 32, 32, Direction.EAST));
		objects.add(creators.get("PartyMan2").create(3410, 1885, 32, 32, Direction.WEST));
		objects.add(creators.get("Architect").create(3230, 2110, 32, 32, Direction.SOUTH));
		
		//ZombieLocation
		objects.add(creators.get("Magician1").create(370, 385, 32, 32, Direction.SOUTH));
		objects.add(creators.get("Magician2").create(490, 385, 32, 32, Direction.SOUTH));
		objects.add(creators.get("Gargoyle").create(610, 352, 32, 32, Direction.EAST));
		objects.add(creators.get("Gargoyle").create(610, 320, 32, 32, Direction.EAST));
		objects.add(creators.get("Gargoyle").create(610, 286, 32, 32, Direction.EAST));
		
		objects.add(creators.get("Crystal").create(1424, 1040, 32, 32, null));
		objects.add(creators.get("Crystal").create(2276, 1036, 32, 32, null));
		objects.add(creators.get("Crystal").create(2288, 135, 32, 32, null));
		objects.add(creators.get("Crystal").create(1688, 120, 32, 32, null));
		objects.add(creators.get("Crystal").create(1844, 514, 32, 32, null));
		
		objects.add(creators.get("Barrier").create(2110, 1120, 32, 32, null));
		objects.add(creators.get("Barrier").create(2142, 1120, 32, 32, null));
		objects.add(creators.get("Barrier").create(2174, 1120, 32, 32, null));
		objects.add(creators.get("Barrier").create(2206, 1120, 32, 32, null));
		
		objects.add(creators.get("Zombie").create(1530, 960, 32, 32, Direction.SOUTH));
		objects.add(creators.get("Zombie").create(1689, 230, 32, 32, Direction.SOUTH));
		objects.add(creators.get("Zombie").create(1978, 514, 32, 32, Direction.SOUTH));
		
		
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