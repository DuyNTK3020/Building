package openerp.openerpresourceserver.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import openerp.openerpresourceserver.converter.CustomerConverter;
import openerp.openerpresourceserver.dto.CustomerDTO;
import openerp.openerpresourceserver.entity.Customer;
import openerp.openerpresourceserver.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log4j2
@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepo customerRepo;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepo.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepo.findById(id).orElse(null);
    }

    @Override
    public Customer addCustomer(CustomerDTO customerDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        customerDTO.setPassword(encryptedPassword);

        Customer customer = CustomerConverter.convertToEntity(customerDTO);

        return customerRepo.save(customer);
    }


    @Override
    public Customer updateCustomer(UUID id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        existingCustomer.setAvatarUrl(customerDTO.getAvatarUrl());
        try {
            existingCustomer.setBod(customerDTO.getBod() != null
                    ? new SimpleDateFormat("yyyy-MM-dd").parse(customerDTO.getBod())
                    : null);
        } catch (ParseException e) {
            log.error("Failed to parse date: " + customerDTO.getBod(), e);
            existingCustomer.setBod(null);
        }
        existingCustomer.setOwner(customerDTO.isOwner());
        existingCustomer.setStatus(customerDTO.getStatus());

        return customerRepo.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        if (!customerRepo.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        customerRepo.deleteById(id);
    }
}