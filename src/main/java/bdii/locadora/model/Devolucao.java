/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import bdii.locadora.utils.jpa.converters.LocalDatePersistenceConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "devolucao")
@NamedQueries({
    @NamedQuery(name = "Devolucao.findAll", query = "SELECT d FROM Devolucao d")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devolucao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "dev_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dev_data")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate data;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dev_valor")
    private BigDecimal valor;

    //@JoinColumn(name = "LOCACAO_loc_codigo", referencedColumnName = "loc_codigo")
    @OneToOne
    private Locacao locacao;

    @JoinColumn(name = "FUNCIONARIO_fun_codigo", referencedColumnName = "fun_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario funcionario;


    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "devolucao", fetch = FetchType.LAZY)
    @OneToOne(mappedBy = "devolucao")
    private Pagamento pagamento;

}
