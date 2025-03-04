import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModaladicionaritemComponent } from './modaladicionaritem.component';

describe('ModaladicionaritemComponent', () => {
  let component: ModaladicionaritemComponent;
  let fixture: ComponentFixture<ModaladicionaritemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModaladicionaritemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModaladicionaritemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
