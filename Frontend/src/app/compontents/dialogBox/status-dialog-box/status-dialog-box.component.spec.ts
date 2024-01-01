import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusDialogBoxComponent } from './status-dialog-box.component';

describe('StatusDialogBoxComponent', () => {
  let component: StatusDialogBoxComponent;
  let fixture: ComponentFixture<StatusDialogBoxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatusDialogBoxComponent]
    });
    fixture = TestBed.createComponent(StatusDialogBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
