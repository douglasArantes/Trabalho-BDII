package bdii.locadora.controller;


import bdii.locadora.model.Funcionario;
import bdii.locadora.persistence.FuncionarioRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FuncionarioRepository funcionarioRepository;
    private Funcionario funcionarioAtual;

    public FuncionarioBean(){
        this.funcionarioAtual = new Funcionario();
    }

    public String salvar(){
        System.out.println(funcionarioAtual);
        funcionarioRepository.salvar(funcionarioAtual);
        return "branco.xhtml?faces-redirect=true";
    }

    public Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }

    public void setFuncionarioAtual(Funcionario funcionarioAtual) {
        this.funcionarioAtual = funcionarioAtual;
    }
}
