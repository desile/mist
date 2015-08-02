package com.mist.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.mist.actions.Quest;
import com.mist.game.MistGame;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject.State;

public class Player extends DynamicGameObject {
	protected boolean justTouched = false;
	protected Vector2 destination = null;
	protected boolean moreCollisions = false;
	
	private GameObject goalObject;
	
	public Quest currentQuest; //можно сделать список квестов, но одного наверное будет достаточно
	
	public boolean inAction = false; //взаимодействует ли в данный момент
	
	public DynamicGameObject target;
	
	Rectangle nextPosition = new Rectangle();
	
	//TODO: Продумать и организовать систему наследования объектов
	
	public Player(Vector2 bounds, Vector2 rec, Direction dir, World world) {
		super(bounds, rec, dir, "testMale");
		//TODO: Написать автомат или мэнэджер статусов
		this.state = State.STAND;
		this.currentWorld = world;
		//TODO: Сделать привязку анимации к состояниям и направлению персонажа.
	}
	
	public Player(float x, float y, float width, float height, Direction dir, World world){
		super(x, y, width, height,dir, "testMale");
		this.state = State.STAND;
		this.velocity = 1.8f;
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
		for(GameObject object : currentWorld.objectHandler.objects){
			Rectangle rectangle = object.bounds;
			if(Intersector.overlaps(rectangle, bounds) && object.isObstruction()){
				overlaps++;
			}
		}
		return overlaps;
	}
	
	public boolean interactWithGoal(){
		if(goalObject!=null)
			if(Intersector.overlaps(goalObject.bounds, bounds) && goalObject.clicked){
				setState(State.ACTION);
				return true;
			}
			else return false;
		return false;
	}
	
	
	public void update(float dt, Vector2 global) {
		inAction = interactWithGoal();
		if(getState()!=State.ACTION){ //если персонаж взаимодейтсвует с предметом
								 //движение не осуществляется
		animation.update(dt*velocity);
		//int xIn=0,yIn=0;
		
		destination = global;
		
		if(global == null){
			destination.x = bounds.x; //если координат передано не было, то текущее место и есть цель
			destination.y = bounds.y;
		}
		
		/*if (Gdx.input.isTouched()) {
			playAnimation = true;
			state = State.WALKING;
			if(justTouched == true){
				justTouched = false;
				initIMG();
			}
		}*/
		else{
			justTouched = true;
		}
		
		if(state == State.WALKING){
				walking();
			}
		
		}
		if(goalObject!=null) goalObject.action(inAction,this);
	
	}

	public GameObject getGoalObject() {
		return goalObject;
	}

	public void setGoalObject(GameObject goalObject) {
		this.goalObject = goalObject;
	}
	
