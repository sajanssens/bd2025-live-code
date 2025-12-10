import {Component, inject, OnInit} from '@angular/core';
import {AsyncPipe, CurrencyPipe} from '@angular/common';
import {PrettyBeerPipe} from '../../pipes/pretty-beer.pipe';
import {BeerService} from '../../services/beer.service';


@Component({
  selector: 'bm-beers',
  templateUrl: './beers.component.html',
  imports: [CurrencyPipe, PrettyBeerPipe, AsyncPipe],
  styleUrl: './beers.component.scss'
})
export class BeersComponent implements OnInit {

  // beers: Beer[] = []
  private beerService = inject(BeerService)
  beers$ = this.beerService.beersAreUpdated$

  // private beerService = inject(BeerServiceInMemory)

  ngOnInit(): void {
    // this.beerService.getAll().subscribe(result => {
    //   this.beers = result
    //   console.log(this.beers)
    // });

    // this.beers$ = this.beerService.getAll()
    this.beerService.getAll()
  }

  protected delete($event: PointerEvent, id: number | undefined) {
    // todo
  }
}
