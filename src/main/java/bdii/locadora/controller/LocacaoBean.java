package bdii.locadora.controller;

import bdii.locadora.model.*;
import bdii.locadora.persistence.*;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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

    private int codigoDoRecurso;
    private int codigoDoExemplar;

    public void buscarClientePorNome(){
        cliente = clienteRepository.buscaPorNome(nomeDoCliente);
    }

    public void buscarClientePorCodigo(){
        cliente = clienteRepository.buscaPorCodigo(codigoDoCliente);
    }

    public void buscarRecursoPorCodigo(){
        recurso = recursoRepository.buscaPorCodigo(codigoDoRecurso);
    }

    public void buscarExemplar(){
        ExemplarPK pk = new ExemplarPK();
        pk.setCodigoDoExemplar(codigoDoExemplar);
        pk.setCodigoDoRecurso(recurso.getCodigo());

        exemplar = exemplarRepository.buscaPorPK(pk);
    }


}
