import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-signup-dialog',
  templateUrl: './signup-dialog.component.html',
  styleUrls: ['./signup-dialog.component.css'],
})
export class SignupDialogComponent {
  @ViewChild('modalDiag') signUpModal: ElementRef;
  @Input() signUpMessage : string;
  @ViewChild('closebutton') closebutton : ElementRef;
  resolve: Function;
  reject: Function;

  showModal(resolve: Function, reject: Function) {
    const modal = new Modal(this.signUpModal.nativeElement);
    modal.show();
    this.resolve = resolve;
    this.reject = reject;
    console.log(this.resolve,this.reject);
    
  }

  onDismiss() {
    this.reject();
  }

  onConfirm() {  
    this.closebutton.nativeElement.click();
    this.resolve();
  }  



}
