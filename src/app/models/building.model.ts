import { CityModel } from "./city.model";
import { ServiceTypeModel } from "./serviceType.model";

export interface BuildingModel {
    id: number;
    name: string;
    address: string;
    city: CityModel;
    serviceTypes: Array<ServiceTypeModel>;
}
