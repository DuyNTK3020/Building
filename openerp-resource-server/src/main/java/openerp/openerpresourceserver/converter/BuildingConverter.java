package openerp.openerpresourceserver.converter;

import openerp.openerpresourceserver.dto.BuildingDTO;
import openerp.openerpresourceserver.entity.Building;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    public BuildingDTO convertToDTO(Building building) {
        return BuildingDTO.builder()
                .id(building.getId())
                .name(building.getName())
                .floors(building.getFloors())
                .location(building.getLocation())
                .build();
    }

    public static Building convertToEntity(BuildingDTO buildingDTO) {
        return Building.builder()
                .id(buildingDTO.getId())
                .name(buildingDTO.getName())
                .floors(buildingDTO.getFloors())
                .location(buildingDTO.getLocation())
                .build();
    }
}
