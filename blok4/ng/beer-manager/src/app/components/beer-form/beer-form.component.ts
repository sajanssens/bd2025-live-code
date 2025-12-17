import {Component, inject} from '@angular/core';
import {FormsModule, NgForm, NgModel} from '@angular/forms';
import {BeerService} from '../../services/beer.service';
import {Beer} from '../../model/Beer';


@Component({
  selector: 'bm-beer',
  imports: [
    FormsModule
  ],
  templateUrl: './beer-form.component.html',
  styleUrl: './beer-form.component.scss',
})
export class BeerFormComponent {
  inputBeerName = "";
  messages = "";
  private beerService = inject(BeerService)
  protected inputBeerType = "";
  protected inputBeerPrice = 1.90;

  protected save(form: NgForm) {
    if (form.invalid) {
      console.log("Try again...")
    } else {
      console.log(`Saving ${this.inputBeerName}`)
      this.add()
    }
  }

  protected clear($event: PointerEvent) {
    $event.preventDefault()
    this.inputBeerName = ""
    this.inputBeerType = ""
    this.inputBeerPrice = 0.0
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

  private add() {
    const b = {
      id: Math.floor(Math.random() * 100000),
      make: this.inputBeerName,
      type: this.inputBeerType,
      price: this.inputBeerPrice
    } as Beer;
    this.beerService.add(b)
  }
}
