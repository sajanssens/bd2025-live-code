import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeerDetailsComponent} from './beer-details.component';

describe('BeerDetails', () => {
  let component: BeerDetailsComponent;
  let fixture: ComponentFixture<BeerDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BeerDetailsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(BeerDetailsComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
