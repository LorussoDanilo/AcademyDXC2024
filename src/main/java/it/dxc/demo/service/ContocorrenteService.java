package it.dxc.demo.service;

import java.util.List;

import it.dxc.demo.dto.ContoCorrMovDTO;
import it.dxc.demo.dto.ContocorrenteDTO;
import it.dxc.demo.dto.MovimentoDTO;
import it.dxc.demo.dto.UtenteDTO;
import it.dxc.demo.entity.Contocorrente;
import it.dxc.demo.entity.Movimento;
import it.dxc.demo.entity.Utente;

public interface ContocorrenteService {
  
	public ContocorrenteDTO registraNuovoConto(Double saldo, Integer idIntestatario, Integer idCointestatario);
	public UtenteDTO registraUtente(Integer idUtente,Integer idContocorrente);
	public double leggiSaldoConto(int numeroConto);
	public List<MovimentoDTO> leggiUltimiMovimentiConto(int numeroConto);
	public ContoCorrMovDTO leggiUltimiMovSaldoConto(int numeroConto);
	public boolean controlla(int idContocorrente);
	public ContocorrenteDTO modificaSaldo(int numeroConto, double nuovoSaldo, int idOperatore);
	public boolean eliminaConto(int numeroConto);
	public ContocorrenteDTO leggiDatiConto (int numeroConto);
}
