import {AuthConfig, OAuthModuleConfig} from "angular-oauth2-oidc";
import {environment} from "../../../environments/environment";

const apiHost = environment.apiHost;

export const authConfig: AuthConfig = {
    issuer: 'https://steyer-identity-server.azurewebsites.net/identity',
    clientId: 'clientIdPassword',
    dummyClientSecret: '123',
    tokenEndpoint: `${apiHost}/oauth/token`,
    userinfoEndpoint: `${apiHost}/api/v1/accounts`,
    logoutUrl: `${apiHost}/oauth/token/invoke`,
    showDebugInformation: true,
    scope: '',
    useHttpBasicAuthForPasswordFlow: true,
    oidc: false
}

export const urlsAuth: OAuthModuleConfig = {
    resourceServer: {
        allowedUrls: [`${apiHost}/api/v1/bookings`],
        sendAccessToken: true,
    }
}