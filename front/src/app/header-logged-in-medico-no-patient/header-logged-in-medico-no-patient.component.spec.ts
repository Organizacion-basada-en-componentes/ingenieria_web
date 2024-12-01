import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderLoggedInMedicoNoPatientComponent } from './header-logged-in-medico-no-patient.component';

describe('HeaderLoggedInMedicoNoPatientComponent', () => {
  let component: HeaderLoggedInMedicoNoPatientComponent;
  let fixture: ComponentFixture<HeaderLoggedInMedicoNoPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HeaderLoggedInMedicoNoPatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderLoggedInMedicoNoPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
