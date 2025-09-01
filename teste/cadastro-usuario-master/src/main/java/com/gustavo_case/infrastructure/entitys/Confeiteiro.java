package com.gustavo_case.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "confeiteiros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Confeiteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String nacionalidade;

    @Column
    private String email;

    @Column
    private String url;

    @Column
    private String dataNascimento;

    @Column
    private String senha;

    @OneToMany(mappedBy = "confeiteiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receita> receitas;
}
