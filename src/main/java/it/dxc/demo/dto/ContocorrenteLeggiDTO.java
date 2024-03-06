package it.dxc.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.dxc.demo.entity.Utente;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ContocorrenteLeggiDTO {
	private int numConto;
	private double saldo;
	@Temporal (TemporalType.TIMESTAMP)
	private Date dataApertura;
	private int idIntestatario;
	private int idCointestatario;
	private List<Integer> idMovimenti = new ArrayList<>();
	
	
	
	public ContocorrenteLeggiDTO(int numConto, double saldo, Date dataApertura, int idIntestatario,List<Integer> idMovimenti) {
		super();
		this.numConto = numConto;
		this.saldo = saldo;
		this.dataApertura = dataApertura;
		this.idIntestatario = idIntestatario;
		this.idMovimenti=idMovimenti;
	}
	
	
	public ContocorrenteLeggiDTO(int numConto, double saldo, Date dataApertura, int idIntestatario,
			int idCointestatario,List<Integer> idMovimenti) {
		super();
		this.numConto = numConto;
		this.saldo = saldo;
		this.dataApertura = dataApertura;
		this.idIntestatario = idIntestatario;
		this.idCointestatario = idCointestatario;
		this.idMovimenti=idMovimenti;
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
	public List<Integer> getIdMovimenti() {
		return idMovimenti;
	}
	public void addIdMovimenti(Integer idMovimento) {
		this.idMovimenti.add(idMovimento);
	}


	@Override
	public String toString() {
		return "ContocorrenteLeggiDTO [numConto=" + numConto + ", saldo=" + saldo + ", dataApertura=" + dataApertura
				+ ", idIntestatario=" + idIntestatario + ", idCointestatario=" + idCointestatario + ", idMovimenti="
				+ idMovimenti + "]";
	}
	
	
	
}
