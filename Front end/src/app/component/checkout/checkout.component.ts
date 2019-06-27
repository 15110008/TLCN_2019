import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookingService} from "../../service/booking.service";
import {Booking} from "../../model/api/booking";
import {BookingDetail} from "../../model/api/booking-detail";
import {Plan} from "../../model/api/plan";

@Component({
    selector: 'app-checkout',
    templateUrl: './checkout.component.html',
    styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

    booking: Booking;

    constructor(private route: ActivatedRoute,
                private bookingService: BookingService) {
        this.booking = new Booking();
        this.booking.details = [];
    }

    ngOnInit() {
        const bookingId = +this.route.snapshot.paramMap.get('booking-id');
        if (!isNaN(bookingId) && bookingId != 0) {
            this.bookingService.getBooking(bookingId).subscribe(booking => {
                this.booking = booking;
                const detailsLink = booking.links.find(l => l.rel == 'details');
                this.bookingService.followLink<BookingDetail>(detailsLink.href).subscribe(details => {
                    this.booking.details = details.content;
                }, error => console.log(`Checkout: follow get details error ${JSON.stringify(error)}`));

                const planLink = booking.links.find(l => l.rel == 'plan');
                this.bookingService.followLink<Plan>(planLink.href).subscribe(plan => {
                    this.booking.plan = plan;
                }, error => console.log(`Checkout: follow gte a plan error ${JSON.stringify(error)}`));

            }, error => console.log(`Checkout: get a item error ${JSON.stringify(error)}`));

        }
    }

    countTicket = (type: string): number => {
        if (!this.booking || !this.booking.details) return 0;
        return this.booking.details.filter(d => d.ticketType == type).length;
    }

}