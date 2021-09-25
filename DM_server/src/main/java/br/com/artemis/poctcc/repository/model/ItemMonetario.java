package br.com.artemis.poctcc.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMonetario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(length = 200000)
    private String image;
    private Double valor;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usuario usuario;
}
