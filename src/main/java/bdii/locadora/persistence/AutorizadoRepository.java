package bdii.locadora.persistence;

import bdii.locadora.model.Autorizado;
import bdii.locadora.model.Cliente;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class AutorizadoRepository implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Autorizado autorizado){
        manager.persist(autorizado);
    }

    @Transactional
    public void atualizar(Autorizado autorizado){
        manager.merge(autorizado);
    }

    @Transactional
    public void excluir(Autorizado autorizado){
        manager.remove(autorizado);
    }

    public List<Autorizado> todosAutorizados(){
        return manager.createNamedQuery("Autorizado.findAll", Autorizado.class).getResultList();
    }
}
