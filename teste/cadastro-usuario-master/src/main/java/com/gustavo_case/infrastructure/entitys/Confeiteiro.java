package com.gustavo_case.infrastructure.entitys;

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
public class Confeiteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "confeiteiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receita> receitas;
}
