package fr.okayo.exercice.customer;

import fr.okayo.exercice.exceptions.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * get customer by customer code.
     * @param customerCode customer code.
     * @return customer with that customer code.
     */
    public Customer getByCustomerCode(String customerCode) {
        Objects.requireNonNull(customerCode);
        return customerRepository
                .findByCustomerCode(customerCode)
                .orElseThrow(() -> new ResourceNotFound("client non trouvé"));
    }

    /**
     * Create new Customer
     * @param dto customer dto.
     * @return created customer.
     */
    public Customer createCustomer(CustomerDTO dto) {
        var customer = new Customer();
        customer.setCustomerCode(dto.customerCode());
        customer.setCity(dto.city());
        customer.setName(dto.name());
        customer.setAddress(dto.address());
        customer.setZipCode(dto.zipCode());
        return customer;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
