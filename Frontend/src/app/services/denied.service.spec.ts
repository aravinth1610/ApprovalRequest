import { TestBed } from '@angular/core/testing';

import { DeniedService } from './denied.service';

describe('DeniedService', () => {
  let service: DeniedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeniedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
