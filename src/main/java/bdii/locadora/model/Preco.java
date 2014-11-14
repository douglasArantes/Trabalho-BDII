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
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//ESTA CLASSE ESTÁ OK

@Entity
@Table(name = "preco")
@NamedQueries({
    @NamedQuery(name = "Preco.findAll", query = "SELECT p FROM Preco p")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "pre_preco")
    private BigDecimal preco;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pre_status")
    private String status;

    @Basic(optional = false)
    @NotNull
    @Column(name = "pre_data")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate data;

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preco", fetch = FetchType.LAZY)
    private List<Recurso> recursos;
*/
}
