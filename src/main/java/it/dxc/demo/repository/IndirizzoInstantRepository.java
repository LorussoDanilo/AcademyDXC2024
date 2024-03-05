package it.dxc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dxc.demo.entity.Indirizzo;

public interface IndirizzoInstantRepository extends JpaRepository<Indirizzo, Integer> {

}
