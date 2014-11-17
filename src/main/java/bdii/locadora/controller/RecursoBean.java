package bdii.locadora.controller;

import bdii.locadora.model.Exemplar;
import bdii.locadora.model.Preco;
import bdii.locadora.model.Recurso;
import bdii.locadora.persistence.RecursoRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RecursoBean implements Serializable{

    private Recurso recursoAtual;
    private Preco preco;
    private Exemplar exemplar;

    @Inject
    private RecursoRepository recursoRepository;


    public Recurso getRecursoAtual() {
        return recursoAtual;
    }

    public void setRecursoAtual(Recurso recurso) {
        this.recursoAtual = recurso;
    }

    public Preco getPreco() {
        return preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }
}
