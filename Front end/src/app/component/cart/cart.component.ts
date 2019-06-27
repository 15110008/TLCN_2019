import {Component, OnInit} from '@angular/core';
import {CartService} from '../../service/cart.service';
import {Item} from '../../model/extra/item';
import {Operate} from "../../model/extra/operate";
import {Notification} from "../../model/extra/notification";
import {ItemPlan} from "../../model/extra/item-plan";

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

    items: ItemPlan[];

    constructor(private cartService: CartService) {
        this.items = [];
    }

    ngOnInit() {
        // load data
        this.loadItemsAndPlanInfo();

        // load data when change
        this.cartService.registerSubscriber().subscribe((not: Notification) => {
            console.log(`Subscriber cart see ${JSON.stringify(not.message)}`);
            switch (not.operate) {
                case Operate.DELETE:
                    // delete item in list
                    let item: Item = not.data;
                    const indexDel = this.items.findIndex(i => i.item.id == item.id);
                    if (indexDel >= 0) this.items.splice(indexDel, 1);
                    break;
                default:
                    this.loadItemsAndPlanInfo();
            }
        });
    }

    loadItemsAndPlanInfo = () => {
        // clear array
        this.items = [];
        // get info
        this.cartService.getCart().subscribe(
            item => this.items.push(item),
            error => console.log(`Cart: load cart error ${JSON.stringify(error)}`));
    }

    removeItemInCart = (item: Item) => {
        // delete item in local storage
        this.cartService.deleteItemInCart(item.id + '');
    }

}
