import {Component, signal} from '@angular/core';
import {DatePipe} from '@angular/common';
import {FormsModule, NgForm, NgModel} from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [
    DatePipe,
    FormsModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('beer-manager');
  today = new Date();
  inputBeerName = "";

  protected save(form: NgForm) {
    if (form.invalid) {
      console.log("Try again...")
    } else {
      console.log(`Saving ${this.inputBeerName}`)
    }
  }

  protected clear($event: any) {
    $event.preventDefault()
    this.inputBeerName = ""

  }

  protected validate(beerNameInput: NgModel) {

  }
}
