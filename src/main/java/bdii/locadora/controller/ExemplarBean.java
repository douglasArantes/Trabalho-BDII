package bdii.locadora.controller;


import bdii.locadora.model.Exemplar;
import bdii.locadora.model.ExemplarPK;
import bdii.locadora.model.Fornecedor;
import bdii.locadora.model.Recurso;
import bdii.locadora.persistence.ExemplarRepository;
import bdii.locadora.persistence.FornecedorRepository;
import bdii.locadora.persistence.RecursoRepository;
import bdii.locadora.utils.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class ExemplarBean implements Serializable {

    private Exemplar exemplarAtual;
    private Recurso recursoAtual;
    private Fornecedor fornecedorAtual;
    private List<Exemplar> exemplares;

    private String nomeDoRecurso;
    private int codigoDoRecurso;
    private int codigoDoFornecedor;

    @Inject
    private ExemplarRepository exemplarRepository;
    @Inject
    private RecursoRepository recursoRepository;
    @Inject
    private FornecedorRepository fornecedorRepository;


    public ExemplarBean() {
        this.exemplarAtual = new Exemplar();
        this.exemplares = Collections.emptyList();
    }

    public void buscaRecuroPorNome() {
        recursoAtual = recursoRepository.buscaPorNome(nomeDoRecurso);
    }

    public void buscaRecuroPorCodigo() {
        recursoAtual = recursoRepository.buscaPorCodigo(codigoDoRecurso);
    }

    public void buscarFornecedor() {
        fornecedorAtual = fornecedorRepository.buscaPorCodigo(codigoDoFornecedor);
    }

    public String salvar() {
        ExemplarPK exePK = new ExemplarPK();
        exePK.setCodigoDoRecurso(recursoAtual.getCodigo());

        exemplarAtual.setRecurso(recursoAtual);
        exemplarAtual.setFornecedor(fornecedorAtual);
        exemplarAtual.setExemplarPK(exePK);

        exemplarRepository.salvar(exemplarAtual);
        exemplares = null;

        return "exemplares.xhtml?faces-redirect=true";
    }

    public void atualizar() {
        exemplarRepository.atualizar(exemplarAtual);
        exemplares = null;
        FacesUtil.addInfoMessage("Exemplar atualizado com sucesso!");
    }

    public void excluir(){
        exemplarRepository.excluir(exemplarAtual);
        exemplares = null;
        FacesUtil.addInfoMessage("Exemplar excluído com sucesso!");
    }

    public Exemplar getExemplarAtual() {
        return exemplarAtual;
    }

    public void setExemplarAtual(Exemplar exemplarAtual) {
        this.exemplarAtual = exemplarAtual;
    }

    public Recurso getRecursoAtual() {
        return recursoAtual;
    }

    public void setRecursoAtual(Recurso recursoAtual) {
        this.recursoAtual = recursoAtual;
    }

    public Fornecedor getFornecedorAtual() {
        return fornecedorAtual;
    }

    public void setFornecedorAtual(Fornecedor fornecedorAtual) {
        this.fornecedorAtual = fornecedorAtual;
    }

    public List<Exemplar> getExemplares() {
        if (exemplares == null || exemplares.isEmpty()) {
            return exemplares = exemplarRepository.todosRecursos();
        } else {
            return exemplares;
        }
    }

    public String getNomeDoRecurso() {
        return nomeDoRecurso;
    }

    public void setNomeDoRecurso(String nome) {
        this.nomeDoRecurso = nome;
    }

    public int getCodigoDoRecurso() {
        return codigoDoRecurso;
    }

    public void setCodigoDoRecurso(int codigo) {
        this.codigoDoRecurso = codigo;
    }

    public int getCodigoDoFornecedor() {
        return codigoDoFornecedor;
    }

    public void setCodigoDoFornecedor(int codigoDoFornecedor) {
        this.codigoDoFornecedor = codigoDoFornecedor;
    }
}