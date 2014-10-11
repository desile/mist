package com.mist.world.objects;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.mist.game.MistGame;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject.State;

public class Player extends DynamicGameObject {
	protected boolean justTouched = false;
	protected Vector2 destination = null;
	protected boolean moreCollisions = false;
	//TODO: Продумать и организовать систему наследования объектов
	
	public Player(Vector2 position, Vector2 rec, Direction dir, World world) {
		super(position, rec, dir, "testMale");
		//TODO: Написать автомат или мэнэджер статусов
		this.state = State.STAND;
		this.currentWorld = world;
		//TODO: Сделать привязку анимации к состояниям и направлению персонажа.
	}
	
	public Player(float x, float y, float width, float height, Direction dir, World world){
		super(x, y, width, height,dir, "testMale");
		this.state = State.STAND;
		this.currentWorld = world;
	}
	
	private int contactListen(Rectangle bounds){
		int overlaps = 0;
		for (MapObject rectangleObject : currentWorld.mapHandler.getMap().getLayers().get("collision").getObjects()) {
			//TODO: Сделать определение классов объектов и по разному их обрабатывать (пока только ректанглы)
		    Rectangle rectangle = ((RectangleMapObject) rectangleObject).getRectangle();
		    if (Intersector.overlaps(rectangle, bounds)) {
		        //world.hero.state = State.STAND;
		        overlaps++;
		    }
		    //else world.hero.state = State.WALKING;
		    //else world.hero.velocity = 1.5f;
		}
		return overlaps;
	}
	
	
	public void update(float dt, Vector2 global) {
		animation.update(dt*velocity);
		//int xIn=0,yIn=0;
		Direction curDir = direction;
		Direction newDir = curDir;
		
		destination = global;
		
		if(global == null){
			destination = position; //если координат передано не было, то текущее место и есть цель
		}
		
		if (Gdx.input.isTouched()) {
			playAnimation = true;
			state = State.WALKING;
			if(justTouched == true){
				justTouched = false;
				initIMG();
			}
			//int xIn = (int) global.x, yIn = (int) global.y;
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
				Rectangle nextPosition = new Rectangle(bounds);
				
				int smoothWay = (int) (velocity/2);
				if(smoothWay < 1 && velocity > 1) smoothWay = 1;
				//понятие smooth - сглаживание движения по траектории относительно пикселей,
				//то есть если скорость героя 3 пикселя, то он может случайно "проскочить"
				//нужный пиксель и застрять с невыполненным условием
				//smooth - проверяет условия с учетом этой погрешности
				boolean canSmoothX = destination.x < (position.x - smoothWay) || destination.x > (position.x + smoothWay);
				boolean canSmoothY = destination.y < (position.y - smoothWay) || destination.y > (position.y + smoothWay);
				
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
							nextPosition.x-=velocity;//Math.sqrt(2);
							nextPosition.y-=velocity;//Math.sqrt(2);
						}
						if(destination.x < position.x && destination.y > position.y){
							if(longWay) newDir = Direction.NORTH;
							else newDir = Direction.WEST;
							nextPosition.x-=velocity;//Math.sqrt(2);
							nextPosition.y+=velocity;//Math.sqrt(2);
						}
						if(destination.x > position.x && destination.y < position.y){
							if(longWay)newDir = Direction.SOUTH;
							else newDir = Direction.EAST;
							nextPosition.x+=velocity;//Math.sqrt(2);
							nextPosition.y-=velocity;//Math.sqrt(2);
						}
						if(destination.x > position.x && destination.y > position.y){
							if(longWay) newDir = Direction.NORTH;
							else newDir = Direction.EAST;
							nextPosition.x+=velocity;//Math.sqrt(2);
							nextPosition.y+=velocity;//Math.sqrt(2);
						}
				}
				else if(canSmoothX){
						if(destination.x < position.x){
							newDir = Direction.WEST;
							nextPosition.x-=velocity;
						}
						if(destination.x > position.x){
							newDir = Direction.EAST;
							nextPosition.x+=velocity;
						}
					}
					else if (canSmoothY){
						if(destination.y < position.y){
							newDir = Direction.SOUTH;
							nextPosition.y-=velocity;
						}
						if(destination.y > position.y){
							newDir = Direction.NORTH;
							nextPosition.y+=velocity;
						}
					}
					else {
						state = State.STAND;
						playAnimation = false;
						initIMG();
					}
				
				int contacts = contactListen(nextPosition);
				
				if(contacts == 0){//если препятствий нет
					position.x = nextPosition.x;
					position.y = nextPosition.y;
				}
				
				//TODO:
				//Сделать булеаны по возможности движению по направлениям
				//Осуществлять сначала проверку - где движение заблокировано
				//Потом использовать эту информацию, чтобы не допускать движения по этим направлениям
				
				else if(contacts == 1){
					//nextPosition = new Rectangle(bounds);
					//если есть, то в каком направлении
					//TODO: Проблема, которую стоит ли решать?
					//Когда персонаж обходит препятствие и при этом достигает одну из нужных координат по х или у - останавливается
					if(contactListen(new Rectangle(bounds).setX(position.x + velocity)) > 0){
						System.out.println("right is lock");
						/*if(canSmoothY){
							if(destination.y > position.y){
								newDir = Direction.NORTH;
								position.y += velocity;
							}
							if(destination.y < position.y){
								newDir = Direction.SOUTH;
								position.y -= velocity;
							}
						}
						if(!canSmoothY){
							playAnimation = false;
							initIMG();
						}*/
					}
					if(contactListen(new Rectangle(bounds).setY(position.y + velocity))> 0){
						System.out.println("up is lock");
						if(canSmoothX){
							if(destination.x < position.x){
								newDir = Direction.WEST;
								position.x-=velocity;
							}
							if(destination.x > position.x){
								newDir = Direction.EAST;
								position.x+=velocity;
							}
						}
						if(!canSmoothX){
							playAnimation = false;
							initIMG();
						}
					}
					if(contactListen(new Rectangle(bounds).setX(position.x - velocity))> 0){
						System.out.println("left is lock");
						if(canSmoothY){
							if(destination.y > position.y){
								newDir = Direction.NORTH;
								position.y += velocity;
							}
							if(destination.y < position.y){
								newDir = Direction.SOUTH;
								position.y -= velocity;
							}
						}
						if(!canSmoothY){
							initIMG();
							playAnimation = false;
						}
					}
					if(contactListen(new Rectangle(bounds).setY(position.y - velocity))> 0){
						System.out.println("down is lock");
						if(canSmoothX){
							if(destination.x < position.x){
								newDir = Direction.WEST;
								position.x-=velocity;
							}
							if(destination.x > position.x){
								newDir = Direction.EAST;
								position.x+=velocity;
							}
						}
						if(!canSmoothX){
							playAnimation = false;
							initIMG();
						}
					}
				}
				else{
					playAnimation = false;
					initIMG();
					System.out.println("more collisions");
					moreCollisions = true;
				}
				
				if(curDir != newDir){
					direction = newDir;
					initIMG();
				}
				
				//копируем координаты
				bounds.x = position.x;
				bounds.y = position.y;
				
				
			}
		}
	
}
