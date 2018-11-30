import { ServiceTypeModel } from "./serviceType.model";

export interface OrderedServiceModel {
    id: number;
    quantity: number;
    price: number;
    serviceType: ServiceTypeModel;
}
