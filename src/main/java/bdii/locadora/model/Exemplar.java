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
@Table(name = "exemplar")
@NamedQueries({
    @NamedQuery(name = "Exemplar.findAll", query = "SELECT e FROM Exemplar e")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exemplar implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ExemplarPK exemplarPK;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "exe_status")
    private String status;

    @Basic(optional = false)
    @NotNull
    @Column(name = "exe_data")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate data;

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exemplar", fetch = FetchType.LAZY)
    private List<ItemLocacao> itensDeLocacao;
*/
    @JoinColumn(name = "RECURSO_rec_codigo", referencedColumnName = "rec_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recurso recurso;

    @JoinColumn(name = "FORNECEDOR_forn_codigo", referencedColumnName = "forn_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fornecedor fornecedor;
}
