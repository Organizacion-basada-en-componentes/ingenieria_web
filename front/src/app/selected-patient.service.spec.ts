import { TestBed } from '@angular/core/testing';

import { SelectedPatientService } from './selected-patient.service';

describe('SelectedPatientService', () => {
  let service: SelectedPatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SelectedPatientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
