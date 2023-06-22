import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistLoginComponent } from './pharmacist-login.component';

describe('PharmacistLoginComponent', () => {
  let component: PharmacistLoginComponent;
  let fixture: ComponentFixture<PharmacistLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PharmacistLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
