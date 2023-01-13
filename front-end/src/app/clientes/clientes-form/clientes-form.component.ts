import { Component, OnInit } from '@angular/core';
import{ Router, ActivatedRoute } from '@angular/router';

import { Cliente } from '../clientes';
import { ClientesService } from '../../clientes.service';
import { VirtualTimeScheduler } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean;
  errors: string[];
  id: number | null;

  constructor( private service: ClientesService, private router: Router, private activatedRoute: ActivatedRoute){
    this.cliente = new Cliente();
    this.success = false;
    this.errors = [];
    this.id= null;
   }

  ngOnInit():  void{
    let params =  this.activatedRoute
    .params.subscribe(urlParams =>{
      this.id = urlParams['id'];
    });

    if(this.id){
      this.service.getClienteById(this.id)
      .subscribe(response => this.cliente = response,
        errorResponse => this.cliente= new Cliente());
    }
  }

  voltarParaListagem(){
    this.router.navigate(['/clientes-lista'])
  }

  onSubmit(){
    if(this.id){
      this.service
        .atualizar(this.cliente)
          .subscribe(response =>{
            this.success = true;
            this.errors = [];
          }, errorResponse => {
            this.errors = ['Erro ao atualizar o cliente.'];
          });
    }else{
      this.service
        .salvar(this.cliente)
          .subscribe( response =>{
            this.errors =[];
            this.success=true;
            this.cliente = response;
          }, errorResponse =>{
            this.success = false;
            this.errors = errorResponse.error.errors
          });
    }
  }

}
