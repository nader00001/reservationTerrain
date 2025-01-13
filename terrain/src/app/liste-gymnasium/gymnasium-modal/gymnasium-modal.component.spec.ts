import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GymnasiumModalComponent } from './gymnasium-modal.component';

describe('GymnasiumModalComponent', () => {
  let component: GymnasiumModalComponent;
  let fixture: ComponentFixture<GymnasiumModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GymnasiumModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GymnasiumModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
