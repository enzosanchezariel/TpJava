package entities;

import java.sql.Date;

public class Room {
	int codSala;
	int amountParticipants;
	Date fechaInicioSala;
	int amountMaxParticipants;
	Date fechaFinSala;
	
	public int getCodSala() {
		return codSala;
	}
	public void setCodSala(int codSala) {
		this.codSala = codSala;
	}
	public int getAmountParticipants() {
		return amountParticipants;
	}
	public void setAmountParticipants(int amountParticipants) {
		this.amountParticipants = amountParticipants;
	}
	public Date getFechaInicioSala() {
		return fechaInicioSala;
	}
	public void setFechaInicioSala(Date fechaInicioSala) {
		this.fechaInicioSala = fechaInicioSala;
	}
	public int getAmountMaxParticipants() {
		return amountMaxParticipants;
	}
	public void setAmountMaxParticipants(int amountMaxParticipants) {
		this.amountMaxParticipants = amountMaxParticipants;
	}
	public Date getFechaFinSala() {
		return fechaFinSala;
	}
	public void setFechaFinSala(Date fechaFinSala) {
		this.fechaFinSala = fechaFinSala;
	}
	
	public Room() {}
	
	public Room(int codSala, int amountParticipants, Date fechaInicioSala, int amountMaxParticipants, Date fechaFinSala) {
		this.codSala = codSala;
		this.amountParticipants = amountParticipants;
		this.fechaInicioSala = fechaInicioSala;
		this.amountMaxParticipants = amountMaxParticipants;
		this.fechaFinSala = fechaFinSala;
	}
	
}
