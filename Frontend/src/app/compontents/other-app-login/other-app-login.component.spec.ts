import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtherAppLoginComponent } from './other-app-login.component';

describe('OtherAppLoginComponent', () => {
  let component: OtherAppLoginComponent;
  let fixture: ComponentFixture<OtherAppLoginComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OtherAppLoginComponent]
    });
    fixture = TestBed.createComponent(OtherAppLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
