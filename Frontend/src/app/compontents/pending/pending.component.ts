import { HttpErrorResponse } from '@angular/common/http';
import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  QueryList,
  ViewChild,
  ViewChildren,
} from '@angular/core';
import { Subscription } from 'rxjs';
import { AppConstant } from 'src/app/constants/app-constant';
import { AuthResponse } from 'src/app/responsePayload/auth-response';
import { PendingService } from 'src/app/services/pending.service';
import { ToasterService } from 'src/app/toasters/toaster.service';
import { StatusDialogBoxComponent } from '../dialogBox/status-dialog-box/status-dialog-box.component';

@Component({
  selector: 'app-pending',
  templateUrl: './pending.component.html',
  styleUrls: ['./pending.component.css'],
})
export class PendingComponent implements OnInit, OnDestroy {
  @ViewChildren('pendingStatus') status : QueryList<ElementRef>;
  @ViewChildren('appvlbtn') approvalBtn : QueryList<ElementRef>;
  @ViewChildren('dendlbtn') deniedBtn : QueryList<ElementRef>;
  @ViewChild(StatusDialogBoxComponent) statusDialog: StatusDialogBoxComponent;
  pendingResponseModal: AuthResponse[] = [];
  //userDetails: AuthResponse;
  statusElementRef : ElementRef;
  btnElementRef : ElementRef;
  statuText: string;
  subscriptions: Subscription[] = [];

  constructor(
    private pendingServices: PendingService,
    private toasterServices: ToasterService
  ) {}

  ngOnInit(): void {
    // this.pendingResponse = this.pendingServices.dummyData;
    this.subscriptions.push(
      this.pendingServices.PendingDetails().subscribe({
        next: (pendingResponse: AuthResponse[]) => {
          console.log(pendingResponse);
          this.pendingResponseModal = pendingResponse;
        },
      })
    );
  }

  ngOnDestroy(): void {
    
    this.subscriptions.forEach((unsubscription) => {
      unsubscription.unsubscribe();
    });
  }

  // viewUser(userDetails: AuthResponse) {
  //   this.userDetails = userDetails;
  // }

  handleApproval(sno: number, id: number): void {
    console.log(id);
    this.subscriptions.push(
      this.pendingServices.updatePendingStatusToApproval(id).subscribe({
        next: (pendingUpdateResponse: any) => {
          let statusElementRef = this.status.get(sno);
          let btnElementRef = this.approvalBtn.get(sno);
          statusElementRef.nativeElement.innerHTML = 'Approval';
          statusElementRef.nativeElement.className =
            'badge rounded-pill bg-success';
          btnElementRef.nativeElement.disabled = true;
        },
        error: (errorResponse: HttpErrorResponse) => {
          this.toasterServices.showError(AppConstant.internalServerError);
        },
      })
    );
  }

  handleDenied(sno: number, id: number) {
      this.statusDialog.showModal(sno,id,'pending')
       this.statusElementRef = this.status.get(sno);
       this.btnElementRef = this.deniedBtn.get(sno);
       
    // this.subscriptions.push(
    //   this.pendingServices.updatePendingStatusToDenied(id).subscribe({
    //     next: (pendingUpdateResponse: any) => {
    //       let statusElementRef = this.status.get(sno);
    //       let btnElementRef = this.deniedBtn.get(sno);
    //       statusElementRef.nativeElement.innerHTML = 'Denied';
    //       statusElementRef.nativeElement.className =
    //         'badge rounded-pill bg-danger';
    //       btnElementRef.nativeElement.disabled = true;
    //     },
    //     error: (errorResponse: HttpErrorResponse) => {
    //       this.toasterServices.showError(AppConstant.internalServerError);
    //     },
    //   })
    // );
  }

  getClass(statusClassName: string) {
    let className: string;
    if (statusClassName === 'Approval') {
      this.statuText = 'Approval';
      className = 'badge rounded-pill bg-success';
    } else if (statusClassName === 'Denied') {
      this.statuText = 'Denied';
      className = 'badge rounded-pill bg-danger';
    } else {
      this.statuText = 'Pending';
      className = 'badge rounded-pill bg-warning text-dark';
    }
    return className;
  }
}
