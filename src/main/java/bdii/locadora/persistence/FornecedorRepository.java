package bdii.locadora.persistence;

import bdii.locadora.model.Cliente;
import bdii.locadora.model.Fornecedor;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class FornecedorRepository implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Fornecedor fornecedor){
        manager.persist(fornecedor);
    }

    @Transactional
    public void atualizar(Fornecedor fornecedor){
        manager.merge(fornecedor);
    }

    @Transactional
    public void excluir(Fornecedor fornecedor){
        manager.remove(fornecedor);
    }

    public List<Fornecedor> todosClientes(){
        return manager.createNamedQuery("Fornecedor.findAll", Fornecedor.class).getResultList();
    }
}
