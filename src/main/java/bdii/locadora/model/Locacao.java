/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import bdii.locadora.utils.jpa.converters.LocalDatePersistenceConverter;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "locacao")
@NamedQueries({
        @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l")})
@Data
@AllArgsConstructor
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "loc_data")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate data;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "loc_status")
    private String status;

    @Basic(optional = false)
    @NotNull
    @Column(name = "loc_preco")
    @Setter(AccessLevel.NONE)
    private BigDecimal preco;

    @OneToOne(mappedBy = "locacao")
    private Devolucao devolucao;

    @JoinColumn(name = "FUNCIONARIO_fun_codigo", referencedColumnName = "fun_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario funcionario;

    @JoinColumn(name = "CLIENTE_cl_codigo", referencedColumnName = "cl_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locacao", fetch = FetchType.LAZY)
    private List<ItemLocacao> itensDeLocacao;

    public Locacao() {
        itensDeLocacao = new ArrayList<>();
    }

    public BigDecimal getPreco() {
        if (preco == null) {
            calculaPreco();
        }
        return preco;
    }

    public void calculaPreco() {
        preco = itensDeLocacao.stream()
                .map(item -> item.valorItem())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void adicionarItem(ItemLocacao item) {
        itensDeLocacao.add(item);
    }

    public void removeItem(ItemLocacao item) {
        itensDeLocacao.remove(item);
    }

    public void removerTodosItens() {
        itensDeLocacao.clear();
    }

    public LocalDate getDataVencimento(){
        return getItensDeLocacao().stream()
                .map(ItemLocacao::getDataDevolucao)
                .min(Comparator.<LocalDate>naturalOrder())
                .get();
    }


}
