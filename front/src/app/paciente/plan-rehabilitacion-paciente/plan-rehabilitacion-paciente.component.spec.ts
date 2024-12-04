import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanRehabilitacionPacienteComponent } from './plan-rehabilitacion-paciente.component';

describe('PlanRehabilitacionPacienteComponent', () => {
  let component: PlanRehabilitacionPacienteComponent;
  let fixture: ComponentFixture<PlanRehabilitacionPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PlanRehabilitacionPacienteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlanRehabilitacionPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
