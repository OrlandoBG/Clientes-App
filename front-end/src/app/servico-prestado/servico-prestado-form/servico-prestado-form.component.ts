import { Component } from '@angular/core';
import { Cliente } from 'src/app/clientes/clientes';
import { ClientesService } from 'src/app/clientes.service';
import { ServicoPrestado } from '../servico-prestado';
import { ServicoPrestadoService } from 'src/app/servico-prestado.service';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.css']
})
export class ServicoPrestadoFormComponent {

  clientes: Cliente[] = [];
  servico: ServicoPrestado = new ServicoPrestado();
  success: boolean = false;
  errors: string[] = [];

  constructor( private clienteService: ClientesService,
               private service: ServicoPrestadoService){}

  ngOnInit(): void{
    this.clienteService
    .getClientes()
    .subscribe(response => this.clientes = response);
  }

  onSubmit(){
    this.service
        .salvar(this.servico)
        .subscribe( response =>{
          this.errors =[];
          this.success=true;
          this.servico = new ServicoPrestado();
        }, errorResponse =>{
          this.success = false;
          this.errors = errorResponse.error.errors
        });
  }
}
