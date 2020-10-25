package com.example.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@RestController	
@RequestMapping("/pessoas")

public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	//@Autowired
	//private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//Listar todas pessoas
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	//Criar pessoa
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa,HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	//Buscar pessoa pelo codigo
	@GetMapping("/{codigo}")
	public Pessoa buscarPeloCodigo(@PathVariable Long codigo) {
		return this.pessoaRepository.findById(codigo).orElse(null);
		}
	
	//Deletar pessoa passando o codigo
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	//Atualizar pessoa + endereço passando o codigo
	@PutMapping("/{codigo}")
	public Pessoa atualizar (@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
		Optional<Pessoa> pessoaOptional = this.pessoaRepository.findById(codigo);
		if(pessoaOptional.isPresent()) { 
			Pessoa pessoaSalva= pessoaOptional.get();
			BeanUtils.copyProperties (pessoa,pessoaSalva, "codigo");
			this.pessoaRepository.save(pessoaSalva);
			return pessoaSalva;
		}
		else return null;
	}
	
	//Ativar ou inativar pessoa passando o codigo 
	@PutMapping("/ativo-inativo/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo) {
		Pessoa pessoa = this.pessoaRepository.findById(codigo).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		boolean ativo = pessoa.getAtivo();
		//System.out.println(ativo);
		pessoa.setAtivo(!ativo);
		pessoaRepository.save(pessoa);
	}
}
