package com.mhsb.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mhsb.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
