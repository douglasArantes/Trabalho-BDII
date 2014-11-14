/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdii.locadora.model;

import bdii.locadora.utils.jpa.converters.LocalDatePersistenceConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fun_codigo")
    private Integer codigo;

    @Basic(optional = false)
    @NotNull
   // @Size(min = 1, max = 50)
    @Column(name = "fun_nome")
    private String nome;

    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "fun_endereco")
    private String endereco;

    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "fun_telefone")
    private String telefone;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fun_nascimento")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate nascimento;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fun_admiss\u00e3o")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate admissao;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fun_email")
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fun_login")
    private String login;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fun_senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<Cliente> clientes;

    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<Caixa> caixas;
*/

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<Devolucao> devolucoes;
*/

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<Locacao> locacoes;
*/

/*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<Cliente> clientes;
*/
}
