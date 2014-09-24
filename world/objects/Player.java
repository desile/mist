package com.mist.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends DynamicGameObject {

	//TODO: Продумать и организовать систему наследования объектов
	
	public Player(Vector2 position, Vector2 rec, Direction dir) {
		super(position, rec, dir);
		//TODO: Написать автомат или мэнэджер статусов
		this.state = State.STAND;
		//TODO: Сделать привязку анимации к состояниям и направлению персонажа.
		initTest();
	}
	
	public Player(float x, float y, float width, float height, Direction dir){
		super(x, y, width, height);
		this.state = State.STAND;
		this.direction = dir;
		initTest();
	}
	
	private void initTest(){
		
		int dirCoeffitient = 0;
		switch (direction) {
		case SOUTH:
			dirCoeffitient = 0;
			break;
		case WEST:
			dirCoeffitient = 1;
			break;
		case EAST:
			dirCoeffitient = 2;
			break;
		case NORTH:
			dirCoeffitient = 3;
			break;

		default:
			break;
		}
		
		tex = new Texture("test_male.png");//TODO: Создать класс-каталог/коллекцию для работы с ресурсами
		TextureRegion[] sprites = new TextureRegion[4];//Четыре кадра анимации (собираем куски из текстуры в массив регионов)
		for(int i = 0; i < sprites.length-1; i++) {
			sprites[i] = new TextureRegion(tex, i * 32, dirCoeffitient*32, 32, 32);
		}
		//Последний кадр - это второй из спрайтовой текстуры, для этого приходится записать его вне цикла.
		sprites[3] = new TextureRegion(tex, 1 * 32, dirCoeffitient*32, 32, 32);
		
		animation.setFrames(sprites, 1 / 6f);
		
		bounds.width = sprites[0].getRegionWidth();
		bounds.height = sprites[0].getRegionHeight();
		
	}

}
