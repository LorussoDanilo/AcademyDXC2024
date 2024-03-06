package it.dxc.demo.dto;

import java.util.Date;

import it.dxc.demo.entity.TipoMovimento;
import it.dxc.demo.entity.Utente;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class MovimentoDTO {
	
	private int idMovimento;
	@Enumerated(EnumType.STRING)
	private TipoMovimento tipo;
	private double importo;
	private Date dataOperazione;
	private Utente operatore;
	
	public MovimentoDTO() {
		super();
	}

	public MovimentoDTO(int idMovimento, TipoMovimento tipo, double importo, Date dataOperazione, Utente operatore) {
		super();
		this.idMovimento = idMovimento;
		this.tipo = tipo;
		this.importo = importo;
		this.dataOperazione = dataOperazione;
		this.operatore = operatore;
	}

	public int getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(int idMovimento) {
		this.idMovimento = idMovimento;
	}

	

	

	public TipoMovimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimento tipo) {
		this.tipo = tipo;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public Date getDataOperazione() {
		return dataOperazione;
	}

	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}

	public Utente getOperatore() {
		return operatore;
	}

	public void setOperatore(Utente operatore) {
		this.operatore = operatore;
	}

	@Override
	public String toString() {
		return "MovimentoDTO [idMovimento=" + idMovimento + ", tipo=" + tipo + ", importo=" + importo
				+ ", dataOperazione=" + dataOperazione + ", operatore=" + operatore + "]";
	}

	
	

}
