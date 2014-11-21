package bdii.locadora.persistence;

import bdii.locadora.model.Exemplar;
import bdii.locadora.model.Recurso;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dougla$ on 09/11/2014.
 */
public class ExemplarRepository implements Serializable {

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Exemplar exemplar){
        manager.persist(exemplar);
    }

    @Transactional
    public void atualizar(Exemplar exemplar){
        manager.merge(exemplar);
    }

    @Transactional
    public void excluir(Exemplar exemplar){
        manager.remove(manager.getReference(Exemplar.class, exemplar.getExemplarPK()));
    }

    public List<Exemplar> todosRecursos(){
        return manager.createNamedQuery("Exemplar.findAll", Exemplar.class).getResultList();
    }
}
