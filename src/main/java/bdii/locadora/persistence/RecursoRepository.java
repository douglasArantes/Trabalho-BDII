package bdii.locadora.persistence;

import bdii.locadora.model.Funcionario;
import bdii.locadora.model.Recurso;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dougla$ on 09/11/2014.
 */
public class RecursoRepository implements Serializable {

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Recurso recurso) {
        manager.persist(recurso);
    }

    @Transactional
    public void atualizar(Recurso recurso) {
        manager.merge(recurso);
    }

    @Transactional
    public void excluir(Recurso recurso) {
        manager.remove(manager.getReference(Recurso.class, recurso.getCodigo()));
    }

    public List<Recurso> todosRecursos() {
        return manager.createNamedQuery("Recurso.findAll", Recurso.class).getResultList();
    }

    public Recurso buscaPorNome(String nome) {
        String jpql = "FROM Recurso rec WHERE rec.nome = :nome";

        return manager.createQuery(jpql, Recurso.class).setParameter("nome", nome).getSingleResult();
    }

    public Recurso buscaPorCodigo(int codigo){
        return manager.find(Recurso.class, codigo);
    }
}
