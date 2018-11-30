import { ServiceTypeModel } from "./serviceType.model";

export interface OrderedServiceModel {
    id: number;
    quantity: number;
    unitPricePerDay: number;
    serviceType: ServiceTypeModel;
}
