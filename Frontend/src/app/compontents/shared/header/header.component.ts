import { Component, DoCheck } from '@angular/core';
import { Router } from '@angular/router';
import { AuthStorageService } from 'src/app/auth/auth-storage.service';
import { HeaderConstant } from 'src/app/constants/header-constant';
import { UserService } from 'src/app/services/user.service';

/**
 * if you need return data you should not cover with {}  
 * eg : const isRolePresent = menuRoles.roles.find((fr) =>
      authStorageServices.getAuthRole().includes(fr)); 
      You should not cover with {} in find method 
 */

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  menus: any[] = [];
  //roles: string[] = ['ADMIN'];

  constructor(
    private authStorageServices: AuthStorageService,
    private router: Router,
    private userService: UserService
  ) {
    if (authStorageServices.getAuthRole() != null) {
      HeaderConstant.menus.forEach((menuRoles) => {
        const isRolePresent = menuRoles.roles.find(
          (fr) => authStorageServices.getAuthRole().includes(fr) //authStorageServices.getAuthRole()
        );
        if (isRolePresent) {
          this.menus.push(menuRoles);
        }
      });
    }
  }
  isUserPresent = this.authStorageServices.isUserLoggedIn();

  logout() {
    this.userService.logout();
  }
}
