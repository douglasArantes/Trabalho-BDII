/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import bdii.locadora.utils.jpa.converters.LocalDatePersistenceConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//ESTA CLASSE ESTÁ OK

@Entity
@Table(name = "item_locacao")
@NamedQueries({
        @NamedQuery(name = "ItemLocacao.findAll", query = "SELECT i FROM ItemLocacao i")})
@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = "exemplar")
public class ItemLocacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "it_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "it_datadevolucao")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate dataDevolucao;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "it_statusdevolucao")
    private String status;

    @JoinColumns({
            @JoinColumn(name = "EXEMPLAR_exe_codigo", referencedColumnName = "exe_codigo"),
            @JoinColumn(name = "EXEMPLAR_RECURSO_rec_codigo", referencedColumnName = "RECURSO_rec_codigo")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Exemplar exemplar;

    @JoinColumn(name = "LOCACAO_loc_codigo", referencedColumnName = "loc_codigo")
    @ManyToOne(optional = false)
    private Locacao locacao;

    public BigDecimal valorItem() {
        return exemplar.getRecurso().getPreco().getPreco();
    }

    public ItemLocacao() {
        dataDevolucao = LocalDate.now().plusDays(14);
        status = "Em aguardo de devoluçao";
    }
}
