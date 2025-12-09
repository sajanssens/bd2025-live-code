import {Component, input, output} from '@angular/core';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'bm-welcome',
  imports: [
    DatePipe
  ],
  templateUrl: './welcome.html',
  styleUrl: './welcome.scss',
})
export class Welcome {
  today = input(new Date());
  message = input('tijd voor bier');
  saysHello = output<string>()

  sayHello() {
    this.saysHello.emit("Hello!")
  }
}
