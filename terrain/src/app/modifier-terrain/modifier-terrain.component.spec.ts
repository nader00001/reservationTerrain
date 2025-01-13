import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierTerrainComponent } from './modifier-terrain.component';

describe('ModifierTerrainComponent', () => {
  let component: ModifierTerrainComponent;
  let fixture: ComponentFixture<ModifierTerrainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifierTerrainComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifierTerrainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
