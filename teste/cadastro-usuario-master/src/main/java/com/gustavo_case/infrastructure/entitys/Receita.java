package com.gustavo_case.infrastructure.entitys;
import jakarta.validation.constraints.NotNull; // ou javax.validation.constraints.NotNull

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "receitas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;


    @Column(nullable = true)
    private String url;


    @Column
    private List<String>ingredientes;

    @ManyToOne
    @JoinColumn(name = "confeiteiro_id", nullable = true)
    private Confeiteiro confeiteiro;
}
