import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadecomprasComponent } from './listadecompras.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';

describe('ListadecomprasComponent', () => {
  let component: ListadecomprasComponent;
  let fixture: ComponentFixture<ListadecomprasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListadecomprasComponent, HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListadecomprasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
