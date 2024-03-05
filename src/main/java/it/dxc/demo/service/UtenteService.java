package it.dxc.demo.service;

import java.util.List;

import it.dxc.demo.dto.UtenteDTO;
import it.dxc.demo.dto.UtenteSalientiDTO;
import it.dxc.demo.dto.UtentiDTO;
import it.dxc.demo.entity.Indirizzo;
import it.dxc.demo.entity.Utente;

public interface UtenteService {
	public UtenteDTO creaUtente(Utente utente, Indirizzo indirizzo);
	
	public UtenteDTO modificaUtente(Utente utente);
	
	public UtenteDTO letturaDatiBase(int idUtente);
	
	public UtenteSalientiDTO letturaDatiSalienti(int idUtente);
	
	public List<UtentiDTO> reportUtenti();
	
	public boolean cancellazioneUtente(int idUtente);

	public List<UtenteDTO> getListaUtentiByName(String nome);

}
