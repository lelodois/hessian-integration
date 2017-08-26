package br.com.roicamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.roicamp.model.CampanhaUsuario;

@Repository
public interface CampanhaUsuarioBaseRepository extends JpaRepository<CampanhaUsuario, Long> {

}
