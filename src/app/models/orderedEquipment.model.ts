import { EquipmentTypeModel } from "./equipmentType.model";

export interface OrderedEquipmentModel {
    id: number;
    price: number;
    quantity: number;
    equipmentType: EquipmentTypeModel;
}
