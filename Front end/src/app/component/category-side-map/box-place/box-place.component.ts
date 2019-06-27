import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Place} from 'src/app/model/api/place';

@Component({
    selector: 'box-place',
    templateUrl: './box-place.component.html',
    styleUrls: ['./box-place.component.css'],
    animations: [trigger('slideInOut', [
        state('in', style({
            'max-height': '100px', 'opacity': '1'
        })),
        state('out', style({
            'max-height': '0px', 'opacity': '0'
        })),
        transition('in => out', [
            animate('300ms ease-in-out', style({
                'max-height': '0px'
            })),
            animate('400ms ease-in-out', style({
                'opacity': '0'
            }))
        ]),
        transition('out => in', [
            animate('400ms ease-in-out', style({
                'opacity': '1'
            })),
            animate('300ms ease-in-out', style({
                'max-height': '100px'
            }))
        ])
    ])]
})
export class BoxPlaceComponent implements OnInit {

    @Input('place') place: Place;
    @Input('description') description = '';
    @Input('stateOfAvatar') animationStateOfAvatar = 'in';

    @Output('clickLeftButton') clickLeftButton = new EventEmitter<any>();

    constructor() {
    }

    ngOnInit() {
    }

    moreTours = (event) => {
        this.clickLeftButton.emit(event);
    }

}
