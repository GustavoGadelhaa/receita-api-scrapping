package com.api.infrastructure.entitys;

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
public class ReceitaEntity {

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
    private ConfeiteiroEntity confeiteiro;
}
