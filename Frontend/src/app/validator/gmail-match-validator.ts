import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const GmailMatchValidator: ValidatorFn = (
  control: AbstractControl
): ValidationErrors | null => {
  const gmail = control.value?.toLowerCase();
  const gmailEntity = ['admin@gmail.com', 'user@gmail.com', 'password@gmail.com'];
  return gmailEntity.includes(gmail) ? { gmailAuthMatch: true } : null;
};
