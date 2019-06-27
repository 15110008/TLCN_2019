import {Component, HostListener, Input, OnInit} from '@angular/core';
import {Item} from 'src/app/model/extra/item';
import {CartService} from 'src/app/service/cart.service';
import {Operate} from "../../model/extra/operate";
import {Notification} from "../../model/extra/notification";
import {Guid} from "guid-typescript";
import {AuthService} from "../../service/auth.service";
import {ItemPlan} from "../../model/extra/item-plan";

declare var $: any;

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

    items: ItemPlan[];

    @Input('home') home: boolean;

    @Input('slide-box') showSlideBox: boolean;

    username: string;
    password: string;

    profile: any;

    openNav: boolean;

    loading: boolean;

    oauthError: string;

    @HostListener('document:keydown.escape', ['$event'])
    onKeydownHandler(event: KeyboardEvent) {
        this.openNav = false;
    }

    constructor(private cartService: CartService,
                private authService: AuthService) {

        this.items = [];
        this.username = '97lynk';
        this.password = 'admin';
        this.openNav = false;
        this.loading = false;
        this.home = true;
        this.oauthError = null;
        console.log('constructor header component');
    }

    ngOnInit() {
        console.log('ngOnInit header component');

        $("#owl-demo").owlCarousel({
            animateOut: 'slideOutDown',
            animateIn: 'fadeInLeft',
            pagination: false,
            nav: false,
            dots: false,
            loop: true,
            smartSpeed: 1000,
            autoplay: true,
            autoplayTimeout: 2000,
            autoplayHoverPause: true,
            items: 1,
            itemsDesktop: false,
            itemsDesktopSmall: false,
            itemsTablet: false,
            itemsMobile: false
        });

        // load data
        this.loadItemsAndPlanInfo();

        // load data when change
        this.cartService.registerSubscriber().subscribe((not: Notification) => {
            console.log(`Subscriber header see ${JSON.stringify(not.message)}`);
            switch (not.operate) {
                case Operate.DELETE:
                    // delete item in list
                    let item: Item = not.data;
                    const indexDel = this.items.findIndex(i => i.item.id == item.id);
                    if (indexDel >= 0) this.items.splice(indexDel, 1);
                    break;
                case Operate.ADD:
                default:
                    this.loadItemsAndPlanInfo();
                    break;
            }
        });

        // subscribe profile
        this.authService.registerSubscriber().subscribe(profile => {
            console.log('OAuth2: authenticated, receive profile');
            this.profile = profile;
        }, error => console.log('Header: receive profile fail'));

        this.authService.loadProfile();
    }

    login = () => {
        this.loading = true;
        this.authService.loginWithUsernameAndPassword(this.username, this.password)
            .then(value => {
                console.log('Load cart');
                const notify = new Notification(Guid.create().toString(), Date.now(),
                    'I\'m logging', Operate.LOGIN, null);
                this.openNav = false;
                this.loading = false;
                this.cartService.publishChangeCart(notify);
            })
            .catch(error => {
                this.loading = false;
                this.openNav = true;
                this.oauthError = 'Tài khoản hoặc mật khẩu không chính xác';
                console.log('OAuth2: login failed');
            });
    }

    logout = () => {
        this.authService.logout();
        const notify = new Notification(Guid.create().toString(), Date.now(),
            'I\'m logout', Operate.LOGOUT, null);
        this.cartService.publishChangeCart(notify);
    }

    loadItemsAndPlanInfo = () => {
        // clear list
        this.items = [];

        // get data
        this.cartService.getCart().subscribe(
            item => this.items.push(item),
            error => console.log(`Header: load cart error ${JSON.stringify(error)}`));
    }

    calTotal = (): number => {
        return this.items.map(i => i.item.noTicketAdult * i.plan.adultPrice + i.item.noTicketChild * i.plan.childPrice)
            .reduce((v1, v2) => v1 + v2, 0.0);
    }

    removeItemInCart = (item: Item) => {
        // delete item in local storage
        this.cartService.deleteItemInCart(item.id + '');
    }

    view = (event?) => {
        // event.preventDefault();
        alert(this.authService.valid());
    }

}
