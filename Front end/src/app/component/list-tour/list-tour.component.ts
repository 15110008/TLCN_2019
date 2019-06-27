import {ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TourService} from "../../service/tour.service";
import {Plan} from "../../model/api/plan";
import {PaginationInstance} from "ngx-pagination";
import {map, tap} from "rxjs/operators";
import {Observable} from "rxjs";

@Component({
    selector: 'app-list-tour',
    templateUrl: './list-tour.component.html',
    styleUrls: ['./list-tour.component.css'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListTourComponent implements OnInit {

    @Output() pageChange: EventEmitter<number>;

    pageSize = 9;

    public config: PaginationInstance = {
        id: 'custom',
        itemsPerPage: this.pageSize,
        currentPage: 1,
        totalItems: 0,
    };

    planObservable: Observable<Plan[]>;

    constructor(private tourService: TourService) {
    }

    ngOnInit() {
        this.planObservable = this.getTour(0);
    }

    pageChanged = (event) => {
        this.planObservable = this.getTour(event - 1);
    }

    // page index = 0
    getTour = (page: number): Observable<Plan[]> => {
        return this.tourService.getPlans(page, this.pageSize, ['tour'])
            .pipe(
                tap((planRes: Plan) => {
                    this.config.totalItems = planRes.page.totalElements;
                    this.config.currentPage = planRes.page.number + 1;
                    this.config.itemsPerPage = planRes.page.size;
                }),
                map((plan: Plan) => plan.content)
            );
    }


}
