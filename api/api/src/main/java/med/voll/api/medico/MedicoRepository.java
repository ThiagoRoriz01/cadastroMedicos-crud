/*
Em Java, uma interface de repositório é um padrão de projeto utilizado para abstrair o acesso aos dados
de uma aplicação. Ela faz parte do padrão de arquitetura de software conhecido como "Repository Pattern"
(Padrão Repositório), que visa separar a lógica de negócios da lógica de persistência de dados.

A interface de repositório define um conjunto de métodos abstratos que representam operações básicas
de persistência, como criar, ler, atualizar e excluir (CRUD - Create, Read, Update, Delete).
Esses métodos servem como uma camada de abstração entre a aplicação e a fonte de dados, seja um
banco de dados, um serviço web ou qualquer outro mecanismo de armazenamento.
 */


package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository <Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
