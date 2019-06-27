import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {HomeComponent} from "./component/home/home.component";
import {CategorySideMapComponent} from "./component/category-side-map/category-side-map.component";
import {TourDetailComponent} from "./component/tour-detail/tour-detail.component";
import {ProfileComponent} from "./component/profile/profile.component";
import {CartComponent} from "./component/cart/cart.component";
import {CheckoutComponent} from "./component/checkout/checkout.component";
import {ErrorPageComponent} from "./component/error-page/error-page.component";
import {ListTourComponent} from "./component/list-tour/list-tour.component";

const travelRoutes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'maps', component: CategorySideMapComponent},
    {path: 'maps/:url-province', component: CategorySideMapComponent},
    {path: 'tours/:url-id', component: TourDetailComponent},
    {path: 'profile', component: ProfileComponent},
    {path: 'cart', component: CartComponent},
    {path: 'checkout', component: CheckoutComponent},
    {path: 'checkout/:booking-id', component: CheckoutComponent},
    {path: 'list-tour', component: ListTourComponent},
    {path: '**', component: ErrorPageComponent},
    {path: '404', component: ErrorPageComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(travelRoutes)
    ],
    exports: [RouterModule],
    declarations: [],
    providers: []
})
export class TravelRoutingModule {
}
