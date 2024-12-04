import { TestBed } from '@angular/core/testing';

import { SelectedAlertaService } from './selected-alerta.service';

describe('SelectedAlertaService', () => {
  let service: SelectedAlertaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SelectedAlertaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
