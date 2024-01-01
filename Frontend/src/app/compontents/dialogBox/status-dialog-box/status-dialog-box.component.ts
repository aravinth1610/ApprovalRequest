import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  ViewChild,
} from '@angular/core';
import { Modal } from 'bootstrap';
import { Subscription } from 'rxjs';
import { PendingComponent } from '../../pending/pending.component';
import { PendingService } from 'src/app/services/pending.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AppConstant } from 'src/app/constants/app-constant';
import { ToasterService } from 'src/app/toasters/toaster.service';
import { ApprovalService } from 'src/app/services/approval.service';
import { ApprovalComponent } from '../../approval/approval.component';
import { DeniedService } from 'src/app/services/denied.service';

@Component({
  selector: 'app-status-dialog-box',
  templateUrl: './status-dialog-box.component.html',
  styleUrls: ['./status-dialog-box.component.css'],
})
export class StatusDialogBoxComponent {
  @ViewChild('modalDiag') signUpModal: ElementRef;
  @ViewChild('closebutton') closebutton: ElementRef;
  @Input() pendingStatusElementRef: ElementRef;
  @Input() pendingBtnElementRef: ElementRef;
  @Input() approvalBtnElementRef: ElementRef;
  @Input() deniedBtnElementRef: ElementRef;

  id: number;
  sno: number;
  status: string;
  statusByServiceCall: string;
  subscriptions: Subscription[] = [];

  constructor(
    //private pendingComponent: PendingComponent,
    //private approvalComponent: ApprovalComponent,
    private pendingServices: PendingService,
    private approvalServices: ApprovalService,
    private deniedService :DeniedService,
    private toasterServices: ToasterService
  ) {}

  ngOnDestroy(): void {
    this.subscriptions.forEach((unsubscription) => {
      unsubscription.unsubscribe();
    });
  }

  showModal(sno: number, id: number, status: string) {
    const modal = new Modal(this.signUpModal.nativeElement);
    modal.show();
    this.id = id;
    this.sno = sno;
    this.statusByServiceCall = status;
    if (status === 'pending' || status === 'approval') {
      this.status = 'Denied';
    } else {
      this.status = 'Approved';
    }
  }

  onConfirm(statusMessage: any) {
    if (this.statusByServiceCall === 'pending') {
      this.subscriptions.push(
        this.pendingServices
          .updatePendingStatusToDenied(this.id, statusMessage.value)
          .subscribe({
            next: (pendingUpdateResponse: any) => {
              this.pendingStatusElementRef.nativeElement.innerHTML = 'Denied';
              this.pendingStatusElementRef.nativeElement.className =
                'badge rounded-pill bg-danger';
              this.pendingBtnElementRef.nativeElement.disabled = true;
              this.closebutton.nativeElement.click();
            },
            error: (errorResponse: HttpErrorResponse) => {
              this.toasterServices.showError(AppConstant.internalServerError);
            },
          })
      );
    } else if (this.statusByServiceCall == 'approval') {
      this.subscriptions.push(
        this.approvalServices
          .updateApprovalStatusToDenied(this.id, statusMessage)
          .subscribe({
            next: (pendingUpdateResponse: any) => {
              this.approvalBtnElementRef.nativeElement.disabled = true;
              this.closebutton.nativeElement.click();
            },
            error: (errorResponse: HttpErrorResponse) => {
              this.toasterServices.showError(AppConstant.internalServerError);
            },
          })
      );
    }else if(this.statusByServiceCall == 'denied'){
      this.subscriptions.push(
        this.deniedService.updateDeniedStatusToApproval(this.id).subscribe({
          next: (pendingUpdateResponse: any) => {
            this.deniedBtnElementRef.nativeElement.disabled = true;
          },
          error: (errorResponse: HttpErrorResponse) => {
            this.toasterServices.showError(AppConstant.internalServerError);
          },
        })
      );
    }
  }
}
