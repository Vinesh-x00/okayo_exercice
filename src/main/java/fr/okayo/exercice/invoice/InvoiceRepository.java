package fr.okayo.exercice.invoice;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository represents the invoice.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    /**
     * Find InVoice by Customer id.
     * @param customerId Id of the CustomerId.
     * @return List of invoice belong to that customer.
     */
    List<Invoice> findByCustomerId(@NotNull Integer customerId);

    /**
     * Find in voice by status
     * @param status status of invoice
     * @return list of invoice with given status.
     */
    List<Invoice> findByStatus(@NotNull InvoiceStatus status);


}
