import { Component, inject, OnInit } from '@angular/core';
import { AlunoModel } from '../../models/alunoModel';
import { FormsModule } from '@angular/forms';
import { AlunoService } from '../../services/aluno-service';


@Component({
  selector: 'app-aluno-component',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './aluno-component.html',
  styleUrl: './aluno-component.css'
})
export class AlunoComponent implements OnInit {

  private service = inject(AlunoService)

  alunos: AlunoModel[] =[];
  novoNome = '';
  editarItem: AlunoModel | null = null
  novoCurso = '';
  novoTelefone = '';
  erro = '';
  sucesso = ''

  loading = false

  //#region Private Methods

  ngOnInit() {
    this.carregar();
  }

  private carregar(){
    this.loading = true;
    this.service.listar()
      .subscribe({
        next: a => {
          this.alunos = a;
          this.loading = false;
        },
        error: e => {
          this.erro = e.message;
          this.loading = false;
        }
      })
  }

  adicionar(){
    this.erro='';

    if(!this.novoTelefone.trim()){
      this.erro = "Informe o Telefone"
      return;
  }

    if(!this.novoNome.trim()){
        this.erro = "Informe o nome"
        return;
    }

    if(!this.novoCurso.trim()){
      this.erro = "Informe um Curso"
      return;
    }

    const payLoad : AlunoModel={
      id: '',
      nome: this.novoNome,
      curso: this.novoCurso,
      telefone: this.novoTelefone
    }

    this.loading = true;
    this.service.adicionar(payLoad).subscribe({
      next: (p) => {
        this.sucesso  = `aluno ${p.nome} salvo com sucesso`
        this.loading = false;
        this.novoNome = '';
        this.novoCurso = '';
        this.novoTelefone = '';
        this.carregar()

          setTimeout(() => this.sucesso = '', 3000);
      },
      error: (e) => {
          this.erro = e.message || "Falha ao salvar o aluno"
          this.loading = false;
      }
    })
    

  }

  remover(id: string) {
    this.service.remover(id).subscribe({
      next: (message: string) => {
        this.sucesso = message || "aluno apagado"
        this.carregar();
        setTimeout(() => this.sucesso = '', 3000)
      },
      error: e => {
        this.erro = e.message || "Fudeu"
      }
    })
  }

  Editar(){
    if(!this.editarItem?.id){
      return;
    }
    this.loading = true;
    this.service.editar(this.editarItem.id, this.editarItem).subscribe({
      next: result =>{
        if(result){
          this.carregar();
          this.sucesso = 'aluno Atualizado com sucesso'
          setTimeout(() => this.sucesso = '', 3000);
        }
      },
      error: e =>{
        this.erro = e.message || "Falha ao editar"
      }
    });
  }



}
