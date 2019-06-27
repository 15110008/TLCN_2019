import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'description-tour',
    templateUrl: './description-tour.component.html',
    styleUrls: ['./description-tour.component.css']
})
export class DescriptionTourComponent implements OnInit {

    @Input('contentHTML') contentHTML: string = '';

    constructor() {
    }

    ngOnInit() {
    }

}
