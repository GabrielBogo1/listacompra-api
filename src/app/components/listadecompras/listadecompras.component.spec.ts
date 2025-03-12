import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadecomprasComponent } from './listadecompras.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../../auth/auth.service';
import { of } from 'rxjs';

describe('ListadecomprasComponent', () => {
  let component: ListadecomprasComponent;
  let fixture: ComponentFixture<ListadecomprasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
       imports: [
              HttpClientModule,
              RouterTestingModule,
              ListadecomprasComponent
            ],
            providers: [
              { provide: ActivatedRoute, useValue: { params: of({}) } }, 
              { provide: AuthService, useValue: { login: jasmine.createSpy('login') } } 
            ]

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
