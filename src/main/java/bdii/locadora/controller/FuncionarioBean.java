package bdii.locadora.controller;


import bdii.locadora.model.Funcionario;
import bdii.locadora.persistence.FuncionarioRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FuncionarioRepository funcionarioRepository;

    private Funcionario funcionarioAtual;
    private List<Funcionario> funcionarios;

    public FuncionarioBean() {
        this.funcionarioAtual = new Funcionario();
        this.funcionarios = Collections.emptyList();
    }

    public String salvar() {
        System.out.println(funcionarioAtual);
        funcionarioRepository.salvar(funcionarioAtual);
        funcionarios = null;
        return "branco.xhtml?faces-redirect=true";
    }

    public String atualizar() {
        funcionarioRepository.atualizar(funcionarioAtual);
        return "funcionarios.xhtml?faces-redirect=true";
    }

    public void excluir(Funcionario funcionario) {
        funcionarioRepository.excluir(funcionario);
    }

    public List<Funcionario> getFuncionarios() {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return funcionarios = funcionarioRepository.todosFuncionarios();
        } else {
            return funcionarios;
        }
    }

    public Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }

    public void setFuncionarioAtual(Funcionario funcionarioAtual) {
        this.funcionarioAtual = funcionarioAtual;
    }
}
