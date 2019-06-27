import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Plan} from '../../../model/api/plan';
import {Item} from '../../../model/extra/item';
import {CartService} from '../../../service/cart.service';
import {Guid} from "guid-typescript";
import {ItemPlan} from "../../../model/extra/item-plan";
import {Operate} from "../../../model/extra/operate";
import {Subscription} from "rxjs";

@Component({
    selector: 'booking-tour',
    templateUrl: './booking-tour.component.html',
    styleUrls: ['./booking-tour.component.css']
})
export class BookingTourComponent implements OnInit, OnDestroy {

    @Input('plan') plan: Plan;

    // quantity ticket in table
    @Input('quantity') quantity: { adult: number, child: number };

    showBooking: boolean;

    item: Item;

    subcriber: Subscription;

    subcriberCart: Subscription;

    disableQA: boolean;

    // init data
    constructor(private cartService: CartService) {
        this.quantity = {
            adult: 1,
            child: 1
        };
        this.showBooking = true;
        this.disableQA = false;
    }

    ngOnInit() {
        this.getCart();

        this.subcriber = this.cartService.registerSubscriber().subscribe(not => {
            console.log(`Subscriber booking see ${JSON.stringify(not.message)}`)
            switch (not.operate) {
                case Operate.ADD:
                    this.item = not.data;
                    this.showBooking = false;
                    break;
                case Operate.DELETE:
                    if (not.data.planId == this.plan.id) this.showBooking = true;
                case  Operate.LOGIN:
                case  Operate.LOGOUT:
                    this.showBooking = true;
                    this.getCart();
                    break;
            }
        })
    }

    getCart = () => {
        this.item = null;
        this.subcriberCart = this.cartService.getCart().subscribe(
            ip => {
                if (this.showBooking && this.plan.id == ip.plan.id) {
                    this.item = ip.item;
                    this.showBooking = false;
                }
            },
            error => console.log(`Booking: load cart error ${JSON.stringify(error)}`));
    }

    addToCart = () => {
        // make new item
        let item = new Item(
            Guid.create().toString(),
            this.plan.id,
            this.quantity.adult,
            this.quantity.child,
            Date.now());

        // add to cart
        this.cartService.addItemToCart(new ItemPlan(item, this.plan));

    }

    rebooking = () => {
        if (this.item) {
            console.log(`request delete item#${this.item.id}`);
            this.cartService.deleteItemInCart(this.item.id + '');
        }
    }

    // handle click change quantity
    addQuantity = (isAdult: boolean) => {
        if (isAdult) {
            this.quantity.adult++;
            this.disableQA = (this.quantity.adult == 1);
        }
        else
            this.quantity.child++;
    }

    subQuantity = (isAdult: boolean) => {
        if (isAdult) {
            if (this.quantity.adult == 1) return;
            this.quantity.adult--;
            this.disableQA = (this.quantity.adult == 1);
        } else {
            if (this.quantity.child == 0) return;
            this.quantity.child--;
        }
    }

    ngOnDestroy(): void {
        this.subcriber.unsubscribe();
        this.subcriberCart.unsubscribe();
    }

    // calculate grand total
    total = () => {
        let totalPrice = 0.0;
        totalPrice += this.quantity.adult * this.plan.adultPrice;
        totalPrice += this.quantity.child * this.plan.childPrice;
        return totalPrice;
    }
}
