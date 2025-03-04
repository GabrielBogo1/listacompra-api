import { Routes } from '@angular/router';
import { ListadecomprasComponent } from './components/listadecompras/listadecompras.component';
import { LoginComponent } from './components/login/login.component';
import { CadastroComponent } from './components/cadastro/cadastro.component';
import { ModaladicionaritemComponent } from './components/modaladicionaritem/modaladicionaritem.component';
import { EditarItemComponent } from './editar-item/editar-item.component';
import { CheckoutComponent } from './components/checkout/checkout.component';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'cadastro', component: CadastroComponent },
    { path: 'listagem', component: ListadecomprasComponent },
    { path: 'adicionar-item', component: ModaladicionaritemComponent },
    { path: 'editar-item/:id', component: EditarItemComponent },
    { path: 'checkout', component: CheckoutComponent }
];
