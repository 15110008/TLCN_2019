import {Component, ElementRef, HostListener, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TourService} from '../../service/tour.service';
import {Place} from '../../model/api/place';
import {Plan} from 'src/app/model/api/plan';
import {MapService} from 'src/app/service/map.service';

declare var $: any;
const BOX_PLACE_HEIGHT = 320;

@Component({
    selector: 'app-category-side-map',
    templateUrl: './category-side-map.component.html',
    styleUrls: ['./category-side-map.component.css']
})
export class CategorySideMapComponent implements OnInit {

    places: Place[];

    plans: Plan[];

    regionName = 'Việt Nam';

    centerCoord: { name: string, url: string, lat: number, lng: number, description: string };

    urlProvince;

    zoomLevel: number;

    animationStateOfAvatar: string;

    showLoader = true;

    @HostListener('window:resize', ['$event'])
    onResize(event) {
        this.responsiveAvatarOnBoxPlace();
    }

    @ViewChild('vmapWapper') vmapWapper: ElementRef;

    constructor(private tourService: TourService,
                private mapService: MapService,
                private route: ActivatedRoute) {
        this.plans = [];
        this.places = [];
        this.urlProvince = this.route.snapshot.paramMap.get('url-province');
        this.centerCoord = {
            name: 'TP Hồ Chí Minh',
            url: 'ho-chi-minh',
            lat: 10.776889,
            lng: 106.700806,
            description: ''
        };
        this.zoomLevel = 9;
        this.animationStateOfAvatar = 'in';
    }

    ngOnInit() {
        console.log('loading place contains any plan');
        this.mapService.getPlacesHasPlan().subscribe((places: Place) => {
            this.places = places.content;
            console.log(`success - ${places.content.length} place(s)`);
            if (this.urlProvince != null && this.urlProvince != undefined) {
                const index = this.places.findIndex(p => p.url.includes(this.urlProvince));
                if (index >= 0) this.showBoxPlace(this.places[index]);
            }
        }, error => console.log(`Map: load places error ${JSON.stringify(error)}`));


        // check path variable
        if (this.urlProvince != null && this.urlProvince != undefined) {
            // load with current url if exsit
            this.loadPlanWithPlaceUrl(this.urlProvince);
        } else {
            this.mapService.followLink("http://ip-api.com/json")
                .subscribe(data => {
                    if (data.hasOwnProperty('city') && data.hasOwnProperty('country')
                        && data.hasOwnProperty('lat') && data.hasOwnProperty('lon'))
                        this.centerCoord = {
                            name: `${data['city']}, ${data['country']}`,
                            url: 'ho-chi-minh',
                            lat: data['lat'],
                            lng: data['lon'],
                            description: ''
                        };
                }, error => console.log(`Map: load coord error ${JSON.stringify(error)}`))
            // load all plans
            console.log('loading all plans');
            this.loadPlanWithPlaceUrl('');
        }

        setInterval(() => {
            $.mad_core.isotope().isotope('layout');
        }, 100);
        this.showLoader = false;
    }

    loadPlanWithPlaceUrl = (url: string) => {
        // prepare search string
        let urlCriteria = {
            key: 'url',
            operator: '=',
            value: url
        };
        this.plans = [];
        console.log(`loading plans with place url = ${JSON.stringify(urlCriteria)}`);
        this.mapService.getPlansBySearchPlace([urlCriteria])
            .subscribe(plans => {
                this.plans = plans.content;
                setTimeout(() => {
                    $.mad_core.isotope().isotope('reloadItems');
                }, 500);
            }, error => console.log(`Map: search place error ${JSON.stringify(error)}`));

    }

    // toggle map
    toggleMap = (event) => {

        if ($('.home-container').length) {
            $('#sidebar').toggleClass('no-map');
            $('.isotope .item-hide').toggleClass('show');
            console.log('click toggle',);
        }

        if ($(event.target).length) {
            //

            $('.header').toggleClass('border');
            $('.map-section').slideToggle('fast', 'swing').resize();

            if ($('.text-change').text() == 'Hiện') {
                $('.text-change').fadeOut(function () {
                    $('.text-change').text(($('.text-change').text() == 'Hiện') ? 'Ẩn' : 'Hiện').fadeIn();
                })
            } else if ($('.text-change').text() == 'Ẩn') {
                $('.text-change').fadeOut(function () {
                    $('.text-change').text(($('.text-change').text() == 'Ẩn') ? 'Hiện' : 'Ẩn').fadeIn();
                })
            }
            $.mad_core.isotope().isotope('reloadItems');
            $.mad_core.isotope().isotope('layout');
        }
    }

    // show box place
    showBoxPlace = (place: Place) => {
        console.log(`show Box Place ${place.name} [${place.latitude}, ${place.longitude}]`);

        // search plan
        this.loadPlanWithPlaceUrl(place.url);

        // centering to place
        this.centerCoord = {
            name: place.name,
            url: place.url,
            lat: place.latitude,
            lng: place.longitude,
            description: ''
        }

        // get description of the place from wiki
        this.mapService.getDescriptionPlace(place.name).subscribe((data: any) => {
            let pages = data['query']['pages'];
            let keyFirst = Object.keys(pages)[0];
            this.centerCoord.description = pages[keyFirst].extract;
            // responsive for box place
            this.responsiveAvatarOnBoxPlace();
        }, error => console.log(`Map: get place's description error ${JSON.stringify(error)}`));

        // set region label
        this.regionName = place.name;
    }

    // styling box place for responsive
    responsiveAvatarOnBoxPlace = () => {
        let vmapWapperHeight = this.vmapWapper.nativeElement.clientHeight / 2;
        console.log(`responsive ${vmapWapperHeight} - ${BOX_PLACE_HEIGHT}`);
        this.animationStateOfAvatar = (vmapWapperHeight < BOX_PLACE_HEIGHT) ? 'out' : 'in';
    }
}
