package com.api.Aluno_Heitor.aluno.repository;

import com.api.Aluno_Heitor.aluno.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlunoRepository extends JpaRepository<AlunoModel, UUID> {
    List<AlunoModel> findByNomeContainingIgnoreCase(String nome);

}
