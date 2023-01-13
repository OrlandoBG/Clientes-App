import { Component, OnInit } from '@angular/core';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../clientes';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent {

  clientes: Cliente[];
  clienteSelecionado: Cliente;
  mensagemSucesso: string;
  mensagemErro: string;

  constructor(private service: ClientesService, private router: Router){
    this.clientes = [];
    this.clienteSelecionado =  new Cliente()
    this.mensagemSucesso = "";
    this.mensagemErro = "";
  }

  ngOnInit(): void{
    this.service.getClientes()
    .subscribe(resposta => this.clientes = resposta);
  }

  novoCadastro(){
    this.router.navigate(['/clientes-form']);
  }

  preparaDelecao(cliente: Cliente){
    this.clienteSelecionado = cliente;
  }

  deletarCliente(){
    this.service
      .deletar(this.clienteSelecionado)
      .subscribe(response =>{
        this.mensagemSucesso = "cliente deletado com sucesso!";
        this.ngOnInit();
      },erro =>{
        this.mensagemErro = "Ocorreu um erro ao deletar o cliente";
      });
  }

}
