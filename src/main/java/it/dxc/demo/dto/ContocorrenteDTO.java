package it.dxc.demo.dto;



import java.util.Date;

import it.dxc.demo.entity.Utente;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ContocorrenteDTO {
	private int numConto;
	private double saldo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimoMovimento;
	private Utente intestatario;
	private Utente cointestatario;
	
	
	
	public ContocorrenteDTO(int numConto, double saldo, Date dataUltimoMovimento, Utente intestatario,
			Utente cointestatario) {
		super();
		this.numConto = numConto;
		this.saldo = saldo;
		this.dataUltimoMovimento = dataUltimoMovimento;
		this.intestatario = intestatario;
		this.cointestatario = cointestatario;
	}
	
	public ContocorrenteDTO(int numConto, double saldo, Date dataUltimoMovimento, Utente intestatario) {
		super();
		this.numConto = numConto;
		this.saldo = saldo;
		this.dataUltimoMovimento = dataUltimoMovimento;
		this.intestatario = intestatario;
	}
	
	public ContocorrenteDTO(int numConto, double saldo, Utente intestatario) {
		super();
		this.numConto = numConto;
		this.saldo = saldo;
		this.intestatario = intestatario;
	}

	public Utente getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(Utente intestatario) {
		this.intestatario = intestatario;
	}

	public Utente getCointestatario() {
		return cointestatario;
	}

	public void setCointestatario(Utente cointestatario) {
		this.cointestatario = cointestatario;
	}

	public ContocorrenteDTO(int numConto, double saldo,Date dataUltimoMovimento) {
		super();
		this.numConto = numConto;
		this.saldo = saldo;
		this.dataUltimoMovimento = dataUltimoMovimento;
	}
	
	public ContocorrenteDTO(int numConto, double saldo) {
		super();
		this.numConto = numConto;
		this.saldo = saldo;
	}

	public ContocorrenteDTO() {
		super();
	}

	public int getNumConto() {
		return numConto;
	}

	public void setNumConto(int numConto) {
		this.numConto = numConto;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getDataUltimoMovimento() {
		return dataUltimoMovimento;
	}

	public void setDataUltimoMovimento(Date dataUltimoMovimento) {
		this.dataUltimoMovimento = dataUltimoMovimento;
	}

	@Override
	public String toString() {
		return "ContocorrenteDTO [numConto=" + numConto + ", saldo=" + saldo + ", dataUltimoMovimento="
				+ dataUltimoMovimento + ", intestatario=" + intestatario + ", cointestatario=" + cointestatario + "]";
	}

	
	
	
	
}
