package openerp.openerpresourceserver.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import openerp.openerpresourceserver.converter.BuildingConverter;
import openerp.openerpresourceserver.dto.BuildingDTO;
import openerp.openerpresourceserver.entity.Building;
import openerp.openerpresourceserver.repo.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepo buildingRepo;

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepo.findAll();
    }

    @Override
    public Page<Building> getAllBuildings(Pageable pageable) {
        return buildingRepo.findAll(pageable);
    }

    @Override
    public Building getBuildingById(UUID id) {
        return buildingRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Building not found with id: " + id));
    }

    @Override
    public Building addBuilding(BuildingDTO buildingDTO) {
        Building building = BuildingConverter.convertToEntity(buildingDTO);
        return buildingRepo.save(building);
    }

    @Override
    public Building updateBuilding(UUID id, BuildingDTO buildingDTO) {
        Building existingBuilding = buildingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id: " + id));

        existingBuilding.setName(buildingDTO.getName());
        existingBuilding.setFloors(buildingDTO.getFloors());
        existingBuilding.setLocation(buildingDTO.getLocation());

        return buildingRepo.save(existingBuilding);
    }

    @Override
    public void deleteBuilding(UUID id) {
        if (!buildingRepo.existsById(id)) {
            throw new EntityNotFoundException("Building not found with id: " + id);
        }
        buildingRepo.deleteById(id);
    }
}
