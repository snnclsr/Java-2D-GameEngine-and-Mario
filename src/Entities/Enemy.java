package Entities;

import enums.ObjectID;

public abstract class Enemy extends GameObject{

	public Enemy(float x, float y, ObjectID id) {
		super(x, y, id);
	}
}
