/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fluxo_caixa")
@NamedQueries({
    @NamedQuery(name = "FluxoCaixa.findAll", query = "SELECT f FROM FluxoCaixa f")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FluxoCaixa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected FluxoCaixaPK fluxoCaixaPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valorpago")
    private BigDecimal valorpago;

    @JoinColumn(name = "PAGAMENTO_pag_codigo", referencedColumnName = "pag_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pagamento pagamento;

    @JoinColumn(name = "CAIXA_cx_codigo", referencedColumnName = "cx_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caixa caixa;


}
