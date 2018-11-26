import { BuildingModel } from "./building.model";
import { EquipmentTypeModel } from "./equipmentType.model";

export interface SpaceModel {
    id: number;
    name: string;
    type: string;
    peopleCapacity: number;
    area: number;
    price: number;
    imageUrl: string;
    building: BuildingModel;
    equipmentTypes: Array<EquipmentTypeModel>
}
