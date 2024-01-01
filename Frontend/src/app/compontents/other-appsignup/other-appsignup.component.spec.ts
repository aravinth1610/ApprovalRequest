import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtherAppsignupComponent } from './other-appsignup.component';

describe('OtherAppsignupComponent', () => {
  let component: OtherAppsignupComponent;
  let fixture: ComponentFixture<OtherAppsignupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OtherAppsignupComponent]
    });
    fixture = TestBed.createComponent(OtherAppsignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