	private void walking(){
		Direction curDir = direction;
		Direction newDir = curDir;
		
		//когда персонаж входит в тупик - есть один путь обратно
		//все из-за того, что если ему дать возможость пройти в другом направлении,
		//то он пройдет сквозь стены
		if(moreCollisions){
			if(deadlockInTheCorner()){
				return;
			}
		}
		
		nextPosition.set(bounds);
		
		int smoothWay = (int) (velocity/2);
		if(smoothWay < 1 && velocity > 1) smoothWay = 1;
		//понятие smooth - сглаживание движения по траектории относительно пикселей,
		//то есть если скорость героя 3 пикселя, то он может случайно "проскочить"
		//нужный пиксель и застрять с невыполненным условием
		//smooth - проверяет условия с учетом этой погрешности
		boolean canSmoothX = destination.x < (bounds.x - smoothWay) || destination.x > (bounds.x + smoothWay);
		boolean canSmoothY = destination.y < (bounds.y - smoothWay) || destination.y > (bounds.y + smoothWay);
		
		float wayX = Math.abs(bounds.x - destination.x);
		float wayY = Math.abs(bounds.y - destination.y);
		boolean longWay = wayX > wayY;
		
		longWay = !longWay; //опциональное изменение логики смены анимации
		
		
			if(canSmoothX && canSmoothY){
					//TODO: Уменьшить скорость для диагонального движения
					//TODO: Исправить скорость при прохождении между двумя объектами
					//(problem with smoothWay - animation bug)
					if(destination.x < bounds.x && destination.y < bounds.y){
						if(longWay) newDir = Direction.SOUTH;
						else newDir = Direction.WEST;
						nextPosition.x-=velocity;//Math.sqrt(2);
						nextPosition.y-=velocity;//Math.sqrt(2);
					}
					if(destination.x < bounds.x && destination.y > bounds.y){
						if(longWay) newDir = Direction.NORTH;
						else newDir = Direction.WEST;
						nextPosition.x-=velocity;//Math.sqrt(2);
						nextPosition.y+=velocity;//Math.sqrt(2);
					}
					if(destination.x > bounds.x && destination.y < bounds.y){
						if(longWay)newDir = Direction.SOUTH;
						else newDir = Direction.EAST;
						nextPosition.x+=velocity;//Math.sqrt(2);
						nextPosition.y-=velocity;//Math.sqrt(2);
					}
					if(destination.x > bounds.x && destination.y > bounds.y){
						if(longWay) newDir = Direction.NORTH;
						else newDir = Direction.EAST;
						nextPosition.x+=velocity;//Math.sqrt(2);
						nextPosition.y+=velocity;//Math.sqrt(2);
					}
			}
			else if(canSmoothX){
					if(destination.x < bounds.x){
						newDir = Direction.WEST;
						nextPosition.x-=velocity;
					}
					if(destination.x > bounds.x){
						newDir = Direction.EAST;
						nextPosition.x+=velocity;
					}
				}
				else if (canSmoothY){
					if(destination.y < bounds.y){
						newDir = Direction.SOUTH;
						nextPosition.y-=velocity;
					}
					if(destination.y > bounds.y){
						newDir = Direction.NORTH;
						nextPosition.y+=velocity;
					}
				}
				else {
					setState(State.STAND);
				}
			
			int contacts = contactListen(nextPosition);
			
			if(contacts == 0){//если препятствий нет
				ableToMove.unlockAll();
				bounds.x = nextPosition.x;
				bounds.y = nextPosition.y;
			}
			else{
				checkSideOfOverlaps();
			}
			
			//TODO: ДО ЛУЧШИХ ВРЕМЕН
			if(contacts == 1){
				//nextPosition = new Rectangle(bounds);
				//если есть, то в каком направлении
				//TODO: Проблема, которую стоит ли решать?
				//Когда персонаж обходит препятствие и при этом достигает одну из нужных координат по х или у - останавливается
				if(!ableToMove.Right){
					if(canSmoothY){
						if(destination.y > bounds.y){
							newDir = Direction.NORTH;
							bounds.y += velocity;
						}
						if(destination.y < bounds.y){
							newDir = Direction.SOUTH;
							bounds.y -= velocity;
						}
					}
					if(!canSmoothY){
						setState(State.STAND);
					}
				}
				if(!ableToMove.Up){
					if(canSmoothX){
						if(destination.x < bounds.x){
							newDir = Direction.WEST;
							bounds.x-=velocity;
						}
						if(destination.x > bounds.x){
							newDir = Direction.EAST;
							bounds.x+=velocity;
						}
					}
					if(!canSmoothX){
						setState(State.STAND);
					}
				}
				if(!ableToMove.Left){
					if(canSmoothY){
						if(destination.y > bounds.y){
							newDir = Direction.NORTH;
							bounds.y += velocity;
						}
						if(destination.y < bounds.y){
							newDir = Direction.SOUTH;
							bounds.y -= velocity;
						}
					}
					if(!canSmoothY){
						setState(State.STAND);
					}
				}
				if(!ableToMove.Down){
					if(canSmoothX){
						if(destination.x < bounds.x){
							newDir = Direction.WEST;
							bounds.x-=velocity;
						}
						if(destination.x > bounds.x){
							newDir = Direction.EAST;
							bounds.x+=velocity;
						}
					}
					if(!canSmoothX){
						setState(State.STAND);
					}
				}
			}
			else if(contacts > 1){
				setState(State.STAND);
				System.out.println("more collisions");
				moreCollisions = true;
			}
			
			if(curDir != newDir){
				direction = newDir;
				initIMG();
			}
	}
	
	private boolean deadlockInTheCorner(){
		if(!ableToMove.Up && !ableToMove.Right){
			if(!(destination.x < bounds.x && destination.y < bounds.y)){
				setState(State.STAND);
				return true;
			}
		}
		if(!ableToMove.Down && !ableToMove.Right){
			if(!(destination.x < bounds.x && destination.y > bounds.y)){
				setState(State.STAND);
				return true;
			}
		}
		if(!ableToMove.Up && !ableToMove.Left){
			if(!(destination.x > bounds.x && destination.y < bounds.y)){
				setState(State.STAND);
				return true;
			}
		}
		if(!ableToMove.Down && !ableToMove.Left){
			if(!(destination.x > bounds.x && destination.y > bounds.y)){
				setState(State.STAND);
				return true;
			}
		}
		return false;
	}
	
	private void checkSideOfOverlaps(){
		if(contactListen(new Rectangle(bounds).setX(bounds.x + velocity)) > 0){
			//System.out.println("right is lock");
			ableToMove.Right = false;
		}
		if(contactListen(new Rectangle(bounds).setY(bounds.y + velocity))> 0){
			//System.out.println("up is lock");
			ableToMove.Up = false;
		}
		if(contactListen(new Rectangle(bounds).setX(bounds.x - velocity))> 0){
			//System.out.println("left is lock");
			ableToMove.Left = false;
		}
		if(contactListen(new Rectangle(bounds).setY(bounds.y - velocity))> 0){
			//System.out.println("down is lock");
			ableToMove.Down = false;
		}
	}
	
	private void smoothGoX(){
		
	}
	
	private void smoothGoY(){
		
	}
	
	private void smoothGoXY(){
		
	}
}
