import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationTerrainComponent } from './reservation-terrain.component';

describe('ReservationTerrainComponent', () => {
  let component: ReservationTerrainComponent;
  let fixture: ComponentFixture<ReservationTerrainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReservationTerrainComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationTerrainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
