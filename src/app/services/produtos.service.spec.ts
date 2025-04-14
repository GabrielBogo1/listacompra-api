import { TestBed } from '@angular/core/testing';

import { ProdutosService } from './produtos.service';
import { HttpClientModule } from '@angular/common/http';
import { Item } from '../models/item/item';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from '../enviroments/enviroment.dev';

//teste

describe('ProdutosService', () => {
  let service: ProdutosService;
  let httpMock: HttpTestingController;
  const API = environment.SERVIDOR + "/produto";

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProdutosService]
    });

    service = TestBed.inject(ProdutosService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('deve ser criado', () => {
    expect(service).toBeTruthy();
  });

  it('deve retornar uma lista de itens', () => {
    const mockItems: Item[] = [
      { id: '1', productName: 'Produto 1', productObservation: 'tst', productValue: 25 },
      { id: '2', productName: 'Produto 2', productObservation: 'tst',productValue: 25 }
    ];

    service.getItens().subscribe(items => {
      expect(items.length).toBe(2);
      expect(items).toEqual(mockItems);
    });

    const req = httpMock.expectOne(API);
    expect(req.request.method).toBe('GET');
    req.flush(mockItems);
  });

  it('deve retornar um item pelo ID', () => {
    const mockItem: Item = { id: '1', productName: 'Produto 1', productObservation: "tst", productValue: 25 };

    service.getItemById('1').subscribe(item => {
      expect(item).toEqual(mockItem);
    });

    const req = httpMock.expectOne(`${API}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(mockItem);
  });

  it('deve criar um novo item', () => {
    const newItem: Item = { id: '3', productName: 'Produto 3', productObservation: 'tst', productValue: 25 };

    service.criarItem(newItem).subscribe(item => {
      expect(item).toEqual(newItem);
    });

    const req = httpMock.expectOne(API);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newItem);
    req.flush(newItem);
  });

  it('deve atualizar um item existente', () => {
    const updatedItem: Item = { id: '1', productName: 'Produto Atualizado', productObservation: 'tst', productValue: 25 };

    service.atualizarItem('1', updatedItem).subscribe(item => {
      expect(item).toEqual(updatedItem);
    });

    const req = httpMock.expectOne(`${API}/1`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedItem);
    req.flush(updatedItem);
  });

  it('deve deletar um item pelo ID', () => {
    service.deletarItem('1').subscribe(response => {
      expect(response).toBeNull(); // Alterado de 'toBeUndefined()' para 'toBeNull()'
    });
  
    const req = httpMock.expectOne(`${API}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });

});

