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
import it.dxc.demo.dto.ContocorrenteDTO;
import it.dxc.demo.dto.UtenteDTO;
import it.dxc.demo.service.ContocorrenteService;

@RestController
@RequestMapping("/conto")
public class ContocorrenteController {
	@Autowired
	private ContocorrenteService serviceC;

	@PostMapping (path ="/registra")
	public ContocorrenteDTO registraNuovoConto (@RequestParam Double saldo,@RequestParam Integer idIntestatario,@RequestParam Integer idCointestatario) {
		return serviceC.registraNuovoConto(saldo, idIntestatario, idCointestatario);
	}

	//A che serve?
	@PutMapping(path="/addProprietario",produces = "application/json")
	public UtenteDTO registraUtente(@RequestParam Integer idUtente, @RequestParam Integer idContocorrente) {
		return serviceC.registraUtente(idUtente, idContocorrente);
	}
	

	//Leggo i dati dei movimenti del mese corrente

	@GetMapping(path="/ultimiMovSaldo/{numeroConto}",consumes = "application/json")
	public ContoCorrMovDTO getUltimiMovimentiSaldo(@PathVariable Integer numeroConto){
		return serviceC.leggiUltimiMovSaldoConto(numeroConto);
	}
	
	@GetMapping(path="/valido/{idContocorrente}")
	public boolean controllaValido(@PathVariable Integer idContocorrente) {
		return serviceC.controlla(idContocorrente);
	}
	

	@PostMapping(path="/addMovimento")
	public ContocorrenteDTO effettuaMovimento(@RequestParam Integer numeroConto,@RequestParam Integer idOperatore,@RequestParam Double nuovoSaldo) {
		return serviceC.modificaSaldo(numeroConto,nuovoSaldo,idOperatore);
	}
	
	@DeleteMapping(path="/del{numeroConto}")
	public boolean rimuoviConto(@PathVariable Integer numeroConto) {
		return serviceC.eliminaConto(numeroConto);
	}



}
