import { EquipmentTypeModel } from "./equipmentType.model";

export interface EquipmentOrderModel {
    quantity: number,
    equipmentType: EquipmentTypeModel
}
