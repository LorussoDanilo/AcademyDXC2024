package it.dxc.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dxc.demo.dto.ContoCorrMovDTO;
import it.dxc.demo.dto.ContocorrenteDTO;
import it.dxc.demo.entity.Contocorrente;
import it.dxc.demo.entity.Movimento;
import it.dxc.demo.entity.TipoMovimento;
import it.dxc.demo.entity.Utente;
import it.dxc.demo.repository.ContocorrenteInstantRepository;
import it.dxc.demo.repository.MovimentoInstantRepository;
import it.dxc.demo.repository.UtenteInstantRepository;

@Service
@Transactional
public class ContocorrenteServiceImpl implements ContocorrenteService {

	@Autowired
	private ContocorrenteInstantRepository contocorrenteDAO;

	@Autowired
	private UtenteInstantRepository utenteDAO;

	@Autowired
	private MovimentoInstantRepository movimentoDAO;

	@Override
	public Contocorrente registraNuovoConto(Double saldo, Integer idIntestatario, Integer idCointestatario) {

		Contocorrente c=null;
		Optional<Utente> o=utenteDAO.findById(idIntestatario);
		if(o.isEmpty()) {
			throw new RuntimeException("Utente con id "+ idIntestatario+ " inesistente");
		}
		Utente u=o.get();
		
		if(idCointestatario==null) {
			c = new Contocorrente(saldo, new Date(), u);
			contocorrenteDAO.save(c);
			
		}else {
			Optional<Utente> o2=utenteDAO.findById(idCointestatario);
			Utente uc = o2.get();
			c = new Contocorrente(saldo, new Date(), u, uc);
			contocorrenteDAO.save(c);
		}
		
		modificaSaldo(c.getNumeroConto(), saldo, idIntestatario);
		return c;
	}


	@Override
	public Utente registraUtente(int idUtente,int idContocorrente) {
		Optional<Utente> o=utenteDAO.findById(idUtente);
		Optional<Contocorrente> o2=contocorrenteDAO.findById(idContocorrente);

		if(o.isEmpty()) 
			throw new RuntimeException("Utente non esistente!!");
		if(o2.isEmpty())
			throw new RuntimeException("Contocorrente non esistente!!");

		Utente u=o.get();
		Contocorrente c=o2.get();
		
		if(c.getCoIntestatario()!=null )
			throw new RuntimeException("Numero massimo di proprietari raggiunto!!");
			
		if(c.getProprietario().getIdUtente()==idUtente)
			throw new RuntimeException("proprietario gria inserito!!");
		
		c.setCoIntestatario(u);
		return u;
	}


	@Override
	public double leggiSaldoConto(int numeroConto) {
		Optional<Contocorrente> o=contocorrenteDAO.findById(numeroConto);

		if(o.isEmpty()) 
			throw new RuntimeException("Conto corrente non esistente!!");

		Contocorrente c = o.get();

		ContocorrenteDTO cDto=new ContocorrenteDTO(numeroConto,c.getSaldo());

		return cDto.getSaldo();
	}


	@Override
	public List<Movimento> leggiUltimiMovimentiConto(int numeroConto) {
		List<Movimento> movimenti = movimentoDAO.getMovimenti(numeroConto);
		if(movimenti.isEmpty()) 
			throw new RuntimeException("Non esiste nessun movimento legato a questo conto corrente!!");
		return movimenti;
	}


	@Override
	public ContoCorrMovDTO leggiUltimiMovSaldoConto(int numeroConto) {

		ContoCorrMovDTO ultimiMovimentiSaldo = new ContoCorrMovDTO(numeroConto, leggiUltimiMovimentiConto(numeroConto), leggiSaldoConto(numeroConto));
		return ultimiMovimentiSaldo;
	}


	@Override
	public boolean controlla(int idContocorrente) {
		Optional<Contocorrente> o2=contocorrenteDAO.findById(idContocorrente);
		if(o2.isEmpty())
			throw new RuntimeException("Contocorrente non esistente!!");
		Contocorrente c=o2.get();
		List<Integer> listaUtenti=movimentoDAO.findUtentiByIdcontocorrente(idContocorrente);
		
		if(listaUtenti.size()!=0) {
			Double n=contocorrenteDAO.getSaldoComplessivoMovimentiVersamenti(idContocorrente);
			Double n2=contocorrenteDAO.getSaldoComplessivoMovimentiPrelievo(idContocorrente);
			if(n==null) {
				n=(double) 0;
			}
			if(n2==null) {
				n2=(double) 0;
			}
			
			if(c.getSaldo()!=n-n2){
				throw new RuntimeException("Saldo diverso!!");
			}
		
			for(int i=0;i<listaUtenti.size();i++) {
				int id=listaUtenti.get(i);
				if(id!=c.getProprietario().getIdUtente() && id!=c.getCoIntestatario().getIdUtente()) {
					throw new RuntimeException("Movimento di un utente non proprietario!!");
				}
			}
		}
		
		if(c.getSaldo()<-5000) {
			throw new RuntimeException("Saldo troppo basso!!");
		}
		
		if(c.getProprietario()==null) {
			throw new RuntimeException("Proprietario non presente!!");
		}
		
		return true;
	}


