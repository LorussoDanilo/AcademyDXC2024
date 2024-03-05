package it.dxc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import it.dxc.demo.dto.UtenteSalientiDTO;
import it.dxc.demo.dto.UtentiDTO;

import it.dxc.demo.entity.Indirizzo;
import it.dxc.demo.entity.Utente;
import it.dxc.demo.service.UtenteService;

@RestController
@RequestMapping("/utente")
public class UtenteController {
	
	@Autowired
	private UtenteService serviceU;
	
	/*
	 * RequestMapping si usa quando non mi connetto al db o per i test e quindi è generico nel caso in cui 
	 * il service che stiamo usando non ritorna una request che non rientra nei 4 tipi(GET,POST, DELETE, PUT)
	 * Per esempio può essere usato per l'invio di un pdf
	 */
	@PostMapping(path = "/new")
	public Utente creaUtente(@RequestBody Utente utente) {
		return serviceU.creaUtente(utente, utente.getResidenza());
	}
	/*Si potrebbe  anche non mettere pathVariable, poichè è indifferente, ma aiuta nello sviluppo del frontend. 
	perchè cosi posso scegliere quale utente modificare
	*/
	@PutMapping(path = "/change/{idUtente}",produces = "application/json",consumes = "application/json")
	public Utente modificaUtente(@RequestBody Utente utente, @PathVariable Integer idUtente) {
		
		return serviceU.modificaUtente(utente,idUtente);
	}
	
	@GetMapping(path = "/get",produces="application/json")
	public Utente getUtente(@PathVariable Integer idUtente) {
		
		return serviceU.letturaDatiBase(idUtente);
	}
	
	@GetMapping(path = "/getSalienti/{idUtente}",produces = "application/json")
	public UtenteSalientiDTO getUtenteSalienti(@PathVariable Integer idUtente) {
		
		return serviceU.letturaDatiSalienti(idUtente);
	}
	@GetMapping("/utentibyname")
	public List<Utente>  getListaUtentiByName(@RequestParam String nome){
		return serviceU.getListaUtentiByName(nome);
	}
	
	@DeleteMapping("/del/{idutente}")
	public boolean delUtente(@PathVariable Integer idUtente) {
		
		return serviceU.cancellazioneUtente(idUtente);
	}
	
	@GetMapping(path = "/report",produces = "application/json")
	public List<UtentiDTO> reportUtente() {
		
		return serviceU.reportUtenti();
	}
}
