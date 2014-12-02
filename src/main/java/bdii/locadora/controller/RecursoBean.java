package bdii.locadora.controller;

import bdii.locadora.model.Exemplar;
import bdii.locadora.model.Preco;
import bdii.locadora.model.Recurso;
import bdii.locadora.persistence.RecursoRepository;
import bdii.locadora.utils.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class RecursoBean implements Serializable {

    private Recurso recursoAtual;
    private List<Recurso> recursos;
    private int codigoRecurso;

    @Inject
    private RecursoRepository recursoRepository;

    public RecursoBean() {
        this.recursoAtual = new Recurso();
        this.recursos = Collections.emptyList();
    }

    public String salvar() {
        recursoRepository.salvar(recursoAtual);
        recursos = null;
        return "recursos.xhtml?faces-redirect=true";
    }

    public void atualizar() {
        recursoRepository.atualizar(recursoAtual);
        recursos = null;
        FacesUtil.addInfoMessage("Recurso atualizado com sucesso!");

    }

    public void excluir() {
        recursoRepository.excluir(recursoAtual);
        recursos = null;
        FacesUtil.addInfoMessage("Recurso excluído com sucesso!");
    }

    public void buscaPorCodigo() {
        recursoAtual = recursoRepository.buscaPorCodigo(codigoRecurso);
    }

    public List<Recurso> getRecursos() {
        if (recursos == null || recursos.isEmpty()) {
            return recursos = recursoRepository.todosRecursos();
        } else {
            return recursos;
        }
    }

    public void setDataAtualParaPreco() {
        recursoAtual.getPreco().setData(LocalDate.now());
    }

    public Recurso getRecursoAtual() {
        return recursoAtual;
    }

    public void setRecursoAtual(Recurso recurso) {
        this.recursoAtual = recurso;
    }

    public int getCodigoRecurso() {
        return codigoRecurso;
    }

    public void setCodigoRecurso(int codigoRecurso) {
        this.codigoRecurso = codigoRecurso;
    }
}
