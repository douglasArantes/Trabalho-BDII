package bdii.locadora.controller;

import bdii.locadora.model.Cliente;
import bdii.locadora.model.Funcionario;
import bdii.locadora.persistence.ClienteRepository;
import bdii.locadora.persistence.FuncionarioRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class ClienteBean implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private ClienteRepository clienteRepository;
    @Inject
    private FuncionarioRepository funcionarioRepository;

    private Cliente clienteAtual;
    private List<Cliente> clientes;

    public ClienteBean(){
        this.clienteAtual = new Cliente();
        clientes = Collections.emptyList();
    }

    public String salvar(){
        Funcionario func = funcionarioRepository.buscaPorID(3); //Provisório, depois pegar @Funcionario logado
        clienteAtual.setFuncionario(func);
        clienteRepository.salvar(this.clienteAtual);
        return "branco.xhtml?faces-redirect=true";
    }
    public void atualizar(){
        clienteRepository.atualizar(clienteAtual);
    }

    public List<Cliente> getClientes(){
        return clientes = clienteRepository.todosClientes();
    }

    public void excluir(Cliente cliente){
        clienteRepository.excluir(cliente);
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

}
