import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ProdutosService } from '../../services/produtos.service';
import { Item } from '../../models/item/item';

@Component({
  selector: 'app-modaladicionaritem',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modaladicionaritem.component.html',
  styleUrls: ['./modaladicionaritem.component.css']
})
export class ModaladicionaritemComponent {

  productName = '';
  productObservation = '';
  productValue = '25';

  constructor(private router: Router, private produtosService: ProdutosService) { }

  adicionarItem() {
    const newItem: Item = { id: '', productName: this.productName, productObservation: this.productObservation, productValue: 25 };
    this.produtosService.criarItem(newItem).subscribe(
      () => {
        this.router.navigate(['/listagem']);
      },
      (error) => {
        this.router.navigate(['/listagem']);
      }
    );
  }
  
  voltar() {
    this.router.navigate(['/listagem']);
  }
}