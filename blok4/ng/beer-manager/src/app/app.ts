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
  messages = "";

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

  protected validate(beerName: NgModel) {
    console.log("validate")
    this.messages = ""
    if (beerName.dirty) {
      if (beerName.hasError('required')) {
        this.messages = "Required field"
      }
      if (beerName.hasError('minlength')) {
        this.messages = "Minimal length is 3."
      }
    }
  }
}
