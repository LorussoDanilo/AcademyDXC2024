package it.dxc.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="contocorrenti")
public class Contocorrente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,unique = true)
	private int numeroConto;
	
	@Column(nullable = false)
	private double saldo;
	
	@Column(nullable = false)
	private Date dataDiApertura;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY) //orphan removale per eliminare le righe olytre che sganciare le chiave nella entity debole
	@JoinColumn(name="fk_contocorrente",nullable = false)
	List<Movimento> movimenti= new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="fk_utente_proprietario",nullable = false)
	private Utente proprietario;
	
	@ManyToOne
	@JoinColumn(name="fk_utente_co")
	private Utente coIntestatario;
	


	public Contocorrente() {}

	public Contocorrente(int numeroConto, double saldo, Date dataDiApertura, List<Movimento> movimenti,Utente proprietario) {
		this.numeroConto = numeroConto;
		this.saldo = saldo;
		this.dataDiApertura = dataDiApertura;
		this.movimenti = movimenti;
		this.proprietario = proprietario;
	}

	public Contocorrente(double saldo, Date dataDiApertura, Utente proprietario) {
		this.saldo = saldo;
		this.dataDiApertura = dataDiApertura;
		this.proprietario=proprietario;
	}
	
	public Contocorrente(double saldo, Date dataDiApertura, Utente proprietario, Utente coIntestatario) {
		super();
		this.saldo = saldo;
		this.dataDiApertura = dataDiApertura;
		this.proprietario = proprietario;
		this.coIntestatario = coIntestatario;
	}
	

	public int getNumeroConto() {
		return numeroConto;
	}

	public void setNumeroConto(int numeroConto) {
		this.numeroConto = numeroConto;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getDataDiApertura() {
		return dataDiApertura;
	}

	public void setDataDiApertura(Date dataDiApertura) {
		this.dataDiApertura = dataDiApertura;
	}

	public List<Movimento> getMovimenti() {
		return movimenti;
	}

	public Utente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Utente proprietario) {
		this.proprietario = proprietario;
	}

	public Utente getCoIntestatario() {
		return coIntestatario;
	}

	public void setCoIntestatario(Utente coIntestatario) {
		this.coIntestatario = coIntestatario;
	}

	
	public void addMovimenti(Movimento m) {
		this.movimenti.add(m);
	}

	@Override
	public String toString() {
		return "Contocorrente [numeroConto=" + numeroConto + ", saldo=" + saldo + ", dataDiApertura=" + dataDiApertura
				+ ", movimenti=" + movimenti + ", proprietario=" + proprietario + ", coIntestatario=" + coIntestatario
				+ "]";
	}
	
	
}
