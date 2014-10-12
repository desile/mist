package com.mist.world.objects;

public class AbleToMove {

	public boolean Right = true,
			  Left = true,
			  Down = true,
			  Up = true;
	
	public AbleToMove(){}
	
	public void setUp(boolean r, boolean l, boolean d, boolean u){
		Right = r;
		Left = l;
		Down = d;
		Up = u;
	}
	
	public void unlockAll(){
		Right = true;
		Left = true;
		Up = true;
		Down = true;
	}
	
}
