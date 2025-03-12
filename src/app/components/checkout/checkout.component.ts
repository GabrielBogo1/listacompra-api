import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { NgxPayPalModule, IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';
import { PaypalService } from '../../services/paypal/paypal.service';
import { Item } from '../../models/item/item';
import { CommonModule } from '@angular/common';
import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/enviroment.dev';

@Component({
  selector: 'app-checkout',
  imports: [NgxPayPalModule, CommonModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css',
})
export class CheckoutComponent implements OnInit {
  paypalService = inject(PaypalService);
  public payPalConfig?: IPayPalConfig;
  public items: Item[] = [];

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    this.items = navigation?.extras?.state?.['items'] || [];
  }

  ngOnInit(): void {
    this.initConfig();
  }

  private initConfig(): void {
    this.payPalConfig = {
      currency: 'BRL',
      clientId: environment.PAYPAL_CLIENT_ID,
      createOrderOnClient: (data) =>
        <ICreateOrderRequest>{
          intent: 'CAPTURE',
          purchase_units: [
            {
              amount: {
                currency_code: 'BRL',
                // Usa o total calculado dinamicamente
                value: this.getTotal().toString(),
                breakdown: {
                  item_total: {
                    currency_code: 'BRL',
                    value: this.getTotal().toString(),
                  },
                },
              },
              // Mapeia cada item para o formato esperado pelo PayPal
              items: this.items.map((item) => ({
                name: item.productName || 'Produto',
                quantity: "1",
                category: 'DIGITAL_GOODS',
                unit_amount: {
                  currency_code: 'BRL',
                  value: (item.productValue || 0).toString(),
                },
              })),
            },
          ],
        },
      advanced: {
        commit: 'true',
      },
      style: {
        label: 'paypal',
        layout: 'vertical',
      },
      onApprove: (data, actions) => {
        console.log('onApprove - transaction approved', data, actions);
        actions.order.get().then((details: any) => {
          console.log('Full order details:', details);
        });
      },
      onClientAuthorization: (data) => {
        console.log('onClientAuthorization - transaction completed', data);
      },
      onCancel: (data, actions) => {
        console.log('OnCancel', data, actions);
      },
      onError: (err) => {
        console.log('OnError', err);
      },
      onClick: (data, actions) => {
        console.log('onClick', data, actions);
      },
    };
  }

  public getTotal(): number {
    let total = 0;
    if (this.items && this.items.length) {
      for (const item of this.items) {
        total += item.productValue || 0;
      }
    }
    return total;
  }
}