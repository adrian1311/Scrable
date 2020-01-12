package edu.uclm.esi.apalabreitor.apalabreitor.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Match {
	private String id;
	private User playerA;
	private User playerB;
	private User jugadorConElTurno;
	private User ganador;

	private String lettersA;
	private String lettersB;
	
	private Board board;
	private int puntosA=0;
	private int puntosB=0;
	
	private Randomizer randomizer;
	
	
	public Match() {
		this.id = UUID.randomUUID().toString();
		this.randomizer = new Randomizer(true);  // Poner a true cuando se juegue de verdad.
	}

	public void setPlayerA(User user) {
		this.playerA = user;
	}

	public void setPlayerB(User playerB) {
		this.playerB = playerB;
	}
	
	public int getPuntosA() {
		return puntosA;
	}

	public void setPuntosA(int puntosA) {
		this.puntosA = puntosA;
	}

	public int getPuntosB() {
		return puntosB;
	}

	public void setPuntosB(int puntosB) {
		this.puntosB = puntosB;
	}

	public String getId() {
		return id;
	}
	
	public User getGanador() {
		return ganador;
	}

	public void setGanador(User ganador) {
		this.ganador = ganador;
	}
	

	public void start() {
		this.jugadorConElTurno = this.randomizer.getJugadorConElTurno(this.playerA, this.playerB);
		

		this.board = new Board(this.randomizer);
		this.lettersA=this.board.getLetters(7);
		this.lettersB=this.board.getLetters(7);
		
		try {
			JSONObject jsaA = new JSONObject();
			jsaA.put("type", "START");
			jsaA.put("letras", this.lettersA);
			jsaA.put("turno", jugadorConElTurno==playerA ? true : false);
			jsaA.put("nameA", this.playerA.getUserName());
			jsaA.put("nameB", this.playerB.getUserName());
			jsaA.put("puntosA", this.puntosA);
			jsaA.put("puntosB", this.puntosB);
			this.playerA.sendMessage(jsaA.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			
		}
		try {
			JSONObject jsaB = new JSONObject();
			jsaB.put("type", "START");
			jsaB.put("letras", this.lettersB);
			jsaB.put("turno", jugadorConElTurno==playerB ? true : false);
			jsaB.put("nameA", this.playerA.getUserName());
			jsaB.put("nameB", this.playerB.getUserName());
			jsaB.put("puntosA", this.puntosA);
			jsaB.put("puntosB", this.puntosB);
			this.playerB.sendMessage(jsaB.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			
		}
	}

	public void playerPlays(String idSession, JSONArray jsaJugada) throws Exception {
		ResultadoJugada resultado;
		User player = this.playerA.getSession().getId().equals(idSession) ? playerA : playerB;
		if (player!=this.jugadorConElTurno) {
			resultado = new ResultadoJugada();
			resultado.addException("No tienes el turno");
			player.sendMessage(resultado);
		} else {
			ArrayList<JSONObject> jugada = new ArrayList<>();
			for (int i=0; i<jsaJugada.length(); i++)
				jugada.add(jsaJugada.getJSONObject(i));
			resultado = this.board.movement(jugada);
			
			if(resultado.getExceptions().isEmpty() && resultado.invalid().isEmpty()) {
				resultado.setTurno(false);
				resultado.setJugador(player);
				player.sendMessage(resultado);
				
				User contrincante = this.playerA==player ? playerB : playerA;
				resultado.ocultarLetras();
				resultado.setCambio(false);
				resultado.setTurno(true);
				contrincante.sendMessage(resultado);
				cambiarTurno();
			}else {
				
				resultado.setTurno(true);
				player.sendMessage(resultado);
				this.board.deshacer(jugada);
			}
		}
	}
	
	
	public void rendirse(String idSession) throws Exception {
		ResultadoJugada resultado = new ResultadoJugada();
		User perdedor = this.playerA.getSession().getId().equals(idSession) ? playerA : playerB;
		resultado.setPartidaTerminada(true);
		resultado.setPerdedor(perdedor.getUserName());
		User ganador = this.playerA==perdedor ? playerB : playerA;
		this.ganador=ganador;
		resultado.setGanador(ganador.getUserName());
		ganador.sendMessage(resultado);
		perdedor.sendMessage(resultado);
	}
	
	

	private void cambiarTurno() {
		this.jugadorConElTurno = (this.playerA==this.jugadorConElTurno ? this.playerB : this.playerA);
	}


	public void pasarTurno() {
		// hay que preguntar si tiene el turno;
		cambiarTurno(); //Cambio turno y lo notifico
		ResultadoJugada resultado = new ResultadoJugada();
		resultado.setTurno(true);	
		try {
			this.jugadorConElTurno.sendMessage(resultado);
			resultado.setTurno(false);
			User otro = (this.playerA==this.jugadorConElTurno ? this.playerB : this.playerA);
			otro.sendMessage(resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		
	}

	public void cambiarLetras(String idSession) throws Exception {
		ResultadoJugada resultado;
		User player = this.playerA.getSession().getId().equals(idSession) ? playerA : playerB;
		if (player!=this.jugadorConElTurno) {
			resultado = new ResultadoJugada();
			resultado.addException("No tienes el turno");
			player.sendMessage(resultado);
		} else {
			resultado = new ResultadoJugada();
			if(this.playerA.getSession().getId().equals(idSession)) {
				for(int i=0; i<lettersA.length(); i++) {
					this.board.devolverLetter(lettersA.charAt(i));
				}
				this.lettersA=this.board.getLetters(7); //conseguir letras nuevas
				resultado.setLetrasCambiadas(this.lettersA);
				resultado.setCambio(true);
				resultado.setTurno(true);
			}else {
				for(int i=0; i<lettersB.length(); i++) {
					this.board.devolverLetter(lettersB.charAt(i));
				}
				this.lettersB=this.board.getLetters(7); //conseguir letras nuevas
				resultado.setLetrasCambiadas(this.lettersB);
				resultado.setCambio(true);
				resultado.setTurno(true);
			}
			player.sendMessage(resultado);
		}
		

		/*String l="";
		resultado.setLetrasCambiadas(l);*/
	}
	

}
