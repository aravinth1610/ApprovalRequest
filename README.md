ClientApproval Application
  
     This project is base on Other User need to SignUp in other-signup.
     The main user need to Approval and then only they can signIn the other-signIn
Main Users :
   ADMIN : They can do the operation in Dashboard,Pending,Approval,Denied.
   USER : They can only View the Dashboard.

Angular : 
      we used in this Application CLI :   
           Angular Popup : npm i ng-angular-popup(https://www.npmjs.com/package/ng-angular-popup)
           {
            --need to put in Appcomponent.html
            <ng-toast [style]="{ width: '400px' }"></ng-toast>
           }
           Bootstrap-5   : npm install bootstrap@5
           {
            "./node_modules/bootstrap/dist/css/bootstrap.min.css"
           }
           Jquery        :  npm install jquery
           {
             "./node_modules/jquery/dist/jquery.min.js",
              "./node_modules/bootstrap/dist/js/bootstrap.min.js"
           }
           Angular Animations : npm install @angular/animations --save
           Fontawesome : npm install @fortawesome/fontawesome-free
           Auth0 JWT decoder  : npm install @auth0/angular-jwt
           {
             private jwtServices = new JwtHelperService();
           }
           Cookie decoder : npm install ngx-cookie-service@16.1.0 --save //It will not work
           {
            private cookiesServices: CookieService
           }

  We used : 

          @ViewChild,@ViewChildren,@Input,@Output,jwt decoder,OAuth decoder,Bootstrap Modal
          Role base Router,template Ref,Reactive form,asyn and non asyn validations,Auth guard
          (canActivate,deActivate),Toaster,BehaviourSubject,subject,RequestParam,pathVariables
          LocalStorage,Level wise propteries,POST,PUT,GET.         

Spring Boot :
        It can access for all NOSQL's Database.

 we used :  

        Authentication,Authorization,Role Base Authentication,JWT Token,
        Refress JWT Token,validations,Multiple values can take from propteries file,
        Level wise propteries,POST,PUT,GET,pathVariables,RequestParam,Cookies Storage,
        can take particular cell form database using JPARepository,default value Insert
        in Database using JPA Annotations,Gloable exception,Join column (OneToOne).    
