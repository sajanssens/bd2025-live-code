import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'bm-beer-details',
  imports: [],
  templateUrl: './beer-details.component.html',
  styleUrl: './beer-details.component.scss',
})
export class BeerDetailsComponent {
  protected id = "unknown"

  constructor(private route: ActivatedRoute) {
    // this.id = this.route.snapshot.params['id'];
    this.route.paramMap.subscribe(
      params => this.id = params.get('id') ?? "unknown"
    )
  }
}
