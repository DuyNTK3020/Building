package openerp.openerpresourceserver.controller;

import lombok.AllArgsConstructor;
import openerp.openerpresourceserver.converter.BuildingConverter;
import openerp.openerpresourceserver.dto.BuildingDTO;
import openerp.openerpresourceserver.entity.Building;
import openerp.openerpresourceserver.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/building")
public class BuildingController {
    private final BuildingService buildingService;
    private final BuildingConverter buildingConverter;

    @GetMapping("/get-all-data")
    public ResponseEntity<List<BuildingDTO>> getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        List<BuildingDTO> buildingDTOs = buildings.stream()
                .map(buildingConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(buildingDTOs);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<BuildingDTO>> getAllBuildings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Building> buildings = buildingService.getAllBuildings(PageRequest.of(page, size));
        Page<BuildingDTO> buildingDTOs = buildings.map(buildingConverter::convertToDTO);
        return ResponseEntity.ok(buildingDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable UUID id) {
        Building building = buildingService.getBuildingById(id);
        BuildingDTO buildingDTO = buildingConverter.convertToDTO(building);
        return ResponseEntity.ok().body(buildingDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<BuildingDTO> addBuilding(@RequestBody BuildingDTO buildingDTO) {
        Building newBuilding = buildingService.addBuilding(buildingDTO);
        BuildingDTO newBuildingDTO = buildingConverter.convertToDTO(newBuilding);
        return ResponseEntity.ok(newBuildingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@PathVariable UUID id, @RequestBody BuildingDTO buildingDTO) {
        Building updatedBuilding = buildingService.updateBuilding(id, buildingDTO);
        BuildingDTO updatedBuildingDTO = buildingConverter.convertToDTO(updatedBuilding);
        return ResponseEntity.ok(updatedBuildingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable UUID id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }
}
