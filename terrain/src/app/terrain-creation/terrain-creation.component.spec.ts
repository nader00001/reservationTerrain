import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerrainCreationComponent } from './terrain-creation.component';

describe('TerrainCreationComponent', () => {
  let component: TerrainCreationComponent;
  let fixture: ComponentFixture<TerrainCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TerrainCreationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TerrainCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
