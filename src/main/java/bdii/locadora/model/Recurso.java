/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import bdii.locadora.utils.jpa.converters.LocalDatePersistenceConverter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "recurso")
@NamedQueries({
        @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r")})
@Data
@AllArgsConstructor
public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "rec_nome")
    private String nome;

    @Column(name = "rec_descricao", length = 512)
    private String descricao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "rec_datacadastro")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate dataCadastro;

    @Column(name = "rec_obs", length = 512)
    private String observacao;

    @JoinColumn(name = "PRECO_pre_codigo", referencedColumnName = "pre_codigo")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Preco preco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recurso", fetch = FetchType.LAZY)
    private List<Exemplar> exemplares;

    public Recurso() {
        this.dataCadastro = LocalDate.now();
        this.preco = new Preco();
    }

    public long getQuantidadeRecursosDisponiveis() {
        return getExemplares().stream()
                .filter(exemplar -> exemplar.getStatus().equalsIgnoreCase("Disponivel"))
                .count();
    }

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recurso", fetch = FetchType.LAZY)
    private List<Reserva> reservas;
*/
}
