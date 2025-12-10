import {Routes} from '@angular/router';
import {BeersComponent} from './components/beers/beers.component';
import {About} from './components/about/about';
import {BeerDetailsComponent} from './components/beer-details/beer-details.component';

export const routes: Routes = [
  {path: '', component: About},
  {path: 'about', component: About},
  {
    path: 'beers', component: BeersComponent, children: [
      {path: ':id', component: BeerDetailsComponent}
    ]
  },
  // {path: 'beers/:id', component: BeerComponent/*, canActivate: [authGuard]*/},
  // {path: 'login', component: LoginComponent},
];
