import { SpaceModel } from "./space.model";
import { UserModel } from "./user.model";
import { OrderedEquipmentModel } from "./orderedEquipment.model";
import { OrderedServiceModel } from "./orderedService.model";

export interface ReservationModel {
    id: number;
    title: string;
    orderDate: Date;
    startDate: Date;
    endDate: Date;
    peopleNumber: number;
    spacePricePerDay: number;
    grandTotalPrice: number;
    space: SpaceModel;
    user: UserModel;
    equipmentOrders: Array<OrderedEquipmentModel>;
    serviceOrders: Array<OrderedServiceModel>;
}
