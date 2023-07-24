package med.voll.api.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*
Sem essa anotação isso é só uma classe Java, quando fazemos isso, dizemos pro Spring que é
uma classe Controller Rest, o Spring vai carregar a classe na inicialização do projeto
*/
@RequestMapping("medicos")
/* incluiremos a @RequestMapping(""), passando a URL deste controller, no caso ("medicos"),
@RequestMapping("medicos").
Assim, o Spring sabe que este arquivo é uma classe controller devido à anotação @RestController,
que está mapeando a URL /medicos. Isto é, ao chegar uma requisição para /medicos o Spring vai
detectar que deverá chamar o MedicoController.
 */


public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
/* Estamos comunicando o Spring que ao chegar uma requisição do tipo post para a URL /medicos,
ele deve chamar o método cadastrar da classe MedicoController. É isso que acabamos de mapear.
 */

    //public void cadastrar(@RequestBody String json) {
        /*@RequestBody passado como parametro serve para o Spring pegar o corpo da requisição e passar
        para a String
        a melhor forma de fazer n é armazenar na String, abaixo está a forma mais eficiente, usando
        uma classe passada como parametro
        */
       // System.out.println(json);


    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        /* sempre que quisermos enviar ou receber informação na nossa API,
        usamos o padrão DTO (data transfer object) que será uma classe ou
        record que contem somente os campos que queremos devolver ou receber
        da nossa API
         */
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
