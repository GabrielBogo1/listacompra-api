import { Injectable } from '@angular/core';
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';
import { environment } from '../../enviroments/enviroment.dev';

//teste

@Injectable({
  providedIn: 'root'
})

export class PaypalService {
  constructor() { }

  public initConfig(): IPayPalConfig {
      let payPalConfig: IPayPalConfig;

      payPalConfig = {
        currency: 'BRL',
        clientId: environment.PAYPAL_CLIENT_ID,
        createOrderOnClient: (data) =>
          <ICreateOrderRequest>{
            intent: 'CAPTURE',
            purchase_units: [
              {
                amount: {
                  currency_code: 'BRL',
                  value: '9.99',
                  breakdown: {
                    item_total: {
                      currency_code: 'BRL',
                      value: '9.99',
                    },
                  },
                },
                items: [
                  {
                    name: 'Enterprise Subscription',
                    quantity: '1',
                    category: 'DIGITAL_GOODS',
                    unit_amount: {
                      currency_code: 'BRL',
                      value: '9.99',
                    },
                  },
                ],
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
          console.log(
            'onApprove - transaction was approved, but not authorized',
            data,
            actions
          );
          actions.order.get().then((details: any) => {
            console.log(
              'onApprove - you can get full order details inside onApprove: ',
              details
            );
          });
        },
        onClientAuthorization: (data) => {
          console.log(
            'onClientAuthorization - you should probably inform your server about completed transaction at this point',
            data
          );
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

      return payPalConfig;
    }
}
