import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RehabilitacionPacienteComponent } from './rehabilitacion-paciente.component';

describe('RehabilitacionPacienteComponent', () => {
  let component: RehabilitacionPacienteComponent;
  let fixture: ComponentFixture<RehabilitacionPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RehabilitacionPacienteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RehabilitacionPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
