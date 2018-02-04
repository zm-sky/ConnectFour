package me.pokemutt.connectFour.math;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(Vector2 v){
		x = v.x;
		y = v.y;
	}
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}
	public void normalize(){
		float mag = magnitude();
		
		if(mag != 0){
			div(mag);
		}
	}
	public Vector2 normalized(){
		Vector2 normalized = new Vector2(x, y);
		normalized.normalize();
		
		return normalized;
	}
	public void limit(float max){
		if(magnitude() > max){
			normalize();
			mult(max);
		}
	}
	public void add(Vector2 v){
		x+= v.x;
		y+= v.y;
	}
	public void sub(Vector2 v){
		x-= v.x;
		y-= v.y;
	}
	public void mult(float n){
		x*= n;
		y*= n;
	}
	public void div(float n){
		x/= n;
		y/= n;
	}
	public float magnitude(){
		return (float) Math.sqrt(x*x + y*y);
	}
	
	/////////////////////
	//Static functions//
	///////////////////
	public static Vector2 div(Vector2 a, float n){
		Vector2 v = new Vector2(a);
		v.div(n);
		return v;
	}
	public static Vector2 add(Vector2 a, Vector2 b){
		return new Vector2(a.x + b.x, a.y + b.y);
	}
	public static Vector2 sub(Vector2 a, Vector2 b){
		return new Vector2(a.x - b.x, a.y - b.y);
	}
	public String toString(){
		return "VECTOR2 - X: " + x + " ||  Y: " + y;
	}
}
