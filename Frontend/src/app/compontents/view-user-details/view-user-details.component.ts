import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthResponse } from 'src/app/responsePayload/auth-response';
import { PendingService } from 'src/app/services/pending.service';

@Component({
  selector: 'app-view-user-details',
  templateUrl: './view-user-details.component.html',
  styleUrls: ['./view-user-details.component.css'],
})
export class ViewUserDetailsComponent implements OnInit, OnDestroy {
  pendingUserDetail: AuthResponse;
  subscriptions: Subscription;
  constructor(
    private activeRoute: ActivatedRoute,
    private pendingServices: PendingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.subscriptions = this.activeRoute.paramMap.subscribe((params) => {
      let statusId = Number(params.get('id'));
      console.log(statusId);

      this.pendingServices.PendingUserDetails(statusId).subscribe({
        next: (pendingDetails: AuthResponse) => {
          this.pendingUserDetail = pendingDetails;
        },
      });

      // this.pendingServices.pendingResponseBehvSubject.subscribe({
      //   next: (pendingDetails: PendingResponsePayload[]) => {
      //     if (pendingDetails) {
      //       this.pendingUserDetail = pendingDetails.find(
      //         (p) => p.status.statusId === statusId
      //       );
      //     } else {
      //       this.router.navigate(['/pending']);
      //     }
      //   },
      // });
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
