import {Routes} from '@angular/router';
import {BeersComponent} from './components/beers/beers.component';
import {About} from './components/about/about';
import {BeerDetailsComponent} from './components/beer-details/beer-details.component';
import {LoginComponent} from './components/login/login.component';
import {authGuard} from './guards/auth.guard';

export const routes: Routes = [
    {path: '', component: About},
    {path: 'about', component: About},
    {
        path: 'beers', component: BeersComponent, canActivate: [authGuard], children: [
            {path: ':id', component: BeerDetailsComponent}
        ]
    },
    {path: 'login', component: LoginComponent},
];
