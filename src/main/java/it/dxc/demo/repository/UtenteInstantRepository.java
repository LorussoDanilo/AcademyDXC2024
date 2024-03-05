package it.dxc.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.dxc.demo.entity.Utente;

public interface UtenteInstantRepository extends JpaRepository<Utente, Integer>{
	@Query(nativeQuery = true,value="SELECT count(*) FROM utenti WHERE mail= :mail ")
	public int controlByMail(String mail);

	
	@Query(nativeQuery = true, value = "SELECT * FROM utenti WHERE nome= :nome")
	public List<Utente> getListaUtentiByName(String nome);
}
