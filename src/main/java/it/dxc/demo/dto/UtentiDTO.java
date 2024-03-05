package it.dxc.demo.dto;

public class UtentiDTO {
	private int idUtente;
	private String nome;
	private String cognome;
	private String citta;
	private String provincia;
	private double saldoComplessivo;
	
	
	
	public UtentiDTO(int idUtente, String nome, String cognome, String citta, String provincia,
			double saldoComplessivo) {
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.citta = citta;
		this.provincia = provincia;
		this.saldoComplessivo = saldoComplessivo;
	}

	public UtentiDTO() {
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

	public double getSaldoComplessivo() {
		return saldoComplessivo;
	}

	public void setSaldoComplessivo(double saldoComplessivo) {
		this.saldoComplessivo = saldoComplessivo;
	}

	
	
	@Override
	public String toString() {
		return "UtentiDTO [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", citta=" + citta
				+ ", provincia=" + provincia + ", saldoComplessivo=" + saldoComplessivo + "]";
	}
	
	
}