	@Override
	public Contocorrente modificaSaldo(int numeroConto, double nuovoSaldo, int idUtenteOperatore) {
		Contocorrente conto = contocorrenteDAO.findById(numeroConto)
				.orElseThrow(() -> new RuntimeException("Contocorrente non esistente!!"));
		
		List<Movimento> listaMovimenti=movimentoDAO.findAllByFk_contocorrente(conto.getNumeroConto());
	
		utenteDAO.findById(idUtenteOperatore)
				.orElseThrow(() -> new RuntimeException("Utente non esistente!!"));
	
		controlla(numeroConto);
//		if (!(conto.getProprietario().getIdUtente() == idUtenteOperatore || 
//			  (conto.getCoIntestatario() != null && conto.getCoIntestatario().getIdUtente() == idUtenteOperatore))) {
//			throw new RuntimeException("Lâ€™utente idUtenteOperatore non Ã¨ presente a sistema oppure non Ã¨ uno dei proprietari del conto (intestatario o cointestatario)");
//		}
	
		double saldoCorrente = conto.getSaldo();
		if (nuovoSaldo == saldoCorrente && conto.getMovimenti().size()>0) {
			return conto; // Nessuna azione se il nuovo saldo Ã¨ uguale a quello corrente
		}
	
//		if (nuovoSaldo < -5000) {
//			throw new RuntimeException("Il saldo finale non puÃ² essere inferiore a -5000 euro.");
//		}
	
		double differenza;
		
		if(listaMovimenti.size()>0) {
			differenza = nuovoSaldo - saldoCorrente;
		}else {
			differenza= saldoCorrente;
		}
	
		// Aggiunta movimento principale di aggiornamento del saldo
		Movimento movimento = new Movimento();
		
		Utente u=utenteDAO.findById(idUtenteOperatore).orElse(null);
		movimento.setIdMovimento(null);
		movimento.setImporto(Math.abs(differenza));
		movimento.setTipo(differenza < 0 ? TipoMovimento.PRELIEVO : TipoMovimento.VERSAMENTO);
		movimento.setOperatore(u);
		movimento.setDataOperazione(new Date());
		conto.addMovimenti(movimento);
		movimentoDAO.save(movimento);
		
		// Se il nuovo saldo Ã¨ negativo, calcola e applica la mora
		if (nuovoSaldo < 0) {
			double mora = calcolaMora(nuovoSaldo, saldoCorrente);
			if (mora > 0) {
				nuovoSaldo -= mora; // Applica la mora al nuovo saldo
				// Registra la mora come un prelievo
				Movimento movimentoMora = new Movimento();
				movimentoMora.setImporto(mora);
				movimentoMora.setTipo(TipoMovimento.PRELIEVO);
				movimentoMora.setOperatore(u);
				movimentoMora.setDataOperazione(new Date());
				conto.addMovimenti(movimentoMora);
				movimentoDAO.save(movimentoMora);
			}
		}
	
		conto.setSaldo(nuovoSaldo);
		System.out.println(conto);
		return conto;
	}
	
	private double calcolaMora(double nuovoSaldo, double saldoCorrente) {
		double differenza = Math.abs(nuovoSaldo - saldoCorrente);
		// Calcolo la mora solo se il nuovo saldo Ã¨ minore del saldo corrente e negativo
		return nuovoSaldo < saldoCorrente && nuovoSaldo < 0 ? differenza * 0.05 : 0;
	}


	@Override
	public boolean eliminaConto(int numeroConto) {
		Contocorrente conto = contocorrenteDAO.findById(numeroConto)
				.orElseThrow(() -> new RuntimeException("Contocorrente non esistente!!"));
		
		if(conto.getSaldo()!=0) {
			throw new RuntimeException("Il saldo non Ã¨ zero!!");
		}
		
		contocorrenteDAO.deleteById(numeroConto);
		
		return true;
	}

}
