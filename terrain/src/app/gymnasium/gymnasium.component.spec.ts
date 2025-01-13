import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GymnasiumComponent } from './gymnasium.component';

describe('GymnasiumComponent', () => {
  let component: GymnasiumComponent;
  let fixture: ComponentFixture<GymnasiumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GymnasiumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GymnasiumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
