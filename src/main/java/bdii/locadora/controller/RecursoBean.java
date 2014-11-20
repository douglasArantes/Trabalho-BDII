package bdii.locadora.controller;

import bdii.locadora.model.Exemplar;
import bdii.locadora.model.Preco;
import bdii.locadora.model.Recurso;
import bdii.locadora.persistence.RecursoRepository;

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

    public String atualizar() {
        recursoRepository.atualizar(recursoAtual);
        return "recursos.xhtml?faces-redirect=true";
    }

    public void excluir() {
        recursoRepository.excluir(recursoAtual);
        recursos = null;
    }

    public List<Recurso> getRecursos() {
        if (recursos == null || recursos.isEmpty()) {
            return recursos = recursoRepository.todosRecursos();
        } else {
            return recursos;
        }
    }

    public void setDataAtualParaPreco(){
        recursoAtual.getPreco().setData(LocalDate.now());
    }

    public Recurso getRecursoAtual() {
        return recursoAtual;
    }

    public void setRecursoAtual(Recurso recurso) {
        this.recursoAtual = recurso;
    }

}
