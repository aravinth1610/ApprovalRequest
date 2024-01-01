import {
  AbstractControl,
  AsyncValidator,
  ValidationErrors,
} from '@angular/forms';
import { UserService } from '../services/user.service';
import { Observable, map } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AlreadyGmailIsExistsValidator implements AsyncValidator {
  constructor(private userServies: UserService) {}

  validate(control: AbstractControl): Observable<ValidationErrors | null> {
    return this.userServies.isGmailAlreadyExists(control.value).pipe(
      map((isEmailExists: any) => {
        console.log("in");
        
        return isEmailExists ? { isEmailExists: true } : null;
      })
    );
  }
}
