package it.dxc.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="movimenti")
public class Movimento {
//	ENTITA DEBOLE RISPETTO A CONTOCORRENTE
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer idMovimento;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoMovimento tipo;
	
	@Column(nullable = false)
	private double importo;
	
	@Column(nullable = false)
	private Date dataOperazione;
	
	@ManyToOne //no cascata perchè utenti indipendenti da operazioni movimenti
	@JoinColumn(name="fk_utente",nullable = false)
	private Utente operatore;

	
	
	public Movimento() {}

	public Movimento(int idMovimento, TipoMovimento tipo, double importo, Date dataOperazione, Utente operatore) {
		super();
		this.idMovimento = idMovimento;
		this.tipo = tipo;
		this.importo = importo;
		this.dataOperazione = dataOperazione;
		this.operatore = operatore;
	}

	public Movimento(TipoMovimento tipo, double importo, Date dataOperazione, Utente operatore) {
		super();
		this.tipo = tipo;
		this.importo = importo;
		this.dataOperazione = dataOperazione;
		this.operatore = operatore;
	}

	
	
	public int getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(Integer idMovimento) {
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
		return "Movimento [idMovimento=" + idMovimento + ", tipo=" + tipo + ", importo=" + importo + ", dataOperazione="
				+ dataOperazione + ", operatore=" + operatore + "]";
	}	
	
	
}
