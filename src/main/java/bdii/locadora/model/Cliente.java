/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import bdii.locadora.utils.jpa.converters.LocalDatePersistenceConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//ESTA CLASSE ESTÁ OK

@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
@Data
public class Cliente implements Serializable {

    private static final long serialVersionUID = 3152248670694285690L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_nome")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_endereco")
    private String endereco;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_telefone")
    private String telefone;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_estadocivil")
    private String estadoCivil;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_rg")
    private String rg;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_cpf")
    private String cpf;

    @Basic(optional = false)
    @NotNull(message = "--------NÃO PODE SER NULO--------")
    @Column(name = "cl_nascimento")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate nascimento;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_mae")
    private String mae;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_pai")
    private String pai;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_email")
    private String email;

    @Basic(optional = false)
    @NotNull
    @Column(name = "cl_sexo")
    private Character sexo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "cl_datacadastro")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate dataCadastro;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cl_status")
    private String status;

    @Column(name = "cl_obs", length = 512)
    private String observacoes;

    public Cliente(){
        this.dataCadastro = LocalDate.now();
        this.nascimento = dataCadastro.minusYears(20); //mudar depois
    }

    @JoinColumn(name = "FUNCIONARIO_fun_codigo", referencedColumnName = "fun_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcionario funcionario;

    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Autorizado> autorizados;
*/

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Locacao> locacoes;
*/




/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Reserva> reservas;
*/
}
