import { Component, OnInit } from '@angular/core';
import { ServicoPrestadoBusca } from './ServicoPrestadoBusca';
import { ServicoPrestadoService } from 'src/app/servico-prestado.service';

@Component({
  selector: 'app-servico-prestado-lista',
  templateUrl: './servico-prestado-lista.component.html',
  styleUrls: ['./servico-prestado-lista.component.css']
})
export class ServicoPrestadoListaComponent {

  nome: string = "";
  mes: number = 0;
  meses: number[] = [1,2,3,4,5,6,7,8,9,10,11,12];
  lista: ServicoPrestadoBusca[] = [];
  message: string = "";

  constructor(private service: ServicoPrestadoService){}

  consultar(){
    this.service.buscar(this.nome, this.mes)
    .subscribe(response =>{
       this.lista = response
       if(this.lista.length <= 0){
          this.message = "Nenhum registro encontrado";
       }else{
          this.message = "";
       }
      });
  }

}
