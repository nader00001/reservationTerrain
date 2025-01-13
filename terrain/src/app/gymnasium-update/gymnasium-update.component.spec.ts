import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GymnasiumUpdateComponent } from './gymnasium-update.component';

describe('GymnasiumUpdateComponent', () => {
  let component: GymnasiumUpdateComponent;
  let fixture: ComponentFixture<GymnasiumUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GymnasiumUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GymnasiumUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
