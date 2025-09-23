package com.api.Aluno_Heitor.aluno.services;

import com.api.Aluno_Heitor.aluno.dto.AlunoDTo;
import com.api.Aluno_Heitor.aluno.models.AlunoModel;
import com.api.Aluno_Heitor.aluno.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {
    private AlunoRepository _alunoRepository;
    public AlunoService(AlunoRepository alunoRepository) {
        this._alunoRepository = alunoRepository;
    }

    public AlunoModel create(AlunoDTo dto) {
        AlunoModel aluno = new AlunoModel();
        aluno.setNome(dto.getNome());
        aluno.setCurso(dto.getCurso());
        aluno.setTelefone(dto.getTelefone());
        return _alunoRepository.save(aluno);
    }

    public List<AlunoModel> listar() {
        return _alunoRepository.findAll();
    }

    public AlunoModel atualizar(AlunoDTo dto, UUID id){
        AlunoModel existente = _alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        existente.setNome(dto.getNome());
        existente.setCurso(dto.getCurso());
        existente.setTelefone(dto.getTelefone());
        return _alunoRepository.save(existente);
    }
    public void deletar(UUID id) {
        AlunoModel existente = _alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        _alunoRepository.deleteById(existente.getId());
    }
    public List<AlunoModel> buscarPorNome(String nomeBusca) {
        return  _alunoRepository.findByNomeContainingIgnoreCase(nomeBusca);
    }
}
