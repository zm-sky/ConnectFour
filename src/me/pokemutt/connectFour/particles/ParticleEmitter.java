package me.pokemutt.connectFour.particles;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.pokemutt.connectFour.math.Vector2;

public class ParticleEmitter {
	/**
	 * Las particulas actualmente activas.
	 */
	private List<Particle> particulas;
	
	/**
	 * El tipo de particula que se debe de emitir. Este en teoria deberia ser una
	 * lista llena de tipos para estar emitiendo diferentes particulas, pero por simplicidad
	 * solo se dara un tipo.
	 */
	private ParticleType type;
	
	/**
	 * Indica que las particulas deben de emitir cuando una
	 * salga de rango.
	 */
	public boolean succesionEmitting = true;
	
	/**
	 * Indica el tiempo transcurrido desde la ultima emision.
	 */
	private double tiempoTranscurrido = 0;
	
	/**
	 * Indica cada cuanto tiempo se debe de emitir.
	 */
	public double emitirCada = 0;
	
	/**
	 * Indica el tiempo que tienen las particulas antes de ser destruidas.
	 */
	public float tiempoVida;
	
	/**
	 * Emite particulas a determinada direccion.
	 */
	public Vector2 direccion;
	
	/**
	 * La velocidad de las particulas.
	 */
	public Vector2 velocidad;
	
	/**
	 * Laa posicion de donde empezar a emitir particulas.
	 */
	public Vector2 posicion;
	
	/**
	 * Indica el tamaño de la particula.
	 */
	public Vector2 tamaño;
	
	/**
	 * Constructor por default.
	 */
	public ParticleEmitter(ParticleType type){
		this.type = type;
		particulas = new ArrayList<>();
		direccion = new Vector2(0,0);
		posicion = new Vector2(0,0);
	}
	
	/**
	 * Creates a new particle.
	 */
	private Particle createParticle(){
		Particle particulaNueva = new Particle(type.particleImage);
		particulaNueva.position = new Vector2(posicion);
		particulaNueva.acceleration = new Vector2(direccion.x * velocidad.x, direccion.y * velocidad.y);
		particulaNueva.frictionMag = 0;
		particulaNueva.lifetime = tiempoVida;
		
		if(tamaño != null)
			particulaNueva.size = tamaño;
		
		return particulaNueva;
	}
	
	/**
	 * Actualiza todas las particulas en pantalla.
	 * @param delta El tiempo delta transcurrido entre frames.
	 * 
	 */
	public void update(double delta){
		if(succesionEmitting){
			if(particulas.size() != 0){
				Particle lastParticle = particulas.get( particulas.size()-1);
				
				if(lastParticle.position.y >= posicion.y + lastParticle.size.y - velocidad.y){
					particulas.add(createParticle());
				}
			}
			else
				particulas.add(createParticle());
		}
		else{
			/**
			 * Verificamos si tenemos que emitir una nueva particula.
			 */
			if(tiempoTranscurrido/60 >= 8){
				synchronized(particulas){
					Particle particulaNueva = new Particle(type.particleImage);
					particulaNueva.position = new Vector2(posicion);
					particulaNueva.acceleration = new Vector2(direccion);
					particulaNueva.frictionMag = 0;
					
					particulas.add(particulaNueva);
					
					tiempoTranscurrido = 0;
				}
			}
		
			tiempoTranscurrido+=delta;
		}	
		

		synchronized(particulas){
			List<Particle> deadParticles = new ArrayList<Particle>();
			
			for(Particle p : particulas){
				if(p.isAlive())
					p.update(delta);
				else{
					deadParticles.add(p);
				}
			}
			
			deadParticles.forEach(deadParticle -> particulas.remove(deadParticle));
			deadParticles.clear();
		}
	}
	
	/**
	 * Dibuja las particulas en pantalla.
	 * @param g El contexto grafico actual.
	 */
	public void draw(Graphics2D g){
		synchronized(particulas){
			for(Particle p : particulas){
				p.draw(g);
			}
		}
	}
	
	/**
	 * Emite particulas.
	 */
	public void emit(){
		tiempoTranscurrido = emitirCada;
	}
	
	/**
	 * Emite antes de que se dibujen las particulas, simulando como si ya
	 * estuviesen ahi desde el inicio.
	 * 
	 * @param cycles La cantidad de segundos que se emitiran antes de iniciar.
	 */
	public void preEmit(float cycles){
		long delta = 0;
		long lastTime = 0;
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;

		while (elapsedTime < cycles * 1000) {
		    //perform db poll/check
		    elapsedTime = (new Date()).getTime() - startTime;
		    delta+= lastTime - delta;
		    update(0.1);
		    
		    lastTime = System.currentTimeMillis();
		}
	}
}
