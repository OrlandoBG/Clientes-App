package io.github.OrlandoBG.io.github.OrlandoBG.clientes.rest;

import io.github.OrlandoBG.io.github.OrlandoBG.clientes.exception.DatabaseException;
import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.entity.Cliente;
import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Cliente salvar( @Valid @RequestBody Cliente cliente) {
        Cliente c;
        try {
            c = repository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Cpf já cadastrado");
        }
        return c;
    }

    @GetMapping
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(cliente ->{
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@Valid @PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        repository
            .findById(id)
            .map(cliente ->{
                   cliente.setNome(clienteAtualizado.getNome());
                   cliente.setCpf(clienteAtualizado.getCpf());
                   return repository.save(cliente);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }



}
