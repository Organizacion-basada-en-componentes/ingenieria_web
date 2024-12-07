import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanRehabilitacionMedicoComponent } from './plan-rehabilitacion-medico.component';

describe('PlanRehabilitacionMedicoComponent', () => {
  let component: PlanRehabilitacionMedicoComponent;
  let fixture: ComponentFixture<PlanRehabilitacionMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PlanRehabilitacionMedicoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlanRehabilitacionMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
