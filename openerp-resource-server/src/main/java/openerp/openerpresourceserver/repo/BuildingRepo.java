package openerp.openerpresourceserver.repo;

import openerp.openerpresourceserver.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuildingRepo extends JpaRepository<Building, UUID> {
}
