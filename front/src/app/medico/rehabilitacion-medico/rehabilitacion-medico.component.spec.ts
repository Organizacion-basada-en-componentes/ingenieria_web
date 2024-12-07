import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RehabilitacionMedicoComponent } from './rehabilitacion-medico.component';

describe('RehabilitacionMedicoComponent', () => {
  let component: RehabilitacionMedicoComponent;
  let fixture: ComponentFixture<RehabilitacionMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RehabilitacionMedicoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RehabilitacionMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
