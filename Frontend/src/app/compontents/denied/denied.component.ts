import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { Subscription } from 'rxjs';
import { AppConstant } from 'src/app/constants/app-constant';
import { AuthResponse } from 'src/app/responsePayload/auth-response';
import { DeniedService } from 'src/app/services/denied.service';
import { ToasterService } from 'src/app/toasters/toaster.service';
import { StatusDialogBoxComponent } from '../dialogBox/status-dialog-box/status-dialog-box.component';

@Component({
  selector: 'app-denied',
  templateUrl: './denied.component.html',
  styleUrls: ['./denied.component.css'],
})
export class DeniedComponent {
  @ViewChildren('appvlbtn') approvalBtn: QueryList<ElementRef>;
  @ViewChild(StatusDialogBoxComponent) statusDialog: StatusDialogBoxComponent;

  btnElementRef : ElementRef;
  subscriptions: Subscription[] = [];
  deniedResponseModal: AuthResponse[];
  statuText!: string;
  constructor(
    private deniedService: DeniedService,
    private toasterServices: ToasterService
  ) {}

  ngOnInit(): void {
    // this.pendingResponse = this.pendingServices.dummyData;
    //We are using in two components so we can use in BehaviorSubject it will be simple to subscribe and call data form one component
    this.subscriptions.push(
      this.deniedService.DeniedDetails().subscribe({
        next: (pendingResponse: AuthResponse[]) => {
          console.log(pendingResponse);
          this.deniedResponseModal = pendingResponse;
        },
      })
    );
  }

  handleApproval(sno: number, id: number): void {

    this.statusDialog.showModal(sno, id, 'denied');
    this.btnElementRef = this.approvalBtn.get(sno);
    
  }
}
