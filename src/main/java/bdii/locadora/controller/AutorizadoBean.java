package bdii.locadora.controller;

import bdii.locadora.model.Autorizado;
import bdii.locadora.model.Cliente;
import bdii.locadora.persistence.AutorizadoRepository;
import bdii.locadora.persistence.ClienteRepository;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class AutorizadoBean implements Serializable {

    private Autorizado autorizadoAtual;
    private List<Autorizado> autorizados;
    private int codigoDoCliente;
    private Cliente clienteAtual;


    @Inject
    private ClienteRepository clienteRepository;
    @Inject
    private AutorizadoRepository autorizadoRepository;

    public AutorizadoBean() {
        autorizadoAtual = new Autorizado();
        autorizados = Collections.emptyList();
    }

    public String salvar() {
        if(clienteAtual != null) {
            autorizadoAtual.setCliente(clienteAtual);
            System.out.println("Cliente do Autorizado: Nome= " + autorizadoAtual.getCliente().getNome());
            autorizadoRepository.salvar(autorizadoAtual);
            autorizados = null;
            return "autorizados.xhtml?faces-redirect=true";
        }
        else throw new RuntimeException("Precisa indicar a qual Cliente o Autorizado pertence!");
    }

    public String atualizar() {
        autorizadoRepository.atualizar(autorizadoAtual);
        autorizados = null;
        return "autorizados.xhtml?faces-redirect=true";
    }

    public void excluir() {
        autorizadoRepository.excluir(autorizadoAtual);
        autorizados = null;
    }

    public void buscaCliente(){
        clienteAtual = clienteRepository.buscaPorCodigo(codigoDoCliente);
        System.out.println(clienteAtual.getCodigo() + clienteAtual.getNome());
    }

    public Autorizado getAutorizadoAtual() {
        return autorizadoAtual;
    }

    public void setAutorizadoAtual(Autorizado autorizadoAtual) {
        this.autorizadoAtual = autorizadoAtual;
    }

    public List<Autorizado> getAutorizados() {
        if (autorizados == null || autorizados.isEmpty()) {
            return autorizados = autorizadoRepository.todosAutorizados();
        }
        return autorizados;
    }

    public int getCodigoDoCliente() {
        return codigoDoCliente;
    }

    public void setCodigoDoCliente(int codigoDoCliente) {
        this.codigoDoCliente = codigoDoCliente;
    }
}
