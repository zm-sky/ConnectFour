package me.pokemutt.connectFour.network;

import me.zmsky.network.Connection;
import me.zmsky.network.GameServer;

public class C4Server extends GameServer{

	/**
	 * Crea un nuevo servidor de Connect4.
	 * 
	 * @param IP La IP donde sera hosteado el servidor.
	 * @param Port El puerto donde sera hosteado el servidor.
	 */
	public C4Server(String IP, int Port) {
		super(IP, Port);
	}

	@Override
	public void Connected(Connection c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Disconnected(Connection c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Received(Connection c, Object o) {
		// TODO Auto-generated method stub
		
	}

	
}
