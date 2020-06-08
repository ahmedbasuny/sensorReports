import { TestBed } from '@angular/core/testing';

import { DeviceTempService } from './device-temp.service';

describe('DeviceTempService', () => {
  let service: DeviceTempService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeviceTempService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
