package fr.okayo.exercice.customer;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository represents the customer.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Check if Customer code exists.
     * @param customerCode Customer code.
     * @return boolean value of existence of Customer.
     */
    boolean existsByCustomerCode(@NotNull String customerCode);

    /**
     * Find Customer by Customer code.
     * @param customerCode Customer code
     * @return Optional of Customer entity.
     */
    Optional<Customer> findByCustomerCode(@NotNull String customerCode);
}
