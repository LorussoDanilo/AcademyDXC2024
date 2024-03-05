package it.dxc.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dxc.demo.dto.UtenteSalientiDTO;
import it.dxc.demo.entity.Contocorrente;
import it.dxc.demo.entity.Indirizzo;
import it.dxc.demo.entity.Utente;
import it.dxc.demo.dto.UtentiDTO;
import it.dxc.demo.dto.ContocorrenteDTO;
import it.dxc.demo.repository.ContocorrenteInstantRepository;
import it.dxc.demo.repository.UtenteInstantRepository;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteInstantRepository utenteDAO;

	@Autowired
	private ContocorrenteInstantRepository contocorrenteDAO;

	@Override
	public Utente creaUtente(Utente utente, Indirizzo indirizzo) {

		int b=utenteDAO.controlByMail(utente.getMail());

		if(b>=1) {
			throw new RuntimeException("Utente gia presente a sistema");
		}

		Utente u=new Utente(utente.getNome(),utente.getCognome(),utente.getMail(),utente.getTelefono(),indirizzo);
		utenteDAO.save(u);
		return u;
	}

	@Override
	public Utente modificaUtente(Utente utente,Integer idUtente) {
		Optional<Utente> o=utenteDAO.findById(idUtente);

		if(o.isEmpty()) 
			throw new RuntimeException("Utente non esistente!!");

		Utente u=o.get();
		u.setNome(utente.getNome());
		u.setCognome(utente.getCognome());
		u.setMail(utente.getMail());
		u.setTelefono(utente.getTelefono());
		u.setResidenza(utente.getResidenza());

		return u;
	}

	@Override
	public Utente letturaDatiBase(int idUtente) {
		Optional<Utente> o=utenteDAO.findById(idUtente);

		if(o.isEmpty()) 
			throw new RuntimeException("Utente non esistente!!");

		Utente u=o.get();
		return u;
	}

	@Override
	public UtenteSalientiDTO letturaDatiSalienti(int idUtente) {
		Optional<Utente> o=utenteDAO.findById(idUtente);

		if(o.isEmpty()) 
			throw new RuntimeException("Utente non esistente!!");

		Utente u=o.get();
		List<Contocorrente> c=contocorrenteDAO.getContocorrentiBase(idUtente);
		List<ContocorrenteDTO> c1 = new ArrayList<>();

		//		for(ContocorrenteDTO ccDTO: elements) {
		//			
		//		}

		for(int i=0;i<c.size();i++) {
			Date dataMax = new Date();
			if(c.get(i).getMovimenti().size()>0) {
				//Questo serve per ordinare la lista in modo da poter prendere l'ultimo elemento
				Collections.sort(c.get(i).getMovimenti(), (of1,of2) -> of1.getDataOperazione().compareTo(of2.getDataOperazione()));
				if(i==c.size() - 1)
					dataMax= c.get(i).getMovimenti().get(i).getDataOperazione();
			}
			c1.add(new ContocorrenteDTO(c.get(i).getNumeroConto(),c.get(i).getSaldo(),dataMax));
		}

		UtenteSalientiDTO u2=new UtenteSalientiDTO(idUtente,u.getNome(),u.getCognome(),c1);

		return u2;
	}

	@Override
	public List<UtentiDTO> reportUtenti() {
		List<Utente> utenti=utenteDAO.findAll();
		List<UtentiDTO> uDTO=new ArrayList<>();

		for(int i=0;i<utenti.size();i++) {
			int ii=utenti.get(i).getIdUtente(); //importante pervchè se no getsaldocomplessivo si riferisce alla posizione in lista

			if(contocorrenteDAO.getSaldoComplessivo(ii)!=null) {

				uDTO.add(new UtentiDTO(utenti.get(i).getIdUtente(),utenti.get(i).getNome(),utenti.get(i).getCognome(),
						utenti.get(i).getResidenza().getCitta(),utenti.get(i).getResidenza().getProvincia(),
						contocorrenteDAO.getSaldoComplessivo(ii)));
			}
		}
		return uDTO;
	}

	@Override
	public boolean cancellazioneUtente(int idUtente) {
		Optional<Utente> o=utenteDAO.findById(idUtente);

		if(o.isEmpty()) 
			throw new RuntimeException("Utente non esistente!!");

		Utente u=o.get();
		utenteDAO.delete(u);

		return true;
	}

	@Override
	public List<Utente> getListaUtentiByName(String nome) {
		return utenteDAO.getListaUtentiByName(nome);
	}


}
