import {Component} from '@angular/core';
import {UserService} from '../../services/user.service';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'bm-navbar',
  templateUrl: './navbar.component.html',
  imports: [
    RouterLinkActive,
    RouterLink
  ],
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  loggedInMessage = 'Not logged in.';

  constructor(private userService: UserService) {
    userService.loggedInMessage$.subscribe(m =>
      this.loggedInMessage = m
    )
  }

  isLoggedIn() {
    return this.userService.isLoggedIn();
  }

  logout() {
    return this.userService.logout();
  }
}
