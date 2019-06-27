import {Injectable} from '@angular/core';
import {JwksValidationHandler, OAuthService} from "angular-oauth2-oidc";
import {authConfig} from "../model/extra/oauth2-config";
import {Observable, Subject} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private subject = new Subject<any>();

    constructor(private oauthService: OAuthService) {
        console.log('constructor auth service');

        // set up OAuth service
        this.oauthService.configure(authConfig);
        this.oauthService.tokenValidationHandler = new JwksValidationHandler();
        this.oauthService.setStorage(localStorage);

        // logging stored token
        this.loggingCurrentToken();

        // handle all event relate token
        this.oauthService.events.subscribe(e => {
            console.log('Oauth/oidc Event', e);
            // refresh token then load profile
            switch (e.type) {
                case 'token_expires':
                    console.log('OAuth2: refresh new token request');
                    this.oauthService.refreshToken()
                        .then(value => {
                            console.log('OAuth2: refresh new token success');
                            this.loadProfile();
                        })
                        .catch(error => console.log('OAuth2: refresh new token success', error));
                    break;
                case 'logout':
                    this.publishChangeProfile(null);
            }
        });

    }

    publishChangeProfile = (profile: any) => this.subject.next(profile);

    registerSubscriber = (): Observable<any> => this.subject.asObservable();

    loadProfile = () => {
        console.log('OAuth2: load profile request');
        // contains access token and valid
        if (this.oauthService.hasValidAccessToken()) {
            console.log('OAuth2: token still valid');
            // already exist claims
            if (this.oauthService.getIdentityClaims()) {
                this.publishChangeProfile(this.oauthService.getIdentityClaims());
                console.log('OAuth2: load profile success (extract from claim token)');
            } else
                this.oauthService.loadUserProfile().then(profile => {
                    this.publishChangeProfile(profile);
                    console.log('OAuth2: load profile success (request api)');
                }).then(error => console.log(`OAuth2: load profile failed (${error})`));
        } else
            console.log('OAuth2: load profile failed (token is invalid)');
    }

    loginWithUsernameAndPassword = (username: string, password): Promise<any> => {
        console.log('OAuth2: request login');
        return this.oauthService.fetchTokenUsingPasswordFlow(username, password)
            .then(value => {
                console.log('OAuth2: authenticated, login success');
                if (value.hasOwnProperty('account')) this.publishChangeProfile(value['account']);
            });
    }

    logout = () => {
        this.oauthService.logOut(true);
    }

    isAuthenticated = () => this.oauthService.hasValidAccessToken();

    loggingCurrentToken = () => {
        console.log(`OAuth2: Access token is exist(${this.oauthService.getAccessToken() != null})`);
        console.log(`OAuth2:      expire: ${(new Date(this.oauthService.getAccessTokenExpiration()).toLocaleTimeString())}`);
        console.log(`OAuth2:       valid: ${this.oauthService.hasValidAccessToken()}`);
        console.log(`OAuth2: Refresh token is exist(${this.oauthService.getRefreshToken() != null})`);
    }

    valid = () => this.oauthService.hasValidAccessToken() + ' ' + (this.oauthService.getAccessTokenExpiration() - Date.now());
}
