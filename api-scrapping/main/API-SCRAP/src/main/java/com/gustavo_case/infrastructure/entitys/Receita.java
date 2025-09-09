package com.gustavo_case.infrastructure.entitys;

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

    @Column(nullable = false)
    private String url;

    @ElementCollection
    @CollectionTable(
            name = "receita_ingredientes",
            joinColumns = @JoinColumn(name = "receita_id")
    )
    @Column(name = "ingrediente")
    private List<String> ingredientes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confeiteiro_id", nullable = false)
    private Confeiteiro confeiteiro;
}
