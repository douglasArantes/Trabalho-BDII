package bdii.locadora.persistence;

import bdii.locadora.model.Preco;
import bdii.locadora.model.Recurso;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dougla$ on 09/11/2014.
 */
public class PrecoRepository implements Serializable {

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Preco preco){
        manager.persist(preco);
    }

    @Transactional
    public void atualizar(Preco preco){
        manager.merge(preco);
    }

    @Transactional
    public void excluir(Preco preco){
        manager.remove(preco);
    }
}
