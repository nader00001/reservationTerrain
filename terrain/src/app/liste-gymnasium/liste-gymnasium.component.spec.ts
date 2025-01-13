import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeGymnasiumComponent } from './liste-gymnasium.component';

describe('ListeGymnasiumComponent', () => {
  let component: ListeGymnasiumComponent;
  let fixture: ComponentFixture<ListeGymnasiumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListeGymnasiumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListeGymnasiumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
