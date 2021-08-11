import { TestBed } from '@angular/core/testing';

import { RouteGuardAdminService } from './route-guard-admin.service';

describe('RouteGuardAdminService', () => {
  let service: RouteGuardAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RouteGuardAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
