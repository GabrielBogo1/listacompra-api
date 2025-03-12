export class Item {
    id: string;
    productName: string;
    productObservation: string;
    productValue: number;

  constructor (id: string, productName: string, productObservation: string, productValue: number) {
    this.id = id;
    this.productName = productName;
    this.productObservation = productObservation;
    this.productValue = productValue;
  }

  }