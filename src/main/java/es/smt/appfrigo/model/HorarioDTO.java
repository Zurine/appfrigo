package es.smt.appfrigo.model;

import java.io.Serializable;

public class HorarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private long horaIni;
	private long horaFin;
	
	public HorarioDTO() {
		super();
	}
	public HorarioDTO(long horaIni, long horaFin) {
		super();
		this.horaIni = horaIni;
		this.horaFin = horaFin;
	}
	public long getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(long horaIni) {
		this.horaIni = horaIni;
	}
	public long getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(long horaFin) {
		this.horaFin = horaFin;
	}
	@Override
	public String toString() {
		return "HorarioDTO [horaIni=" + horaIni + ", horaFin=" + horaFin + "]";
	}
	
	
}
