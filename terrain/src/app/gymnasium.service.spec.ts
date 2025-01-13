import { TestBed } from '@angular/core/testing';

import { GymnasiumService } from './gymnasium.service';

describe('GymnasiumService', () => {
  let service: GymnasiumService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GymnasiumService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
