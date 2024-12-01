import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedAlertaComponent } from './selected-alerta.component';

describe('SelectedAlertaComponent', () => {
  let component: SelectedAlertaComponent;
  let fixture: ComponentFixture<SelectedAlertaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SelectedAlertaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectedAlertaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
