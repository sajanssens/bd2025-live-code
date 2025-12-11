import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeerFormComponent} from './beer-form.component';

describe('Beer', () => {
  let component: BeerFormComponent;
  let fixture: ComponentFixture<BeerFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BeerFormComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(BeerFormComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
