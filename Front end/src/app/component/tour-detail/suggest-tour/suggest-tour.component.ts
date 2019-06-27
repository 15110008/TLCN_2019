import {Component, Input, OnInit} from '@angular/core';
import {Plan} from 'src/app/model/api/plan';
import {environment} from '../../../../environments/environment';

@Component({
    selector: 'suggest-tour',
    templateUrl: './suggest-tour.component.2.html',
    styleUrls: ['./suggest-tour.component.css']
})
export class SuggestTourComponent implements OnInit {

    @Input('tours') plans: Plan[];

    defaultImage = environment.defaultImage;

    constructor() {
    }

    ngOnInit() {
    }

}
