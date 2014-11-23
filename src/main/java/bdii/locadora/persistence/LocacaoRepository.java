package bdii.locadora.persistence;

import bdii.locadora.model.Autorizado;
import bdii.locadora.model.Locacao;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class LocacaoRepository implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Locacao locacao){
        manager.persist(locacao);
    }

    public List<Locacao> todasLocacoes(){
        return manager.createNamedQuery("Locacao.findAll", Locacao.class).getResultList();
    }
}
