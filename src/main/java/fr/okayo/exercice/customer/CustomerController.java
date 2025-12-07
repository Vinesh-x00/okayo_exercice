package fr.okayo.exercice.customer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerCode}")
    public Customer getByCustomerCode(@PathVariable String customerCode) {
        return customerService.getByCustomerCode(customerCode);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDTO dto) {
        return customerService.createCustomer(dto);
    }

}
