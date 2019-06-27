import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TourService} from '../../service/tour.service';
import {Plan} from 'src/app/model/api/plan';
import {Tour} from '../../model/api/tour';
import {MapService} from '../../service/map.service';
import {Place} from '../../model/api/place';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Link} from "../../model/extra/link";

declare var $: any;

@Component({
    selector: 'app-tour-detail',
    templateUrl: '/tour-detail.component.html',
    styleUrls: ['./tour-detail.component.css'],
    animations: [trigger('slideOut', [
        state('out', style({
            'max-height': '400px'
        })),
        state('*', style({
            'max-height': '0', 'overflow': 'hidden'
        })),
        transition('* => out', [
            animate('500ms linear')
        ])
    ])]
})

export class TourDetailComponent implements OnInit {

    @ViewChild('wapper') infoTour: ElementRef;

    plan: Plan;

    planId: string;

    contentHTML: string;

    relatedPlans: Plan[];

    quantity: { adult: number, child: number };

    defaultCenter: { lat: number, lng: number };

    constructor(private route: ActivatedRoute,
                private router: Router,
                private tourService: TourService,
                private mapService: MapService) {
        this.plan = new Plan();
        this.defaultCenter = {lat: 10.776889, lng: 106.700806};
    }

    ngOnInit() {

        this.route.url.subscribe(value => {
            const index = this.route.snapshot.paramMap.get('url-id').lastIndexOf('-') + 1;
            this.planId = this.route.snapshot.paramMap.get('url-id').slice(index);
            this.contentHTML = '';

            this.relatedPlans = [];
            this.quantity = {adult: 1, child: 1};

            console.log(`load Plan#${this.planId}`);
            this.tourService.getPlanById(this.planId)
                .subscribe((plan: Plan) => {
                    console.log(`success Plan#${plan.id}`);
                    this.plan = plan;

                    // following link to get tour of a plan
                    const linkTour = plan.links.find(p => p.rel == 'tour');
                    this.followLinkToGetTour(linkTour);

                    // following link to get all places of a plan
                    const placesLink = plan.links.find(p => p.rel == 'places');
                    this.followLinkToGetPlaces(placesLink);

                    //  related plans: get all plans with place ids of current plan
                    this.getRelatedPlans(plan.placeIds);

                    // console.log(this.infoTour);
                    this.infoTour.nativeElement.scrollIntoView(true);
                    window.scrollBy(0, 20);
                }, error => this.router.navigate(['/404']));
        });
    }

    private followLinkToGetTour = (linkTour: Link) => {
        if (linkTour != undefined) {
            console.log(`following ${linkTour.href}`);
            this.tourService.followLink<Tour>(linkTour.href)
                .subscribe((tour: Tour) => {
                    this.plan.tour = tour;
                    console.log(`following success ${linkTour.href}`);

                    console.log(`load tour's content ${tour.fileContentUrl}`);
                    this.tourService.loadContentPostOfTour(tour.fileContentUrl).subscribe(
                        (html: string) => this.contentHTML = html,
                        error => this.router.navigate(['/404']));
                }, error => this.router.navigate(['/404']));
        }
    }

    private followLinkToGetPlaces = (placesLink: Link) => {
        if (placesLink != undefined) {
            console.log(`following ${placesLink.href}`);
            this.mapService.followLink<Place>(placesLink.href)
                .subscribe((place: Place) => {
                    this.plan.places = place.content;
                    if (place.content.length == 1)
                        this.defaultCenter = {lat: place.content[0].latitude, lng: place.content[0].longitude};

                    console.log(`following success ${placesLink.href}`);
                }, error => this.router.navigate(['/404']))
        }
    }

    private getRelatedPlans = (placeIds: number[] = []) => {
        console.log(`load related plans ${JSON.stringify(placeIds)}`);
        placeIds.forEach(pid => {
            this.tourService.getPlansByPlaceId(pid)
                .subscribe((relatedPlan: Plan) => {
                    relatedPlan.content.forEach(p => {
                        if (this.relatedPlans.findIndex(pp => pp.id == p.id) < 0 && p.id != this.plan.id)
                            this.relatedPlans.push(p);
                    });
                    console.log(`load related plans ${JSON.stringify(placeIds)} suucess`);
                }, error => this.router.navigate(['/404']));
        });
    }

    clickPlaceMarker = (place: Place) => {
        this.router.navigate(['/maps', place.url])
    }

}
