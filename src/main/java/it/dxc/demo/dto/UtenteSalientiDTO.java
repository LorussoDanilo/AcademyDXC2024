package it.dxc.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class UtenteSalientiDTO {
	
	private int idUtente;
	private String nome;
	private String cognome;
	private List<ContocorrenteDTO> contocorrenti=new ArrayList<>();
	
	

	public UtenteSalientiDTO() {
		super();
	}

	public UtenteSalientiDTO(int idUtente, String nome, String cognome, List<ContocorrenteDTO> contocorrenti) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.contocorrenti=contocorrenti;
	}

	
	
	
	
	public List<ContocorrenteDTO> getContocorrenti() {
		return contocorrenti;
	}

	public void setContocorrenti(List<ContocorrenteDTO> contocorrenti) {
		this.contocorrenti = contocorrenti;
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
	

	@Override
	public String toString() {
		return "UtenteSalientiDTO [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome
				+ ", contocorrenti=" + contocorrenti + "]";
	}

	
	
	
	
}
