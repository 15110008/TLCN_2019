import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {VTextEncodePipe} from "../../pipe/vtext-encode.pipe";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    constructor(private route: Router) {
    }

    ngOnInit() {
    }

    findTour = (value: string) => {
        const url = `/maps/${new VTextEncodePipe().transform(value)}`;
        this.route.navigateByUrl(url);
    }

    onKeydown = (event) => {
        if (event.key === "Enter") {
            this.findTour(event.target.value);
        }
    }
}
