export class AppConstant {

    static JWTKeyToken = "jwt-auth"
    static JWTHeader = "Authorization"
    static JWTCookiesToken = "LOGIN_INFO";

    static internalServerError = "Internal Server Error,Please try again";

    static signupSuccessHeader = "Registration Successful";
    static signupSuccessDetail = "Your account has been successfully created. A confirmation email has been sent to your email address. Please, verify.";
    static signupErrorHeader = "Registration is not Successful";

    static LoginSuccessHeader = "user Successfully Login";
    static InvalidUsernamePasswordErrorHeader = "Invalid Username and Password";
    static InvalidPasswordErrorHeader = "Invalid Password for this username";

    static emailVerifySuccessHeader = "Email Verified";
    static emailVerifySuccessDetail = "Your email has been verified successfully.";

    static emailChangeSuccessHeader = "Email Changed Successfully | Verify";
    static emailChangeSuccessDetail = "Your email has been changed successfully. A confirmation email has been sent to your email address. Please, verify.";

    static forgotPasswordSuccessHeader = "Email Sent";
    static forgotPasswordSuccessDetail = "An email has been sent to the given email address if it exist in our database. Please, verify.";

    static passwordChangeSuccessHeader = "Password Changed Successfully";
    static passwordChangeSuccessDetail = "Your password has been changed successfully. Please, login with your new password.";

    static notFoundErrorHeader = '404 Not Found';
    static notFoundErrorDetail = 'What you are looking for does not exist.';
   
    static signUpDialogMessage = "Do you want to Exist this Page...";


}
