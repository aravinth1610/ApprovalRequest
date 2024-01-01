import { FormGroup, ValidationErrors, ValidatorFn } from "@angular/forms";

export const passwordMatchValidators: ValidatorFn = (group: FormGroup): ValidationErrors | null => {
        const password = group.get('password').value;
        const confirmPassword = group.get('confirmPassword').value;
        return password === confirmPassword ? null : { passwordMissMatch: true };
      };

