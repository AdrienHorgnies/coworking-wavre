import { EquipmentTypeModel } from "./equipmentType.model";

export interface OrderedEquipmentModel {
    id: number;
    unitPricePerDay: number;
    quantity: number;
    equipmentType: EquipmentTypeModel;
}
