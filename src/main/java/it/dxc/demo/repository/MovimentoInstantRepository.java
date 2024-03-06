package it.dxc.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.dxc.demo.entity.Movimento;

public interface MovimentoInstantRepository extends JpaRepository<Movimento, Integer>{
	
	@Query(nativeQuery = true, value="SELECT m.* FROM movimenti m WHERE MONTH(m.data_operazione) =  MONTH(now()) and fk_contocorrente=:numeroConto")
	public List<Movimento> getMovimenti(int numeroConto);
	
	@Query(nativeQuery = true,value="SELECT u.id_utente "
			+"FROM movimenti m inner join utenti u on m.fk_utente=u.id_utente "
			+"WHERE m.fk_contocorrente=:idContocorrente")
	public List<Integer> findUtentiByIdcontocorrente(int idContocorrente);
	
	@Query(nativeQuery = true,value="SELECT * "
			+"FROM movimenti "
			+"WHERE fk_contocorrente=:idContocorrente")
	public List<Movimento> findAllByFk_contocorrente(int idContocorrente);

	@Query(nativeQuery = true,value="SELECT COUNT(*) "
			+"FROM movimenti "
			+"WHERE fk_contocorrente=:idContocorrente")
	public Integer sommaMovimenti(int idContocorrente);
	
}
