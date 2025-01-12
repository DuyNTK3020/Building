package openerp.openerpresourceserver.converter;

import openerp.openerpresourceserver.dto.CustomerDTO;
import openerp.openerpresourceserver.entity.Customer;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CustomerConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public CustomerDTO convertToDTO(Customer customer) {
        String formattedBod = "";
        if (customer.getBod() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            formattedBod = dateFormat.format(customer.getBod());
        }

        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .avatarUrl(customer.getAvatarUrl())
                .bod(formattedBod)
                .isOwner(customer.isOwner())
                .status(customer.getStatus())
                .build();
    }

    public CustomerDTO convertToDTOForAdmin(Customer customer) {
        String formattedBod = "";
        if (customer.getBod() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            formattedBod = dateFormat.format(customer.getBod());
        }

        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .avatarUrl(customer.getAvatarUrl())
                .bod(formattedBod)
                .isOwner(customer.isOwner())
                .status(customer.getStatus())
                .password(customer.getPassword())
                .build();
    }

    public static Customer convertToEntity(CustomerDTO customerDTO) {
        Date parsedBod = null;
        if (customerDTO.getBod() != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                parsedBod = dateFormat.parse(customerDTO.getBod());
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format for bod: " + customerDTO.getBod());
            }
        }

        Date currentDate = new Date();

        Customer.CustomerBuilder customerBuilder = Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .phoneNumber(customerDTO.getPhoneNumber())
                .avatarUrl(customerDTO.getAvatarUrl())
                .bod(parsedBod)
                .isOwner(customerDTO.isOwner())
                .status(customerDTO.getStatus())
                .createdDate(currentDate)
                .lastModifiedDate(currentDate);

        if (customerDTO.getPassword() != null && !customerDTO.getPassword().isEmpty()) {
            customerBuilder.password(customerDTO.getPassword());
        }

        return customerBuilder.build();
    }
}
