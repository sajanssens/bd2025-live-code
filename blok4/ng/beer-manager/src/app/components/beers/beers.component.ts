import {Component, inject, OnInit} from '@angular/core';
import {CurrencyPipe} from '@angular/common';
import {PrettyBeerPipe} from '../../pipes/pretty-beer.pipe';
import {BeerService} from '../../services/beer.service';
import {Beer} from '../../model/Beer';


@Component({
  selector: 'bm-beers',
  templateUrl: './beers.component.html',
  imports: [
    CurrencyPipe,
    PrettyBeerPipe
  ],
  styleUrl: './beers.component.scss'
})
export class BeersComponent implements OnInit {

  beers: Beer[] = []
  private beerService = inject(BeerService)

  ngOnInit(): void {
    this.beers = this.beerService.getAllSync()// synchroon
    this.beerService.getAll().subscribe(
      result => this.beers = result
    );
  }


  protected delete($event: PointerEvent, id: number) {
    // todo
  }
}
