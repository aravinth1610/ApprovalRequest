import { Injectable } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';

@Injectable({
  providedIn: 'root',
})
export class ToasterService {
  constructor(private readonly toast: NgToastService) {}

  showSuccess(successMessage: string) {
    this.toast.success({
      detail: 'SUCCESS',
      summary: successMessage,
      duration: 2000,
      position: 'topLeft',
    });
  }

  showError(errorMessage: string) {
    this.toast.error({
      detail: 'ERROR',
      summary: errorMessage,
      sticky: true,
      duration: 2000,
      position: 'botomCenter',
    });
  }

  showInfo(errorMessage: string) {
    this.toast.info({
      detail: 'INFO',
      summary: errorMessage,
      duration: 2000,
      sticky: true,
      position: 'topRight',
    });
  }

  showWarn(errorMessage: string) {
    this.toast.warning({
      detail: 'WARN',
      summary: errorMessage,
      duration: 2000,
      sticky: true,
      position: 'topRight',
    });
  }
}
