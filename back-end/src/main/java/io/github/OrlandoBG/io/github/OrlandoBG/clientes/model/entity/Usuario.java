package io.github.OrlandoBG.io.github.OrlandoBG.clientes.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    private String username;

    @Column(name = "senha")
    private String password;

}
