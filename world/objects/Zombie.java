package com.mist.world.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject.State;

public class Zombie extends NPC {

	protected Vector2 destination = new Vector2(0, 0);
	Rectangle nextPosition = new Rectangle();
	
	public Rectangle detectionZone = new Rectangle();
	
	final int detectionRadius = 100;
	
	protected boolean moreCollisions = false;
	
	private World currentWorld;
	
	public Zombie(float x, float y, float width, float height, Direction dir,
			String textureName) {
		super(x, y, width, height, dir, textureName);
		state = State.STAND;
		interaction = false;
		velocity = 1.2f;
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
	
	private boolean detectPlayer(Player player){
		if(Intersector.overlaps(detectionZone, player.bounds)){
			player.bounds.getPosition(destination);
			if(state!=State.WALKING)setState(State.WALKING);
			return true;
		}
		else{
			setState(State.STAND);
			return false;
		}
	}
	
	private boolean alwaysDetectPlayer(Player player){
		player.bounds.getPosition(destination);
		state = State.WALKING;
		return true;
	}
	
	public void update(float dt,Player player, World world){
		currentWorld = world;
		updateDetectionZone();
		/*if(alwaysDetectPlayer(player)){
			System.out.println("i see you");
			walking();
		}*/
		if(detectPlayer(player)){
			walking();
		}
	}
	
	private void walking(){
		Direction curDir = direction;
		Direction newDir = curDir;
		
		//когда персонаж входит в тупик - есть один путь обратно
		//все из-за того, что если ему дать возможость пройти в другом направлении,
		//то он как, мать его, коперфильд пройдет сквозь стены!
		//ДВА ДНЯ ПЫТАЛСЯ ПОФИКСИТЬ - НО НИХ*Я!
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
	
	private void updateDetectionZone(){
		detectionZone.x = bounds.x - detectionRadius;
		detectionZone.y = bounds.y - detectionRadius;
		//Теорема Пифагора
		//detectionZone.height = (float) (bounds.height + Math.sqrt(Math.pow(detectionRadius, 2)*2));
		//detectionZone.width = (float) (bounds.width + Math.sqrt(Math.pow(detectionRadius, 2)*2));
		detectionZone.height = bounds.height + detectionRadius*2;
		detectionZone.width = bounds.width + detectionRadius*2;
	}
	
}
