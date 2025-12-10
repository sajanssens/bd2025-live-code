import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NavbarComponent} from './components/navbar/navbar.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'bm-root',
  imports: [
    FormsModule,
    NavbarComponent,
    RouterOutlet,
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {

  messages = "";

  protected handleHelloEvent(event: string) {
    this.messages = `We said: ${event}`
  }

}
