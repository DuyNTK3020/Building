package openerp.openerpresourceserver.service;

import openerp.openerpresourceserver.dto.BuildingDTO;
import openerp.openerpresourceserver.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BuildingService {
    List<Building> getAllBuildings();

    Page<Building> getAllBuildings(Pageable pageable);

    Building getBuildingById(UUID id);

    Building addBuilding(BuildingDTO buildingDTO);

    Building updateBuilding(UUID id, BuildingDTO buildingDTO);

    void deleteBuilding(UUID id);
}
