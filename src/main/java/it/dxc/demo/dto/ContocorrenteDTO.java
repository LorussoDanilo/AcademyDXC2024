package it.dxc.demo.dto;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.dxc.demo.entity.Utente;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ContocorrenteDTO {
	private int numConto;
	private double saldo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimoMovimento;
	@Temporal (TemporalType.TIMESTAMP)
	private Date dataApertura;
	private Utente intestatario;
	private Utente cointestatario;
	private int idIntestatario;
	private int idCointestatario;
	private List<Integer> idMovimenti = new ArrayList<>();
	
	public ContocorrenteDTO(int numConto, Date dataApertura, double saldo, int idIntestatario, int idCointestatario, List<Integer> idMovimenti) {
		
		this.numConto = numConto;
		this.saldo = saldo;
		this.dataApertura = dataApertura;
		this.idIntestatario=idIntestatario;
		this.idCointestatario=idCointestatario;
		this.idMovimenti=idMovimenti;
	}
	
	
	public ContocorrenteDTO(int numConto, Date dataApertura, double saldo, int idIntestatario, List<Integer> idMovimenti) {
		
		this.numConto = numConto;
		this.saldo = saldo;
		this.dataApertura = dataApertura;
		this.idIntestatario=idIntestatario;
		this.idMovimenti=idMovimenti;
	}
	
	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public int getIdIntestatario() {
		return idIntestatario;
	}

	public void setIdIntestatario(int idIntestatario) {
		this.idIntestatario = idIntestatario;
	}

	public int getIdCointestatario() {
		return idCointestatario;
	}

	public void setIdCointestatario(int idCointestatario) {
		this.idCointestatario = idCointestatario;
	}

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

	
	public List<Integer> getIdMovimenti() {
		return idMovimenti;
	}


	@Override
	public String toString() {
		return "ContocorrenteDTO [numConto=" + numConto + ", saldo=" + saldo + ", dataUltimoMovimento="
				+ dataUltimoMovimento + ", dataApertura=" + dataApertura + ", intestatario=" + intestatario
				+ ", cointestatario=" + cointestatario + ", idIntestatario=" + idIntestatario + ", idCointestatario="
				+ idCointestatario + ", idMovimenti=" + idMovimenti + "]";
	}


	
	

	
	
	
	
}
