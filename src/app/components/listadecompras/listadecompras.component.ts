import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { ProdutosService } from '../../services/produtos.service';
import { Item } from '../../models/item/item';

@Component({
  selector: 'app-listadecompras',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './listadecompras.component.html',
  styleUrls: ['./listadecompras.component.css']
})
export class ListadecomprasComponent implements OnInit {
  items: Item[] = [];

  constructor(private router: Router, private meuService: ProdutosService) {}

  ngOnInit(): void {
    this.carregarItens();
  }

  carregarItens(): void {
    this.meuService.getItens().subscribe(
      (data: Item[]) => {
        this.items = data;
      },
      (error) => {
        console.error('Erro ao carregar itens', error);
      }
    );
  }

  resetarLista(): void {
    this.meuService.deletarTodosItens().subscribe(
      () => {
        this.items = [];
      },
      (error) => {
        console.error('Erro ao resetar lista', error);
      }
    );
  }

  editarItem(item: Item): void {
    this.router.navigate(['/editar-item', item.id]);
  }

  removerItem(id: string): void {
    this.meuService.deletarItem(id).subscribe(
      () => {
        this.items = this.items.filter(item => item.id !== id);
      },
      (error) => {
        console.error('Erro ao remover item', error);
      }
    );
  }
}