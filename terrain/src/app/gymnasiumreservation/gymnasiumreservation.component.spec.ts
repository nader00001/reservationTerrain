import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GymnasiumreservationComponent } from './gymnasiumreservation.component';

describe('GymnasiumreservationComponent', () => {
  let component: GymnasiumreservationComponent;
  let fixture: ComponentFixture<GymnasiumreservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GymnasiumreservationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GymnasiumreservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
