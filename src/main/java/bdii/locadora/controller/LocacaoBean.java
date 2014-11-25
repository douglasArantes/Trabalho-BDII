package bdii.locadora.controller;

import bdii.locadora.model.*;
import bdii.locadora.persistence.*;
import bdii.locadora.utils.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;

@Named
@ViewScoped
public class LocacaoBean implements Serializable {

    private Locacao locacao;
    private ItemLocacao itemLocacao;
    private Cliente cliente;
    private Funcionario funcionario;
    private Recurso recurso;
    private Exemplar exemplar;

    @Inject
    private LocacaoRepository locacaoRepository;
    @Inject
    private ClienteRepository clienteRepository;
    @Inject
    private FuncionarioRepository funcionarioRepository;
    @Inject
    private RecursoRepository recursoRepository;
    @Inject
    private ExemplarRepository exemplarRepository;

    private String nomeDoCliente;
    private int codigoDoCliente;

    private int codigoDoFuncionario;

    private int codigoDoRecurso;
    private int codigoDoExemplar;

    public LocacaoBean() {
        locacao = new Locacao();
    }

    public void buscarClientePorNome() {
        cliente = clienteRepository.buscaPorNome(nomeDoCliente);
    }

    public void buscarClientePorCodigo() {
        cliente = clienteRepository.buscaPorCodigo(codigoDoCliente);
    }

    public void buscarFuncionarioPorCodigo() {
        funcionario = funcionarioRepository.buscaPorCodigo(codigoDoFuncionario);
    }

    public void buscarRecursoPorCodigo() {
        recurso = recursoRepository.buscaPorCodigo(codigoDoRecurso);
    }

    public void buscarExemplar() {
        ExemplarPK pk = new ExemplarPK();
        pk.setCodigoDoExemplar(codigoDoExemplar);
        pk.setCodigoDoRecurso(recurso.getCodigo());

        exemplar = exemplarRepository.buscaPorPK(pk);

        if("locado".equals(exemplar.getStatus())){
            FacesUtil.addErrorMessage("Exemplar já esta locado");
            exemplar = null;
        }
    }

    public void adicionarItem() {
        itemLocacao = new ItemLocacao();
        itemLocacao.setExemplar(exemplar);
        itemLocacao.setLocacao(locacao);
        locacao.adicionarItem(itemLocacao);

        exemplar.setStatus("locado");
        autualizaExemplar(exemplar);

        itemLocacao = null;
        recurso = null;
        exemplar = null;
    }

    public String finalizar() {
        locacao.setCliente(cliente);
        locacao.setData(LocalDate.now());
        locacao.setFuncionario(funcionario);
        locacao.setStatus("Teste");
        locacao.calculaPreco();

        locacaoRepository.salvar(locacao);

        return "branco.xhtml?faces-redirect=true";

    }

    private void autualizaExemplar(Exemplar exemplar){
        exemplarRepository.atualizar(exemplar);
    }

    /*
    * Apenas um teste antes de implementar a tela
    * */
/*
    public String simulaLocacao(){
        locacao = new Locacao();

        cliente = clienteRepository.buscaPorCodigo(1);
        System.out.println("Cliente Nome: " + cliente.getNome());

        funcionario = funcionarioRepository.buscaPorCodigo(1);
        System.out.println("Funcionario Nome: " + funcionario.getNome());

        recurso = recursoRepository.buscaPorCodigo(8);
        System.out.println("Recurso Nome: " + recurso.getNome());

        ExemplarPK pk = new ExemplarPK();
        pk.setCodigoDoExemplar(1);
        pk.setCodigoDoRecurso(8);
        exemplar = exemplarRepository.buscaPorPK(pk);
        System.out.println("Exemplar Codigo: " + exemplar.getExemplarPK().getCodigoDoExemplar());

        ItemLocacao item = new ItemLocacao();
        item.setExemplar(exemplar);
        item.setStatus("Teste");
        item.setDataDevolucao(LocalDate.now().plusDays(14));
        item.setLocacao(locacao);

        locacao.adicionarItem(item);
        locacao.setCliente(cliente);
        locacao.setData(LocalDate.now());
        locacao.setFuncionario(funcionario);
        locacao.setStatus("Teste");
        locacao.getPreco();

        locacaoRepository.salvar(locacao);

        return "branco.xhtml?faces-redirect=true";
    }

*/
    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public int getCodigoDoCliente() {
        return codigoDoCliente;
    }

    public void setCodigoDoCliente(int codigoDoCliente) {
        this.codigoDoCliente = codigoDoCliente;
    }

    public int getCodigoDoRecurso() {
        return codigoDoRecurso;
    }

    public void setCodigoDoRecurso(int codigoDoRecurso) {
        this.codigoDoRecurso = codigoDoRecurso;
    }

    public int getCodigoDoExemplar() {
        return codigoDoExemplar;
    }

    public void setCodigoDoExemplar(int codigoDoExemplar) {
        this.codigoDoExemplar = codigoDoExemplar;
    }

    public int getCodigoDoFuncionario() {
        return codigoDoFuncionario;
    }

    public void setCodigoDoFuncionario(int codigoDoFuncionario) {
        this.codigoDoFuncionario = codigoDoFuncionario;
    }
}
