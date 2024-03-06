package it.dxc.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dxc.demo.dto.ContoCorrMovDTO;
import it.dxc.demo.dto.ContocorrenteDTO;

import it.dxc.demo.dto.MovimentoDTO;

import it.dxc.demo.dto.ContocorrenteReportDTO;
import it.dxc.demo.dto.ReportDTO;
import it.dxc.demo.dto.UtenteDTO;
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
	public ContocorrenteDTO registraNuovoConto(Double saldo, Integer idIntestatario, Integer idCointestatario) {

		Contocorrente c=null;
		Optional<Utente> o=utenteDAO.findById(idIntestatario);
		if(o.isEmpty()) {
			throw new RuntimeException("Utente con id "+ idIntestatario+ " inesistente");
		}
		Utente u=o.get();
		
		if(idCointestatario==null) {
			c = new Contocorrente(saldo, new Date(), u);
			contocorrenteDAO.save(c);
			ContocorrenteDTO cdto= new ContocorrenteDTO(c.getNumeroConto(), c.getSaldo(), new Date(), u); 
			modificaSaldo(c.getNumeroConto(), saldo, idIntestatario);
			return cdto;
		}else {
			Optional<Utente> o2=utenteDAO.findById(idCointestatario);
			Utente uc = o2.get();
			c = new Contocorrente(saldo, new Date(), u, uc);
			contocorrenteDAO.save(c);
			ContocorrenteDTO cdto= new ContocorrenteDTO(c.getNumeroConto(), c.getSaldo(), new Date(),u, uc); 
			modificaSaldo(c.getNumeroConto(), saldo, idIntestatario);
			return cdto;
		} 		
	}


	@Override
	public UtenteDTO registraUtente(Integer idUtente,Integer idContocorrente) {
		Optional<Utente> o=utenteDAO.findById(idUtente);

		Optional<Contocorrente> o2=contocorrenteDAO.findById(idContocorrente);

		if(o.isEmpty()) 

			throw new RuntimeException("Utente non esistente!!");

		if(o2.isEmpty())

			throw new RuntimeException("Contocorrente non esistente!!");
		Utente u=o.get();
		//int idUtente, String nome, String cognome, String mail, String telefono, Indirizzo residenza
		UtenteDTO udto = new UtenteDTO(u.getIdUtente(), u.getNome(), u.getCognome(), u.getMail(), u.getTelefono(), u.getResidenza());
		Contocorrente c=o2.get();


		if(c.getCoIntestatario()!=null )
			throw new RuntimeException("Numero massimo di proprietari raggiunto!!");

		if(c.getProprietario().getIdUtente()==idUtente)
			throw new RuntimeException("proprietario gria inserito!!");

		c.setCoIntestatario(u);
		return udto;
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
	public List<MovimentoDTO> leggiUltimiMovimentiConto(int numeroConto) {
		List<Movimento> movimenti = movimentoDAO.getMovimenti(numeroConto);
		List <MovimentoDTO> listaMovimentiDTO = new ArrayList <MovimentoDTO>();

		if(movimenti.isEmpty()) 
			throw new RuntimeException("Non esiste nessun movimento legato a questo conto corrente!!");
		
		for (Movimento mov : movimenti) {
			MovimentoDTO movRestituitoDTO = new MovimentoDTO(mov.getIdMovimento(), mov.getTipo(), mov.getImporto(), mov.getDataOperazione(), mov.getOperatore());
			listaMovimentiDTO.add(movRestituitoDTO);
		}	
		return listaMovimentiDTO;
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
	public ContocorrenteDTO modificaSaldo(int numeroConto, double nuovoSaldo, int idUtenteOperatore) {
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
			ContocorrenteDTO cdto = new ContocorrenteDTO(conto.getNumeroConto(), conto.getSaldo(), conto.getProprietario());
			return cdto; // Nessuna azione se il nuovo saldo Ã¨ uguale a quello corrente
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

//		movimentoDAO.save(movimento);

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
//				movimentoDAO.save(movimentoMora);
			}
		}

		conto.setSaldo(nuovoSaldo);
		ContocorrenteDTO cdto = new ContocorrenteDTO(conto.getNumeroConto(), conto.getSaldo(),new Date(), conto.getProprietario());
		return cdto;
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
	
	@Override
    public ContoCorrMovDTO sganciaCointestatario(int numeroConto, int idCointestatario) {
        //Recupero il conto corrente
        Contocorrente conto = contocorrenteDAO.findById(numeroConto)
                .orElseThrow(() -> new RuntimeException("Contocorrente non esistente"));

        //Verifico se il cointestatario ha effettuato movimenti
        List<Integer> utentiMovimenti = movimentoDAO.findUtentiByIdcontocorrente(numeroConto);
        if (utentiMovimenti.contains(idCointestatario)) {
            throw new RuntimeException("Il cointestatario ha effettuato movimenti sul conto e non può essere sganciato");
        }

        //Sgancio il cointestatario se non ha effettuato movimenti
		if (conto.getCoIntestatario() != null && conto.getCoIntestatario().getIdUtente() == idCointestatario) {
			conto.setCoIntestatario(null);
			contocorrenteDAO.save(conto);
		} else {
			throw new RuntimeException("L'utente specificato non è il cointestatario o è già stato sganciato");
		}

		//DTO con le informazioni aggiornate del conto
		ContoCorrMovDTO contoCorrMovDTO = new ContoCorrMovDTO(conto.getNumeroConto(), conto.getMovimenti(), conto.getSaldo());

    	return contoCorrMovDTO;
    }

	@Override
	public ContoCorrMovDTO leggiDatiSalientiConto(Integer numeroConto) {
		Contocorrente conto = contocorrenteDAO.findById(numeroConto)
				.orElseThrow(() -> new RuntimeException("Contocorrente non esistente!!"));
		
		ContoCorrMovDTO contoDTO=new ContoCorrMovDTO(conto.getNumeroConto(),conto.getMovimenti(),conto.getSaldo());
		
		return contoDTO;
	}


	@Override
	public ReportDTO report() {
		List<Contocorrente> conti=contocorrenteDAO.findAll();
		ReportDTO report=new ReportDTO(contocorrenteDAO.getPatrimonioBanca());
		
		for(int i=0;i<conti.size();i++) {
			ContocorrenteReportDTO contoReport=new ContocorrenteReportDTO(conti.get(i).getNumeroConto(),conti.get(i).getSaldo(),
					conti.get(i).getProprietario().getNome(),conti.get(i).getProprietario().getCognome(),movimentoDAO.sommaMovimenti(conti.get(i).getNumeroConto()));
			report.addConti(contoReport);
		}
		
		
		return report;
	}


	@Override
	public ContocorrenteDTO leggiDatiConto(int numeroConto) {
		Optional<Contocorrente> o=contocorrenteDAO.findById(numeroConto);

		if(o.isEmpty()) 
			return null;
		Contocorrente c =o.get();
		List <Integer> idMovimenti = new ArrayList<>();
		for (int i = 0; i < c.getMovimenti().size(); i++ ) {
			Integer idMov = c.getMovimenti().get(i).getIdMovimento();
			idMovimenti.add(idMov);		
		}
		if (c.getCoIntestatario()== null) {
			ContocorrenteDTO cdto = new ContocorrenteDTO(c.getNumeroConto(), c.getDataDiApertura(), c.getSaldo(), c.getProprietario().getIdUtente(), idMovimenti);
			return cdto;
		}
		else {
			ContocorrenteDTO cdto = new ContocorrenteDTO(c.getNumeroConto(), c.getDataDiApertura(), c.getSaldo(), c.getProprietario().getIdUtente(), c.getCoIntestatario().getIdUtente(), idMovimenti);
			return cdto;
		}
	}

}
