package fr.okayo.exercice.invoice;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository represents the invoice.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    /**
     * Find InVoice by Customer code.
     * @param customerCode code of the Customer.
     * @return List of invoices belong to that customer.
     */
    List<Invoice> findByCustomer_CustomerCodeCode(@NotNull String customerCode);

    /**
     * Find invoice by status
     * @param status status of invoice
     * @return list of invoices with given status.
     */
    List<Invoice> findByStatus(@NotNull InvoiceStatus status);

    /**
     * Find invoice by reference.
     * @param reference reference code of invoice
     * @return Optional invoice.
     */
    Optional<Invoice> findByReference(@NotNull String reference);

    /**
     * Find invoices by customerCode and status
     * @param customerCode customer Code.
     * @param status invoice status.
     * @return List of invoices.
     */
    List<Invoice> findByCustomer_CustomerCodeAndStatus(@NotNull String customerCode, @NotNull InvoiceStatus status);


}
