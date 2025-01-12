package openerp.openerpresourceserver.repo;

import openerp.openerpresourceserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {
}
