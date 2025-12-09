import {Component, signal} from '@angular/core';
import {FormsModule, NgForm, NgModel} from '@angular/forms';
import {Welcome} from './components/welcome/welcome';

@Component({
  selector: 'app-root',
  imports: [
    FormsModule,
    Welcome
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('beer-manager');
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

  protected handleHelloEvent(event: string) {
    this.messages = `We said: ${event}`
  }
}
