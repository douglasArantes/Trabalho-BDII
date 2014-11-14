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
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "autorizado")
@NamedQueries({
        @NamedQuery(name = "Autorizado.findAll", query = "SELECT a FROM Autorizado a")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autorizado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "au_nome")
    private String nome;

    @Column(name = "au_descricao", length = 512)
    private String descricao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "au_datanasc")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate nascimento;

    @Basic(optional = false)
    @NotNull
    @Column(name = "au_status")
    private boolean status;

    @JoinColumn(name = "CLIENTE_cl_codigo", referencedColumnName = "cl_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;

}
