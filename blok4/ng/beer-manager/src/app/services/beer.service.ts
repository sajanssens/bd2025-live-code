import {Injectable} from '@angular/core';
import {Beer} from '../model/Beer';
import {Observable, of} from 'rxjs';

@Injectable({providedIn: 'root'})
export class BeerService {
  private readonly beers = {} as Beer[]

  constructor() {
    this.beers = [{
      id: 1,
      make: "Leffe",
      type: "Blond",
      price: 1.89
    }, {
      id: 2,
      make: "Leffe",
      type: "Tripel",
      price: 2.09
    }, {
      type: "Pils",
      make: "Grolsch",
      price: 0.89,
      id: 3
    }, {
      make: "LaTrappe",
      type: "Dubbel",
      price: 2,
      id: 5
    }, {
      make: "Karmeliet",
      type: "Tripel",
      price: 1,
      id: 6
    }]
  }

  getAllSync(): Beer[] {
    return this.beers;
  }

  getAll(): Observable<Beer[]> {
    return of(this.beers);
  }

  add(b: Beer) {
    this.beers.push(b)
  }

}
