/*
JPA significa Java Persistence API, que é uma especificação do Java para persistência de dados
em bancos de dados relacionais. A JPA define uma série de interfaces e anotações que permitem
que os desenvolvedores Java acessem e gerenciem dados de maneira transparente, sem precisar lidar
diretamente com detalhes específicos de um banco de dados.



Com o uso da JPA, os desenvolvedores podem definir classes de entidade Java que representam tabelas
no banco de dados e usar anotações para mapear os atributos das classes de entidade para colunas do
banco de dados. A JPA também fornece recursos para consultas (JPQL - Java Persistence Query Language),
gerenciamento de transações e relacionamentos entre entidades.
 */

package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.Especialidade;

/*
as anotações e imports transformam essa classe Java em entidade Java, as entidades Java
representam tabelas no banco de dados

 */
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

    /*estou aprendendo a usar o Spring na pratica, junto as outras ferramentas, não sei exatamente como
    as anotações funcionam e quais devo usar, estou seguindo o curso, minha ideia é entender o suficiente
    para poder aplicar e desenvolver, com o tempo me aprofundo em conceitos e entendo detalhes, estou
    nesse momento entendendo o que é e como fazer funcionar, usando a ferramenta em si, pra esse momento
    é suficiente
     */
