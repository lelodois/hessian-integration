package br.com.roicamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.roicamp.model.Campanha;

@Repository
public interface CampanhaBaseRepository extends JpaRepository<Campanha, Long> {

}
