package me.pokemutt.connectFour.particles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import me.pokemutt.connectFour.math.Vector2;

public class Particle {
	/**
	 * La imagen de esta particula. Puede ser nula.
	 */
	private BufferedImage attachedImage;
	
	/**
	 * La posicion de esta particula en pantalla.
	 */
	public Vector2 position = new Vector2(0, 0);
	/**
	 * La aceleracion de esta particula.
	 */
	public Vector2 acceleration = new Vector2(0,0);
	/**
	 * La velocidad de esta particula.
	 */
	public Vector2 velocity = new Vector2(0,0);
	/**
	 * El tamaño de esta particula, puede ser diferente que la 
	 * de la imagen.
	 */
	public Vector2 size = new Vector2(0,0);
	/**
	 * La friccion de la particula.
	 */
	public Vector2 friction = new Vector2(0,0);
	
	/**
	 * El limite de velocidad de la particula.
	 */
	public float limit = 0;
	
	/**
	 * Esta es una constante para la friccion.
	 */
	private float c = 0.05f;
	/**
	 * Al igual que C, esta tambien es una constante de la friccion, que sirve
	 * para regularizar o cambiar la fuerza de la friccion.
	 */
	private float normal = 1f;
	/**
	 * Finalmente la fuerza de la friccion.
	 */
	protected float frictionMag = c*normal;
	
	/**
	 * Indica cuanto tiempo de vida tiene esta particula antes de que se destruya.
	 */
	public float lifetime;
	
	/**
	 * Indica el tiempo de vida actual de la particula.
	 */
	private float currentLifeTime;
	
	/**
	 * Constructor por default.
	 */
	public Particle(){
		
	}
	
	/**
	 * Crea una nueva particula con una determinada imagen.
	 * @param image
	 */
	public Particle(BufferedImage image){
		attachedImage = image;
		
		size.x = image.getWidth();
		size.y = image.getHeight();
	}
	
	/**
	 * Actualizamos la posicion de esta particula.
	 * @param delta
	 */
	public void update(double delta) {	
		currentLifeTime+=delta;
		
		friction = new Vector2((float) (velocity.x * delta),(float) (velocity.y * delta));
		friction.mult(-1 * frictionMag);
		
		applyForce(friction);
		
		velocity.add(acceleration);
		
		if(limit > 0)
			velocity.limit(limit);
		
		position.add(velocity);
		acceleration.mult(0);
	}
	
	/**
	 * Dibuja la particula en el contexto grafico actual.
	 * 
	 * @param g El contexto grafico actual.
	 */
	public void draw(Graphics2D g){
		if(attachedImage != null)
			g.drawImage(attachedImage, (int) position.x, (int) position.y, (int) size.x, (int) size.y, null);
	}
	
	/**
	 * Añade una fuerza a esta particula.
	 * @param v
	 */
	public void applyForce(Vector2 v) { acceleration.add(v); }
	
	/**
	 * Determina si la particula aun tiene tiempo de vida.
	 * Si no, se marca como que debe ser destruida.
	 * 
	 * @return
	 */
	public boolean isAlive(){
		return !(currentLifeTime/60 >= lifetime);
	}
}
