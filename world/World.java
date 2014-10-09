package com.mist.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mist.world.objects.Direction;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Player;

public class World {
	
	//TODO: Выбрать лучшую позицию камеры
	//TODO: Начать проектирование мира
	
	//TODO: Организовать систему подачи объектов в WorldRenderer
	
	public DynamicGameObject testRect;
	public GameObject testTex;
	public Player dynamTest;
	public Player test2;
	public Player test3;
	public Player test4;
	
	public Player hero;
	
	public World(){
		testRect = new DynamicGameObject(0,0,100,100);
		testTex = new GameObject(-50,-50,0,0);
		dynamTest = new Player(100,80,100,100,Direction.SOUTH);
		test2 = new Player(100, 30, 50,50,Direction.WEST);
		test3 = new Player(100, -20, 50,50,Direction.EAST);
		test4 = new Player(100, -70, 50,50,Direction.NORTH);
		
		hero = new Player(100, 120, 50,50,Direction.SOUTH){
			@Override
			public void update(float dt, Vector2 global) {
				animation.update(dt*velocity);
				//int xIn=0,yIn=0;
				Direction curDir = direction;
				Direction newDir = null;
				if (Gdx.input.isTouched()) {
					playAnimation = true;
					state = State.WALKING;
					if(justTouched == true){
						justTouched = false;
						initTest();
					}
					//int xIn = (int) global.x, yIn = (int) global.y;
					destination = global;
					//xIn = (int) destination.x; yIn = (int) destination.y;
				}
				else{
					justTouched = true;
					//initTest();
				}
					/*if(xIn!=position.x){
						if(xIn < position.x){
							newDir = Direction.WEST;
							position.x--;
						}
						if(xIn > position.x){
							newDir = Direction.EAST;
							position.x++;
						}
					}
					else{
						if(yIn < position.y){
							newDir = Direction.SOUTH;
							position.y--;
						}
						if(yIn > position.y){
							newDir = Direction.NORTH;
							position.y++;
						}
					}*/
					if(state == State.WALKING){
						int smoothWay = (int) (velocity/2);
						if(smoothWay < 1 && velocity > 1) smoothWay = 1;
						
						boolean canSmoothX = destination.x < position.x - smoothWay || destination.x > position.x + smoothWay;
						boolean canSmoothY = destination.y < position.y - smoothWay || destination.y > position.y + smoothWay;
						
						float wayX = Math.abs(position.x - destination.x);
						float wayY = Math.abs(position.y - destination.y);
						boolean longWay = wayX > wayY; //true when wayX bigger, false when wayY bigger
						
						if(canSmoothX && canSmoothY){
								//TODO: less speed for diagonal movement
								//(problem with smoothWay - animation bug)
								if(destination.x < position.x && destination.y < position.y){
									if(longWay) newDir = Direction.SOUTH;
									else newDir = Direction.WEST;
									position.x-=velocity;//Math.sqrt(2);
									position.y-=velocity;//Math.sqrt(2);
								}
								if(destination.x < position.x && destination.y > position.y){
									if(longWay) newDir = Direction.NORTH;
									else newDir = Direction.WEST;
									position.x-=velocity;//Math.sqrt(2);
									position.y+=velocity;//Math.sqrt(2);
								}
								if(destination.x > position.x && destination.y < position.y){
									if(longWay)newDir = Direction.SOUTH;
									else newDir = Direction.EAST;
									position.x+=velocity;//Math.sqrt(2);
									position.y-=velocity;//Math.sqrt(2);
								}
								if(destination.x > position.x && destination.y > position.y){
									if(longWay) newDir = Direction.NORTH;
									else newDir = Direction.EAST;
									position.x+=velocity;//Math.sqrt(2);
									position.y+=velocity;//Math.sqrt(2);
								}
						}
						else if(canSmoothX){
								if(destination.x < position.x){
									newDir = Direction.WEST;
									position.x-=velocity;
								}
								if(destination.x > position.x){
									newDir = Direction.EAST;
									position.x+=velocity;
								}
							}
							else if (canSmoothY){
								if(destination.y < position.y){
									newDir = Direction.SOUTH;
									position.y-=velocity;
								}
								if(destination.y > position.y){
									newDir = Direction.NORTH;
									position.y+=velocity;
								}
							}
							else {
								state = State.STAND;
								System.out.println("stand state");
								playAnimation = false;
								initTest();
							}
						if(curDir != newDir){
							if(newDir == null) {System.out.println("null direction"); return;}
							direction = newDir;
							initTest();
						}
					}
				}
				/*else{
					justTouched = true;
					playAnimation = false;
					initTest();
				}*/
		};
	}
}
