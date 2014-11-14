package bdii.locadora.persistence;

import bdii.locadora.model.Cliente;
import bdii.locadora.model.Funcionario;
import bdii.locadora.utils.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dougla$ on 09/11/2014.
 */
public class FuncionarioRepository implements Serializable {

    @Inject
    private EntityManager manager;

    @Transactional
    public void salvar(Funcionario funcionario){
        manager.persist(funcionario);
    }

    @Transactional
    public void atualizar(Funcionario funcionario){
        manager.merge(funcionario);
    }

    @Transactional
    public void excluir(Funcionario funcionario){
        manager.remove(funcionario);
    }


    public Funcionario buscaPorID(int id){
        return manager.find(Funcionario.class, id);
    }

    public List<Funcionario> todosFuncionarios(){
        return manager.createNamedQuery("Funcionario.findAll", Funcionario.class).getResultList();
    }
}
