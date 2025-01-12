package openerp.openerpresourceserver.service;

import openerp.openerpresourceserver.dto.CustomerDTO;
import openerp.openerpresourceserver.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Page<Customer> getAllCustomers(Pageable pageable);

    Customer getCustomerById(UUID id);

    Customer addCustomer(CustomerDTO customerDTO);

    Customer updateCustomer(UUID id, CustomerDTO customerDTO);

    void deleteCustomer(UUID id);
}