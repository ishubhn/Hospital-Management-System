import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDoctorLoginComponent } from './admin-doctor-login.component';

describe('AdminDoctorLoginComponent', () => {
  let component: AdminDoctorLoginComponent;
  let fixture: ComponentFixture<AdminDoctorLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminDoctorLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDoctorLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
