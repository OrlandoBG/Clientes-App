package io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.repository;

import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario,Integer>{

    Usuario findByUsername(String username);

    boolean existsByUsername(String username);
}
