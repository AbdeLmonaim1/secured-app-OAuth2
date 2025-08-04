import { TestBed } from '@angular/core/testing';

import { Security } from './security';

describe('Security', () => {
  let service: Security;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Security);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
