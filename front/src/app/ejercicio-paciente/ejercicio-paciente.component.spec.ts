import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EjercicioPacienteComponent } from './ejercicio-paciente.component';

describe('EjercicioPacienteComponent', () => {
  let component: EjercicioPacienteComponent;
  let fixture: ComponentFixture<EjercicioPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EjercicioPacienteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EjercicioPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
