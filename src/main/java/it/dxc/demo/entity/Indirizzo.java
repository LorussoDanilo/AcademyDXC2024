package it.dxc.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="indirizzi")
public class Indirizzo {
//	ENTITA DEBOLE RISPETTO A UTENTE
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,unique = true)
	private int idIndirizzo;
	
	@Column(nullable = false)
	private String via;
	
	@Column(nullable = false)
	private String cap;
	
	@Column(nullable = false)
	private String citta;
	
	@Column(nullable = false)
	private String provincia;
	
	
	
	public Indirizzo() {}

	public Indirizzo(int idIndirizzo, String via, String cap, String citta, String provincia) {
		this.idIndirizzo = idIndirizzo;
		this.via = via;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}

	public Indirizzo(String via, String cap, String citta, String provincia) {
		this.via = via;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}
	
	

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	
	
	
}
