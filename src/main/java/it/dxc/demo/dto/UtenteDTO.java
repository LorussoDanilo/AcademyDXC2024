package it.dxc.demo.dto;

import it.dxc.demo.entity.Indirizzo;

public class UtenteDTO {
	
	private int idUtente;
	private String nome; 
	private String cognome;
	private String mail; 
	private String telefono; 
	private Indirizzo residenza;
	
	public UtenteDTO(int idUtente, String nome, String cognome, String mail, String telefono, Indirizzo residenza) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.telefono = telefono;
		this.residenza = residenza;
	}

	public UtenteDTO() {
		super();
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Indirizzo getResidenza() {
		return residenza;
	}

	public void setResidenza(Indirizzo residenza) {
		this.residenza = residenza;
	}

	@Override
	public String toString() {
		return "UtenteDTO [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", mail=" + mail
				+ ", telefono=" + telefono + ", residenza=" + residenza + "]";
	}
	
	
	
	
	
	

}
