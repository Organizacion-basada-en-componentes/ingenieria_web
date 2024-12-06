import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderLoggedInPacienteComponent } from './header-logged-in-paciente.component';

describe('HeaderLoggedInPacienteComponent', () => {
  let component: HeaderLoggedInPacienteComponent;
  let fixture: ComponentFixture<HeaderLoggedInPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HeaderLoggedInPacienteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderLoggedInPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
