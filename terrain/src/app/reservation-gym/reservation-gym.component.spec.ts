import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationGymComponent } from './reservation-gym.component';

describe('ReservationGymComponent', () => {
  let component: ReservationGymComponent;
  let fixture: ComponentFixture<ReservationGymComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReservationGymComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationGymComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
