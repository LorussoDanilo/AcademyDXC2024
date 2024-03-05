package it.dxc.demo.service;

import java.util.List;

import it.dxc.demo.dto.ContoCorrMovDTO;
import it.dxc.demo.entity.Contocorrente;
import it.dxc.demo.entity.Movimento;
import it.dxc.demo.entity.Utente;

public interface ContocorrenteService {

	public Contocorrente registraNuovoConto(Double saldo,Integer idIntestatario, Integer idCointestatario);
	public Utente registraUtente(int idUtente,int idContocorrente);

	public double leggiSaldoConto(int numeroConto);
	public List<Movimento> leggiUltimiMovimentiConto(int numeroConto);
	public ContoCorrMovDTO leggiUltimiMovSaldoConto(int numeroConto);
	public boolean controlla(int idContocorrente);
	public Contocorrente modificaSaldo(int numeroConto, double nuovoSaldo, int idOperatore);
	public boolean eliminaConto(int numeroConto);
}
