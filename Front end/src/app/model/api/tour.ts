import {ResponseResource} from '../extra/response-resource';

export class Tour extends ResponseResource<Tour> {

    id: number;
    name: string;
    url: string;
    title: string;
    imageUrl: string;
    fileContentUrl: string;
    description: string;
    numberOfDate: number;
    numberOfNight: number;
    createAt: string;
    createBy: string;

}
