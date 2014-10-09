package com.mist.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mist.game.MistGame;
import com.mist.world.objects.DynamicGameObject.State;

public class Player extends DynamicGameObject {
	protected boolean justTouched = false;
	protected Vector2 destination = null;
	//TODO: Продумать и организовать систему наследования объектов
	
	public Player(Vector2 position, Vector2 rec, Direction dir) {
		super(position, rec, dir, "testMale");
		//TODO: Написать автомат или мэнэджер статусов
		this.state = State.STAND;
		//TODO: Сделать привязку анимации к состояниям и направлению персонажа.
	}
	
	public Player(float x, float y, float width, float height, Direction dir){
		super(x, y, width, height,dir, "testMale");
		this.state = State.STAND;
	}
	
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
				initIMG();
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
				boolean longWay = wayX > wayY;
				
				longWay = !longWay; //опциональное изменение логики смены анимации
				
				if(canSmoothX && canSmoothY){
						//TODO: Уменьшить скорость для диагонального движения
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
						//System.out.println("stand state");
						playAnimation = false;
						initIMG();
					}
				if(curDir != newDir){
					if(newDir == null) return;
					direction = newDir;
					initIMG();
				}
			}
			
			bounds.x = position.x;
			bounds.y = position.y;
		}
		/*else{
			justTouched = true;
			playAnimation = false;
			initTest();
		}*/
}
