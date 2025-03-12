import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProdutosService } from '../services/produtos.service';
import { Item } from '../models/item/item';

@Component({
  selector: 'app-editar-item',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './editar-item.component.html',
  styleUrls: ['./editar-item.component.css']
})
export class EditarItemComponent implements OnInit {
  item: Item = { id: '', productName: '', productObservation: '', productValue: 25 };



  // teste
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private produtosService: ProdutosService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.produtosService.getItemById(id).subscribe(
        (data: Item) => {
          this.item = data;
        },
        (error) => {
          console.error('Erro ao carregar item', error);
        }
      );
    }
  }

  atualizarItem(): void {
    this.produtosService.atualizarItem(this.item.id, this.item).subscribe(
      () => {
        this.router.navigate(['/listagem']);
      },
      (error) => {
        console.error('Erro ao atualizar item', error);
      }
    );
  }

  voltar(): void {
    this.router.navigate(['/listagem']);
  }
}