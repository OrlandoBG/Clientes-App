package io.github.OrlandoBG.io.github.OrlandoBG.clientes.rest;

import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.entity.Usuario;
import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){

        repository.save(usuario);

    }

}
