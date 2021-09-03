package com.mhsb.tarefas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mhsb.tarefas.model.Tarefa;
import com.mhsb.tarefas.repository.TarefaRepository;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin("http://localhost:4200")
public class TarefaController {

	@Autowired
	private TarefaRepository repository;
	
	@PostMapping
	public Tarefa save(@RequestBody Tarefa tarefa) {
		return repository.save(tarefa);
	}
	
	@GetMapping("/{id}")
	public Tarefa getById(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Tarefa> findAll() {
		return repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PatchMapping("/{id}/concluida")
	public Tarefa concluirTarefa(@PathVariable Long id) {
		return repository.findById(id).map(tarefa -> {
			tarefa.setConcluida(true);
			tarefa.setDataConclusao(LocalDateTime.now());
			repository.save(tarefa);
			return tarefa;
		}).orElse(null);
	}
}
