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
@Table(name = "reserva")
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_codigo")
    private Integer codigo;

   // @Lob
   //@Size(max = 65535)
    @Column(name = "res_obs", length = 512)
    private String observacao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "res_datareserva")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate dataReserva;

    @JoinColumn(name = "RECURSO_rec_codigo", referencedColumnName = "rec_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recurso recurso;

    @JoinColumn(name = "CLIENTE_cl_codigo", referencedColumnName = "cl_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;


}
