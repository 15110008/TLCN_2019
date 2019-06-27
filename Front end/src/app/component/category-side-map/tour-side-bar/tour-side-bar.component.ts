import {Component, Input, OnInit} from '@angular/core';
import {Plan} from '../../../model/api/plan';
import {environment} from '../../../../environments/environment';

declare var $: any;

@Component({
    selector: 'tour-side-bar',
    templateUrl: './tour-side-bar.component.html',
    styleUrls: ['./tour-side-bar.component.css']
})
export class TourSideBarComponent implements OnInit {

    @Input('tours') plans: Plan[] = [];

    defaultImage = environment.defaultImage;

    constructor() {
    }

    ngOnInit() {
    }


}
