package com.api.Aluno_Heitor.aluno.controller;

import com.api.Aluno_Heitor.aluno.dto.AlunoDTo;
import com.api.Aluno_Heitor.aluno.models.AlunoModel;
import com.api.Aluno_Heitor.aluno.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService _alunoService;

    public AlunoController(AlunoService alunoService) {
        this._alunoService = alunoService;
    }

    @GetMapping("/listar")
    public List<AlunoModel> listar() {
        return _alunoService.listar();
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(
            @RequestBody @Valid AlunoDTo dto) {
        AlunoModel alunoSalvo = _alunoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                alunoSalvo);
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(
            @RequestBody @Valid AlunoDTo dto,
            @PathVariable(value = "id") UUID id
    ) {
        try {
            AlunoModel alunoEditado = _alunoService.atualizar(dto, id);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(alunoEditado);
        } catch (Exception e) {
            //Retorna eror 500 com a mensagem de erro para o front
            return ResponseEntity.status(
                    HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: "+ e.getMessage());
        }
    }

    @PostMapping("/apagar/{id}")
    public ResponseEntity<String> apagar(@PathVariable UUID id) {
        try {
            _alunoService.deletar(id);
            return ResponseEntity.ok(
                    "Aluno apagado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    "Deu ruim: " +e.getMessage()
            );
        }
    }

    @GetMapping("/buscar")
    public List<AlunoModel> buscar(
            @RequestParam String nomeBusca
    ){
        return _alunoService.buscarPorNome(nomeBusca);
    }




}
