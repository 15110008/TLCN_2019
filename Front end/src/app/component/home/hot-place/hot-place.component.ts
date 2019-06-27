import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'hot-place',
    templateUrl: './hot-place.component.html',
    styleUrls: ['./hot-place.component.css']
})
export class HotPlaceComponent implements OnInit {

    places: any[];

    constructor() {
        this.places = PLACE_HOT;
    }

    ngOnInit() {
    }
}

const PLACE_HOT = [
    {
        name: 'Đà Nẵng',
        latitude: 16.066667,
        longitude: 108.233333,
        url: 'da-nang',
        imageUrl: 'assets/images/da-nang.png',
        parentId: null
    },
    {
        name: 'Nha Trang',
        latitude: 10.933333,
        longitude: 108.1,
        url: 'nha-trang',
        imageUrl: 'assets/images/nha-trang.png',
        parentId: 10
    },
    {
        name: 'Hội An',
        latitude: 15.8664055,
        longitude: 108.2934662,
        url: 'hoi-an',
        imageUrl: 'assets/images/hoi-an.png',
        parentId: 46
    },
    {
        name: 'Phú Quốc',
        latitude: 10.2294015,
        longitude: 103.6772139,
        url: 'phu-quoc',
        imageUrl: 'assets/images/phu-quoc-2.png',
        parentId: 32
    },
    {
        name: 'Bà Rịa - Vũng Tàu',
        latitude: 10.583333,
        longitude: 107.25,
        url: 'ba-ria-vung-tau',
        imageUrl: 'assets/images/tuong-chua-kito-vung-tau.png',
        parentId: null
    },
    {
        name: 'Sa Pa',
        latitude: 22.347596,
        longitude: 103.746898,
        url: 'sa-pa',
        imageUrl: 'assets/images/ruong-bac-thang-sapa.png',
        parentId: 37
    },
    {
        name: 'Mũi Né',
        latitude: 10.9605994,
        longitude: 108.2407278746898,
        url: 'mui-ne',
        imageUrl: 'assets/images/mui-ne.png',
        parentId: 10
    },
    {
        name: 'Hà Nội',
        latitude: 21.0287847,
        longitude: 105.8479873,
        url: 'ha-noi',
        imageUrl: 'assets/images/ho-gom-ha-noi.png',
        parentId: 10
    }
]
