package com.api.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "confeiteiros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfeiteiroEntity {

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
    private List<ReceitaEntity> receitas;
}
