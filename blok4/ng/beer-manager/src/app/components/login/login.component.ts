import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {User} from '../../model/User';
import {UserService} from '../../services/user.service';
import {Subject} from 'rxjs';
import {FormsModule} from '@angular/forms';
import {AsyncPipe} from '@angular/common';

@Component({
  selector: 'bm-login',
  templateUrl: './login.component.html',
  imports: [
    FormsModule,
    AsyncPipe
  ]
})
export class LoginComponent {
  user = {} as User;
  message$: Subject<string>;

  constructor(private service: UserService, private router: Router) {
    this.message$ = this.service.message$;
  }

  login(): void {
    console.log(this.user)
    this.service.login(this.user);
    this.user = {} as User;
  }
}
