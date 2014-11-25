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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pagamento")
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pag_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pag_status")
    private String status;

    @Basic(optional = false)
    @NotNull
    @Column(name = "pag_data")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate data;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagamento", fetch = FetchType.LAZY)
    private List<FluxoCaixa> fluxosDeCaixa;

    //@JoinColumn(name = "DEVOLUCAO_dev_codigo", referencedColumnName = "dev_codigo")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Devolucao devolucao;
}
