import {AfterViewChecked, Component, Input, OnInit} from '@angular/core';
import {Plan} from 'src/app/model/api/plan';
import {TourService} from 'src/app/service/tour.service';

declare let $: any;

@Component({
    selector: 'carousel',
    templateUrl: './carousel.component.html',
    styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit, AfterViewChecked {

    @Input('carousel-id') carouselId: string;

    owlCarouselConfig: any;

    $bestTour: any;

    plans: Plan[];

    constructor(private tourService: TourService) {

        this.plans = [];
        this.$bestTour = $(`#${this.carouselId}`);

        let max_items = 3;
        let desc_items = max_items;
        if (max_items >= 4)
            desc_items = max_items - 1;

        let tablet_items = max_items;
        if (max_items >= 4)
            tablet_items = max_items - 2;
        else
            tablet_items = max_items - 1;

        let smart_items = max_items;
        if (max_items > 4)
            smart_items = max_items - 3;
        else
            smart_items = 1;

        if (max_items < 2) {
            smart_items = max_items;
            tablet_items = max_items;
        }
        let mobile_items = 1;

        this.owlCarouselConfig = {
            loop: true,
            items: 3,
            smartSpeed: 450,
            autoplay: true,
            autoplayTimeout: 2000,
            autoplayHoverPause: true,
            navText: false,
            nav: true,
            margin: 20,
            lazyLoad: true,
            itemsScaleUp: true,
            responsiveClass: true,
            responsive: {
                0: {
                    items: mobile_items
                },
                480: {
                    items: smart_items
                },
                768: {
                    items: tablet_items
                },
                992: {
                    items: desc_items
                },
                1280: {
                    items: max_items
                }
            }
        };

        this.carouselId = 'id';
    }

    ngOnInit() {
        this.tourService.getPlans(0, 10, ['tour'])
            .subscribe((data: Plan) => this.plans = data.content,
                error => console.log('Carousel: load plans fail'));
    }

    ngAfterViewChecked(): void {
        let $owlCarousel = $(`#${this.carouselId}`);
        if ($(`#content-${this.carouselId}`).children().length > 0) {
            $owlCarousel.append($(`#content-${this.carouselId}`).children());
            $owlCarousel.owlCarousel(this.owlCarouselConfig);
        }
    }
}
