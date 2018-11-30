import { SpaceModel } from "./space.model";
import { EquipmentOrderModel } from "./equipmentOrder.model";
import { ServiceOrderModel } from "./serviceOrder.model";

export interface ReservationModel {
    title: string,
    startDate: Date;
    endDate: Date;
    peopleNumber: number;
    space: SpaceModel;
    equipmentOrders: Array<EquipmentOrderModel>;
    serviceOrders: Array<ServiceOrderModel>;
}
