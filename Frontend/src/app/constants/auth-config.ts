import { AuthConfig } from "angular-oauth2-oidc";

export const authConfig : AuthConfig = {
    issuer : "http://localhost:8080/realms/oauthDemo",
    redirectUri : window.location.origin,
    clientId : 'oauthjwt',
    responseType : 'code',
    strictDiscoveryDocumentValidation : true,
    scope : 'openid profile email offline_access'
}