import { Component, OnInit, inject } from '@angular/core';
import { NgxPayPalModule, IPayPalConfig } from 'ngx-paypal';
import { PaypalService } from '../../services/paypal/paypal.service';

@Component({
  selector: 'app-checkout',
  imports: [NgxPayPalModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css',
})

export class CheckoutComponent implements OnInit {
  paypalService = inject(PaypalService);
  public payPalConfig?: IPayPalConfig;

  ngOnInit(): void {
    this.initConfig();
  }

  private initConfig(): void {
    this.payPalConfig = this.paypalService.initConfig();
  }
}
