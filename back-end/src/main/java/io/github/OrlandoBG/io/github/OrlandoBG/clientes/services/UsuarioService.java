package io.github.OrlandoBG.io.github.OrlandoBG.clientes.services;

import io.github.OrlandoBG.io.github.OrlandoBG.clientes.exception.UsuarioCadastradoException;
import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario){
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if(exists) {
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repository.save(usuario);
    }

    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username);
        if(usuario == null) {
            logger.error("Usuario não encontrado: " + username);
            throw new UsernameNotFoundException("Email não encontrado");
        }
        logger.info("Usuario encontrado: " + username);

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }


}
