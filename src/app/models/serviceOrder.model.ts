import { ServiceTypeModel } from "./serviceType.model";

export interface ServiceOrderModel {
    quantity: number;
    serviceType: ServiceTypeModel;
}
