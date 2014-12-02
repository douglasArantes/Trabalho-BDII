package bdii.locadora.persistence;

import bdii.locadora.model.Caixa;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Dougla$ on 01/12/2014.
 */
public class CaixaRepository implements Serializable {

    @Inject
    private EntityManager manager;

    @Transactional
    public void cadastrarCaixaInicial(Caixa caixa){
        manager.persist(caixa);
    }
}
