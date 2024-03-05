package it.dxc.demo.dto;



import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ContocorrenteDTO {
	private int numConto;
	private double saldo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimoMovimento;
	
	
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
				+ dataUltimoMovimento + "]";
	}

	
	
	
	
}
