package io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.repository;

import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
