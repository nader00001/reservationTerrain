import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerrainModalComponent } from './terrain-modal.component';

describe('TerrainModalComponent', () => {
  let component: TerrainModalComponent;
  let fixture: ComponentFixture<TerrainModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TerrainModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TerrainModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
