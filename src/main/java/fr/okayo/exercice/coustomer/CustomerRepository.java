package fr.okayo.exercice.coustomer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Check if Customer code exists.
     * @param customerCode Customer code.
     * @return boolean value of existence of Customer.
     */
    boolean existsByCustomerCode(String customerCode);

    /**
     * Find Customer by Customer code.
     * @param customerCode Customer code
     * @return Optional of Customer entity.
     */
    Optional<Customer> findByCustomerCode(String customerCode);
}
