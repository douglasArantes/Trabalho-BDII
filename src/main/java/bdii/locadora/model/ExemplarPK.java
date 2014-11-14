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
public class ExemplarPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "exe_codigo")
    private int codigoDoExemplar;

    @Basic(optional = false)
    @NotNull
    @Column(name = "RECURSO_rec_codigo")
    private int codigoDoRecurso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExemplarPK that = (ExemplarPK) o;

        if (codigoDoExemplar != that.codigoDoExemplar) return false;
        if (codigoDoRecurso != that.codigoDoRecurso) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoDoExemplar;
        result = 31 * result + codigoDoRecurso;
        return result;
    }
}
