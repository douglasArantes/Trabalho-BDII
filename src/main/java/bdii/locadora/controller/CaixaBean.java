package bdii.locadora.controller;

import bdii.locadora.model.Caixa;
import bdii.locadora.model.Funcionario;
import bdii.locadora.persistence.CaixaRepository;
import bdii.locadora.persistence.FuncionarioRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CaixaBean implements Serializable {

    @Inject
    private FuncionarioRepository funcionarioRepository;
    @Inject
    private CaixaRepository caixaRepository;

    private Caixa caixa;
    private Funcionario funcionario;
    private int codigoFuncionario;

    public CaixaBean(){
        caixa = new Caixa();
    }

    public void buscaFucionarioPorCodigo(){
        funcionario = funcionarioRepository.buscaPorCodigo(codigoFuncionario);
    }

    public String cadastrarCaixaInicial(){
        caixa.setFuncionario(funcionario);
        caixaRepository.cadastrarCaixaInicial(caixa);

        return "/locacoes/locar_recurso.xhtml?faces-redirect=true";
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }
}
