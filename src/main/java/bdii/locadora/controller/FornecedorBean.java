package bdii.locadora.controller;


import bdii.locadora.model.Fornecedor;
import bdii.locadora.model.Funcionario;
import bdii.locadora.persistence.FornecedorRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FornecedorRepository fornecedorRepository;

    private Fornecedor fornecedorAtual;
    private List<Fornecedor> fornecedores;

    public FornecedorBean() {
        this.fornecedorAtual = new Fornecedor();
        this.fornecedores = Collections.emptyList();
    }

    public String salvar() {
        fornecedorRepository.salvar(fornecedorAtual);
        fornecedores = null;
        return "fornecedores.xhtml?faces-redirect=true";
    }

    public String atualizar() {
        fornecedorRepository.atualizar(fornecedorAtual);
        return "fornecedores.xhtml?faces-redirect=true";
    }

    public void excluir() {
        fornecedorRepository.excluir(fornecedorAtual);
        fornecedores = null;
    }

    public List<Fornecedor> getFornecedores() {
        if (fornecedores == null || fornecedores.isEmpty()) {
            return fornecedores = fornecedorRepository.todosFornecedores();
        } else {
            return fornecedores;
        }
    }

    public Fornecedor getFornecedorAtual() {
        return fornecedorAtual;
    }

    public void setFornecedorAtual(Fornecedor fornecedor) {
        this.fornecedorAtual = fornecedor;
    }


}
