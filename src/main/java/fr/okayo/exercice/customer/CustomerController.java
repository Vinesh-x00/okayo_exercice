package fr.okayo.exercice.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customers", description = "Endpoints for creating and retrieving customers")
@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "get customer by customer code"
    )
    @GetMapping("/{customerCode}")
    public Customer getByCustomerCode(@NotNull @PathVariable String customerCode) {
        return customerService.getByCustomerCode(customerCode);
    }

    @Operation(summary = "create new customers")
    @PostMapping
    public Customer createCustomer(
            @Parameter(description = "customers data")
            @NotNull @RequestBody CustomerDTO dto) {
        return customerService.createCustomer(dto);
    }

    @Operation(summary = "get all customers")
    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

}
