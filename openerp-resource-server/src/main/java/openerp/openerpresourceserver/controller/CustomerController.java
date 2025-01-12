package openerp.openerpresourceserver.controller;

import lombok.AllArgsConstructor;
import openerp.openerpresourceserver.converter.CustomerConverter;
import openerp.openerpresourceserver.dto.CustomerDTO;
import openerp.openerpresourceserver.entity.Customer;
import openerp.openerpresourceserver.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @GetMapping("/get-all-data")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customerConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customerDTOs);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Customer> customers = customerService.getAllCustomers(PageRequest.of(page, size));
        Page<CustomerDTO> customerDTOs = customers.map(customerConverter::convertToDTO);
        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable UUID id) {
        Customer customer = customerService.getCustomerById(id);
        CustomerDTO customerDTO = customerConverter.convertToDTO(customer);
        return ResponseEntity.ok().body(customerDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer newCustomer = customerService.addCustomer(customerDTO);
        CustomerDTO newCustomerDTO = customerConverter.convertToDTO(newCustomer);
        return ResponseEntity.ok(newCustomerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customerDTO) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDTO);
        CustomerDTO updatedCustomerDTO = customerConverter.convertToDTO(updatedCustomer);
        return ResponseEntity.ok(updatedCustomerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}