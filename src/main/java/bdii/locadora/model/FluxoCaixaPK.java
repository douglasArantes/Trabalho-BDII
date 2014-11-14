/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FluxoCaixaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CAIXA_cx_codigo")
    private int codigoDoCaixa;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PAGAMENTO_pag_codigo")
    private int codigoDoPagamento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FluxoCaixaPK that = (FluxoCaixaPK) o;

        if (codigoDoCaixa != that.codigoDoCaixa) return false;
        if (codigoDoPagamento != that.codigoDoPagamento) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoDoCaixa;
        result = 31 * result + codigoDoPagamento;
        return result;
    }
}
