package com.mist.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends DynamicGameObject {

	//TODO: ��������� � ������������ ������� ������������ ��������
	
	public Player(Vector2 position, Vector2 rec, Direction dir) {
		super(position, rec, dir);
		//TODO: �������� ������� ��� �������� ��������
		this.state = State.STAND;
		//TODO: ������� �������� �������� � ���������� � ����������� ���������.
		tex = new Texture("test_male.png");//TODO: ������� �����-�������/��������� ��� ������ � ���������
		TextureRegion[] sprites = new TextureRegion[4];//������ ����� �������� (�������� ����� �� �������� � ������ ��������)
		for(int i = 0; i < sprites.length-1; i++) {
			sprites[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
		}
		//��������� ���� - ��� ������ �� ���������� ��������, ��� ����� ���������� �������� ��� ��� �����.
		sprites[3] = new TextureRegion(tex, 1 * 32, 0, 32, 32);
		
		animation.setFrames(sprites, 1 / 6f);
		
		bounds.width = sprites[0].getRegionWidth();
		bounds.height = sprites[0].getRegionHeight();
		
	}

}
