package it.dxc.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dxc.demo.dto.ContoCorrMovDTO;
import it.dxc.demo.entity.Contocorrente;
import it.dxc.demo.entity.Utente;
import it.dxc.demo.service.ContocorrenteService;

@RestController
@RequestMapping("/conto")
public class ContocorrenteController {
	@Autowired
	private ContocorrenteService serviceC;

	@PostMapping (path ="/conto/registra")
	public Contocorrente registraNuovoConto (@RequestParam double saldo, Integer idIntestatario, Integer idCointestatario) {
		return serviceC.registraNuovoConto(saldo, idIntestatario, idCointestatario);
	}

	@PostMapping(path="/addProprietario")
	public Utente registraUtente(@RequestParam Integer idCointestatario,@RequestParam Integer idContocorrente) {
		return serviceC.registraUtente(idCointestatario, idContocorrente);
	}
	

	//Leggo i dati dei movimenti del mese corrente
	@GetMapping(path="/ultimiMovSaldo")
	public ContoCorrMovDTO getUltimiMovimentiSaldo(@PathVariable Integer numeroConto){
		return serviceC.leggiUltimiMovSaldoConto(numeroConto);
	}
	
	@GetMapping(path="/valido")
	public boolean controllaValido(@RequestParam Integer idContocorrente) {
		return serviceC.controlla(idContocorrente);
	}
	
	@PutMapping(path="/addMovimento")
	public Contocorrente effettuaMovimento(@RequestParam Integer numeroConto,@RequestParam Integer idOperatore,@RequestParam Double nuovoSaldo) {
		return serviceC.modificaSaldo(numeroConto,nuovoSaldo,idOperatore);
	}
	
	@DeleteMapping(path="/del")
	public boolean rimuoviConto(@RequestParam Integer numeroConto) {
		return serviceC.eliminaConto(numeroConto);
	}



}
