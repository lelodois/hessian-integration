package br.com.roicamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.roicamp.model.Usuario;

@Repository
public interface UsuarioBaseRepository extends JpaRepository<Usuario, Long> {

}
