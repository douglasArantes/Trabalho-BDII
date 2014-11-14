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
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "caixa")
@NamedQueries({
    @NamedQuery(name = "Caixa.findAll", query = "SELECT c FROM Caixa c")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Caixa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cx_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "cx_data")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate data;

    @Basic(optional = false)
    @NotNull
    @Column(name = "cx_valor")
    private BigDecimal valor;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cx_status")
    private String status;

    @JoinColumn(name = "FUNCIONARIO_fun_codigo", referencedColumnName = "fun_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario funcionario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caixa", fetch = FetchType.LAZY)
    private List<FluxoCaixa> fluxosDeCaixa;

}
