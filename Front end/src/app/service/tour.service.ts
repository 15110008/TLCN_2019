import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Plan} from '../model/api/plan';
import {environment} from "../../environments/environment";


@Injectable({
    providedIn: 'root'
})
export class TourService {


    PLAN_API_URL = `${environment.apiHost}/api/v1/plans`;

    TOUR_API_URL = `${environment.apiHost}/api/v1/tours`;

    constructor(private http: HttpClient) {
    }

    getPlanById = (id: number | string, expand: string[] = []): Observable<Plan> =>
        this.http.get<Plan>(`${this.PLAN_API_URL}/${id}`, {params: {_expand: expand.join(',')}});

    getPlans = (page: number = 0, size: number = 10, expand: string[] = []): Observable<Plan> =>
        this.http.get<Plan>(`${this.PLAN_API_URL}`, {
            params: {
                _expand: expand.join(','),
                page: `${page}`,
                size: `${size}`
            }
        });


    getPlansByPlaceId = (id: number | string): Observable<Plan> =>
        this.http.get<Plan>(this.PLAN_API_URL, {params: {placeId: `${id}`, _expand: 'tour'}});

    followLink = <T>(link: string): Observable<T> => this.http.get<T>(link);

    loadContentPostOfTour = (url: string): Observable<string> =>
        this.http.get(`${environment.apiHost}/data/contents/${url}`, {responseType: 'text'});


}
