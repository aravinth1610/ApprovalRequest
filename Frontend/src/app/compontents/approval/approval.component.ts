import { HttpErrorResponse } from '@angular/common/http';
import {
  Component,
  ElementRef,
  QueryList,
  ViewChild,
  ViewChildren,
} from '@angular/core';
import { Subscription } from 'rxjs';
import { AppConstant } from 'src/app/constants/app-constant';
import { AuthResponse } from 'src/app/responsePayload/auth-response';
import { ApprovalService } from 'src/app/services/approval.service';
import { ToasterService } from 'src/app/toasters/toaster.service';
import { StatusDialogBoxComponent } from '../dialogBox/status-dialog-box/status-dialog-box.component';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css'],
})
export class ApprovalComponent {
  @ViewChildren('dendlbtn') deniedBtn: QueryList<ElementRef>;
  @ViewChild(StatusDialogBoxComponent) statusDialog: StatusDialogBoxComponent;

  btnElementRef: ElementRef;
  subscriptions: Subscription[] = [];
  approvalResponseModal: AuthResponse[];
  statuText!: string;
  constructor(private approvalServices: ApprovalService) {}

  ngOnInit(): void {
    // this.pendingResponse = this.pendingServices.dummyData;
    //We are using in two components so we can use in BehaviorSubject it will be simple to subscribe and call data form one component
    this.subscriptions.push(
      this.approvalServices.ApprovalDetails().subscribe({
        next: (pendingResponse: AuthResponse[]) => {
          console.log(pendingResponse);
          this.approvalResponseModal = pendingResponse;
        },
      })
    );
  }
  ngOnDestroy(): void {
    this.subscriptions.forEach((unsubscription) => {
      unsubscription.unsubscribe();
    });
  }

  handleDenied(sno: number, id: number) {
    this.statusDialog.showModal(sno, id, 'approval');
    this.btnElementRef = this.deniedBtn.get(sno);
    this.approvalResponseModal = this.approvalResponseModal.filter(
      (x) => x.userId != id
    );
  }
}
