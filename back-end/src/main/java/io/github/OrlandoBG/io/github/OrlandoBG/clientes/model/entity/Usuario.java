package io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class Usuario{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true, name="email")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column(name = "senha")
    private String password;

}
