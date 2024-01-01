import { TestBed } from '@angular/core/testing';

import { OtherAppUserService } from './other-app-user.service';

describe('OtherAppUserService', () => {
  let service: OtherAppUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OtherAppUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
