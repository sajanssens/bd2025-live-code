import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'bm-navbar',
  templateUrl: './navbar.component.html',
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  // loggedInMessage = 'Not logged in.';

  // constructor(private userService: UserService) {
  //   userService.loggedInMessage$.subscribe(m =>
  //     this.loggedInMessage = m
  //   )
  // }

  isLoggedIn() {
    // return this.userService.isLoggedIn();
  }

  logout() {
    // return this.userService.logout();
  }
}
