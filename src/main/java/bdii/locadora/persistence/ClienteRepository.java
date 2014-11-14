package bdii.locadora.persistence;

import bdii.locadora.model.Cliente;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class ClienteRepository implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Cliente cliente){
        manager.persist(cliente);
    }

    @Transactional
    public void atualizar(Cliente cliente){
        manager.merge(cliente);
    }

    @Transactional
    public void excluir(Cliente cliente){
        manager.remove(manager.getReference(Cliente.class, cliente.getCodigo()));
    }

    public List<Cliente> todosClientes(){
        return manager.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
    }
}
