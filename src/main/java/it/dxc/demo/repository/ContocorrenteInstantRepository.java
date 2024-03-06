package it.dxc.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.dxc.demo.entity.Contocorrente;

public interface ContocorrenteInstantRepository extends JpaRepository<Contocorrente, Integer>{
	
	@Query(nativeQuery = true,value = "SELECT c.* "
			+ "FROM contocorrenti c "
			+ "WHERE fk_utente_proprietario= :idUtente or fk_utente_co= :idUtente ")
	public List<Contocorrente> getContocorrentiBase(int idUtente);
	
	@Query(nativeQuery = true,value="SELECT SUM(c.saldo) "
			+"FROM contocorrenti c " 
			+"WHERE fk_utente_co = :idUtente OR fk_utente_proprietario = :idUtente")
	public Double getSaldoComplessivo(int idUtente);
	
	@Query(nativeQuery = true,value="SELECT SUM(m.importo)" 
			+"FROM contocorrenti c inner join movimenti m on c.numero_conto=m.fk_contocorrente "
			+"WHERE m.fk_contocorrente = :idContocorrente and m.tipo='VERSAMENTO'")
	public Double getSaldoComplessivoMovimentiVersamenti(int idContocorrente);
	
	@Query(nativeQuery = true,value="SELECT SUM(m.importo) " 
			+"FROM contocorrenti c inner join movimenti m on c.numero_conto=m.fk_contocorrente "
			+"WHERE m.fk_contocorrente = :idContocorrente and m.tipo='PRELIEVO'")
	public Double getSaldoComplessivoMovimentiPrelievo(int idContocorrente);
}
