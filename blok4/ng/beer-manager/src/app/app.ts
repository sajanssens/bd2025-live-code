import {Component, inject, signal} from '@angular/core';
import {FormsModule, NgForm, NgModel} from '@angular/forms';
import {Welcome} from './components/welcome/welcome';
import {BeersComponent} from './components/beers/beers.component';
import {BeerService} from './services/beer.service';
import {Beer} from './model/Beer';

@Component({
  selector: 'bm-root',
  imports: [
    FormsModule,
    Welcome,
    BeersComponent
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('beer-manager');
  inputBeerName = "";
  messages = "";
  private beerService = inject(BeerService)

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

  protected add() {
    const b = {id: 1, make: this.inputBeerName} as Beer;
    this.beerService.add(b)
  }
}
