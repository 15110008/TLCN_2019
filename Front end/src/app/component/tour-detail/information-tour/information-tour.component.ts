import {Component, Input, OnInit} from '@angular/core';
import {Plan} from 'src/app/model/api/plan';
import {environment} from '../../../../environments/environment';

@Component({
    selector: 'information-tour',
    templateUrl: './information-tour.component.html',
    styleUrls: ['./information-tour.component.css']
})
export class InformationTourComponent implements OnInit {

    @Input('plan') plan: Plan;

    defaultImage: string;

    constructor() {
        this.defaultImage = environment.defaultImage;
    }

    ngOnInit() {
    }

}
