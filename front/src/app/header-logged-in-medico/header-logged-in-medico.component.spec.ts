import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderLoggedInMedicoComponent } from './header-logged-in-medico.component';

describe('HeaderLoggedInMedicoComponent', () => {
  let component: HeaderLoggedInMedicoComponent;
  let fixture: ComponentFixture<HeaderLoggedInMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HeaderLoggedInMedicoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderLoggedInMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
