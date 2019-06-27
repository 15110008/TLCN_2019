import {Injectable} from '@angular/core';
import {Item} from '../model/extra/item';
import {TourService} from './tour.service';
import {Plan} from '../model/api/plan';
import {concatMap, map, mergeMap} from 'rxjs/operators';
import {from, Observable, Subject} from 'rxjs';
import {Notification} from "../model/extra/notification";
import {TicketType} from "../model/extra/ticket-type";
import {BookingService} from "./booking.service";
import {ItemPlan} from "../model/extra/item-plan";
import {Booking} from "../model/api/booking";
import {AuthService} from "./auth.service";
import {Guid} from "guid-typescript";
import {Operate} from "../model/extra/operate";

@Injectable({
    providedIn: 'root'
})
export class CartService {

    private subject = new Subject<Notification>();

    KEY_CART = 'cart';

    constructor(private  tourService: TourService,
                private  bookingService: BookingService,
                private  authService: AuthService) {
    }

    publishChangeCart = (not: Notification) => this.subject.next(not);

    registerSubscriber = (): Observable<Notification> => this.subject;

    // store items with plan id, number of adult tickets, number of child tickets
    // into window's local storage
    addItemToCart = (newItem: ItemPlan) => {
        if (this.authService.isAuthenticated()) {
            this.bookingService.addBooking(newItem).subscribe(booking => {
                // notify change
                newItem.item.id = booking.id;
                const notifyAdd = new Notification(Guid.create().toString(), Date.now(),
                    'Yeah, booking success', Operate.ADD, newItem.item);
                this.publishChangeCart(notifyAdd);
            }, error => console.log(`Header: add item error ${JSON.stringify(error)}`));
        } else {
            let items: Item[] = this.restoreItems();
            items.push(newItem.item);
            this.storeItems(items);

            // notify change
            const notifyAdd = new Notification(Guid.create().toString(), Date.now(),
                'Hey, I just booking a tour (anonymous)', Operate.ADD, newItem.item);
            this.publishChangeCart(notifyAdd);
        }
    }

    // restore items and get plan it's information from api
    getCart = (): Observable<ItemPlan> => {
        console.log('Cart Service: load cart');
        if (this.authService.isAuthenticated()) {
            console.log('Cart Service: authenticated, load cart');

            return this.bookingService.getBookings().pipe(
                mergeMap(booking => from(booking.content).pipe(
                    concatMap((booking: Booking) => this.tourService.getPlanById(booking.planId, ['tour'])
                        .pipe(
                            // combine response data plan with item
                            map((plan: Plan) => new ItemPlan(this.convertBookingToItem(booking), plan))
                        )
                    )
                ))
            );
        }

        console.log('Cart Service: anonymous, load cart');
        // for anonymous user
        // restore items
        let items: Item[] = this.restoreItems();

        // make observables
        const source = from(items);
        return source.pipe(
            // create multi request
            concatMap((i: Item) => this.tourService.getPlanById(i.planId, ['tour'])
                .pipe(
                    // combine response data plan with item
                    map((plan: Plan) => new ItemPlan(i, plan))
                )
            )
        );
    }

    // restore a item in local storage by id and remove it
    deleteItemInCart = (id: string) => {

        if (this.authService.isAuthenticated()) {

            this.bookingService.deleteBooking(id).subscribe(booking => {
                // notify change
                let deleteItem = new Item(id, booking.planId, null, null, null);
                const notifyDeleteItem = new Notification(Guid.create().toString(), Date.now(),
                    'I just remove a item', Operate.DELETE, deleteItem);
                this.publishChangeCart(notifyDeleteItem);
            }, error => console.log(`Cart: delete item error ${JSON.stringify(error)}`))
        } else {

            let items: Item[] = this.restoreItems();
            let deleteItem: Item;
            // find the item's index by id
            const deleteIndex = items.findIndex(i => i.id === id);
            // delete item
            if (deleteIndex >= 0) deleteItem = items.splice(deleteIndex, 1)[0];
            this.storeItems(items);

            // notify change
            const notifyDeleteItem = new Notification(Guid.create().toString(), Date.now(),
                'I just remove a item (anonymous)', Operate.DELETE, deleteItem);
            this.publishChangeCart(notifyDeleteItem);
        }
    }

    // store items
    private storeItems = (items: Item[]) => localStorage.setItem('cart', JSON.stringify(items));

    // restore items in local storage
    private restoreItems = (): Item[] => {
        // restore data
        const cartString = localStorage.getItem(this.KEY_CART);
        // when it's not exist, create it as empty array
        if (cartString == null || cartString == undefined || cartString == '')
            return [];

        return JSON.parse(localStorage.getItem(this.KEY_CART));
    }

    private convertBookingToItem(booking: Booking): Item {
        const noAdult = booking.details.filter(d => d.ticketType === TicketType.ADULT).length;
        const noChild = booking.details.filter(d => d.ticketType === TicketType.CHILD).length;
        let item = new Item(booking.id, booking.planId, noAdult, noChild, booking.bookingTime);
        return item;
    }

}


