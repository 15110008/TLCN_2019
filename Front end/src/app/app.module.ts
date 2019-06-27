import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {IsotopeModule} from 'ngx-isotope';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import {AgmCoreModule} from '@agm/core';
import {AgmSnazzyInfoWindowModule} from '@agm/snazzy-info-window';
import {intersectionObserverPreset, LazyLoadImageModule} from 'ng-lazyload-image';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {OAuthModule, OAuthStorage} from 'angular-oauth2-oidc';
import {
    MatBadgeModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatTabsModule
} from '@angular/material';

import {AppComponent} from './app.component';
import {CategorySideMapComponent} from './component/category-side-map/category-side-map.component';
import {HomeComponent} from './component/home/home.component';
import {TourDetailComponent} from './component/tour-detail/tour-detail.component';
import {HeaderComponent} from './component/header/header.component';
import {CartComponent} from './component/cart/cart.component';
import {CheckoutComponent} from './component/checkout/checkout.component';
import {ErrorPageComponent} from './component/error-page/error-page.component';
import {TravelRoutingModule} from './travel-routing.module';
import {ProfileComponent} from './component/profile/profile.component';
import {ShortendTextPipe} from './pipe/shortend-text.pipe';
import {CarouselComponent} from './component/home/carousel/carousel.component';
import {HotPlaceComponent} from './component/home/hot-place/hot-place.component';
import {LatestVideoComponent} from './component/home/latest-video/latest-video.component';
import {InformationTourComponent} from './component/tour-detail/information-tour/information-tour.component';
import {DescriptionTourComponent} from './component/tour-detail/description-tour/description-tour.component';
import {BookingTourComponent} from './component/tour-detail/booking-tour/booking-tour.component';
import {SuggestTourComponent} from './component/tour-detail/suggest-tour/suggest-tour.component';
import {TourSideBarComponent} from './component/category-side-map/tour-side-bar/tour-side-bar.component';
import {BoxPlaceComponent} from './component/category-side-map/box-place/box-place.component';
import {VDateTimePipe} from './pipe/vdate-time.pipe';
import {urlsAuth} from "./model/extra/oauth2-config";
import {VTextEncodePipe} from './pipe/vtext-encode.pipe';
import {ListTourComponent} from './component/list-tour/list-tour.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {VCurrencyPipe} from './pipe/vcurrency.pipe';

@NgModule({
    declarations: [
        AppComponent,
        CategorySideMapComponent,
        HomeComponent,
        TourDetailComponent,
        HeaderComponent,
        CartComponent,
        CheckoutComponent,
        ErrorPageComponent,
        ProfileComponent,
        ShortendTextPipe,
        CarouselComponent,
        HotPlaceComponent,
        LatestVideoComponent,
        InformationTourComponent,
        DescriptionTourComponent,
        BookingTourComponent,
        SuggestTourComponent,
        TourSideBarComponent,
        BoxPlaceComponent,
        VDateTimePipe,
        VTextEncodePipe,
        ListTourComponent,
        VCurrencyPipe
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        TravelRoutingModule,
        IsotopeModule,
        NgbModule,
        // for AGM
        AgmSnazzyInfoWindowModule,
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyBp27HFTtRCYCpaK6B435-939VYMN3PPEA'
        }),
        LazyLoadImageModule.forRoot({
            preset: intersectionObserverPreset
            , finally: ({element}) => console.log('The image is loaded', element['src'])
        }),
        // for material
        MatCardModule,
        MatButtonModule,
        MatInputModule,
        MatIconModule,
        MatTabsModule,
        MatBadgeModule,
        MatProgressSpinnerModule,
        //for oauth2
        OAuthModule.forRoot(urlsAuth),
        //
        NgxPaginationModule
    ],
    providers: [{provide: OAuthStorage, useValue: localStorage}],
    bootstrap: [AppComponent]
})
export class AppModule {
}
