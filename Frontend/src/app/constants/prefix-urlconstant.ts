const premitAllURL = '/signinrequest/authentication';
const otherPremitAllURL = '/signinrequest/other-app';
const authorizationURL = '/signinrequest/authorization';

export const PrefixURLConstant = {
  ALREADY_GMAIL_EXISTS: `${premitAllURL}/auth/isexists-email`,
  SIGNUP_URL_API: `${premitAllURL}/auth/signup`,
  LOGIN_URL_API: `${premitAllURL}/auth/login`,
  OTHER_SIGNUP_URL_API: `${otherPremitAllURL}/signup`,
  OTHER_LOGIN_URL_API: `${otherPremitAllURL}/login`,
  REFRESH_TOKEN_URL_API: `${premitAllURL}/auth/refress-token`,
  LOGOUT_TOKEN_URL_API: `${premitAllURL}/logout`,
  AUTH_URL_API: `${authorizationURL}`,
  };
  