package it.dxc.demo.dto;

import java.util.List;

import it.dxc.demo.entity.Movimento;

public class ContoCorrMovDTO {
	private int numeroConto;
	private List<Movimento> movimenti;
	private double saldo;
	
	public ContoCorrMovDTO() {
		super();
	}



	public ContoCorrMovDTO(int numeroConto, List<Movimento> movimenti, double saldo) {
		this.numeroConto = numeroConto;
		this.movimenti = movimenti;
		this.saldo = saldo;
	}
	
	
	public int getNumeroConto() {
		return numeroConto;
	}

	public void setNumeroConto(int numeroConto) {
		this.numeroConto = numeroConto;
	}
	
	public List<Movimento> getMovimenti() {
		return movimenti;
	}



	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}



	public double getSaldo() {
		return saldo;
	}



	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}



	@Override
	public String toString() {
		return "ContoCorrMovDTO [numeroConto=" + numeroConto + ", movimenti=" + movimenti + ", saldo=" + saldo + "]";
	}






	

}
