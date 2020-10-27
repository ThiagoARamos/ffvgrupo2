package com.fatec.scel.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.query.*;
@Repository

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	@Query("SELECT a FROM Aluno a WHERE a.nome = nome")
	public Aluno findByName(@Param("nome") String nome);
	
	@Query("SELECT a FROM Aluno a WHERE a.ra = ra")
	public Aluno findByRa(@Param("ra") String ra);
	

}