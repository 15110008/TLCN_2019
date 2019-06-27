import {Component, OnInit} from '@angular/core';
import {environment} from '../environments/environment';
import {NavigationStart, Router} from "@angular/router";

declare let $: any;

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

    loaderImage = environment.loaderImage;

    isHome: boolean;

    showFooter: boolean = true;

    showSlideBox: boolean = true;

    constructor(private router: Router) {
        this.isHome = true;
        this.showFooter = true;
    }

    ngOnInit(): void {
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationStart) {
                const url = event['url'];
                this.isHome = (url == '/' || url == '' || url == '/#discount-tour' || url == '/#best-tour');
                this.showFooter = (url.indexOf('/maps') < 0);
                this.showSlideBox = this.showFooter;
                if (url.indexOf('/maps') >= 0) $('body').css('overflow-y', 'hidden');
                else $('body').css('overflow-y', 'unset');
            }
        });
    }
}
