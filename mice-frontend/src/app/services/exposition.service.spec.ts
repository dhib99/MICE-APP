import { TestBed } from '@angular/core/testing';

import { ExpositionService } from './exposition.service';

describe('ExpositionService', () => {
  let service: ExpositionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExpositionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
