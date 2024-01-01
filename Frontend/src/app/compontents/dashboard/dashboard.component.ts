import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DashboardResponsePayload } from 'src/app/responsePayload/dashboard-response-payload';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit, OnDestroy {
  subscriptions: Subscription[] = [];
  dashboardResponseModal: DashboardResponsePayload[] = [];
  statuText!: string;

  constructor(private readonly dashboardService: DashboardService) {}

  ngOnInit(): void {
 //   this.dashboardResponseModal = this.dashboardService.dummyData;
    this.subscriptions.push(
      this.dashboardService.dashboardDetails().subscribe({
        next: (dashboardResponse: DashboardResponsePayload[]) => {
          console.log(dashboardResponse);
          this.dashboardResponseModal = dashboardResponse;
        },
      })
    );
  }
  ngOnDestroy(): void {
    this.subscriptions.forEach((unsubscription) => {
      unsubscription.unsubscribe();
    });
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
