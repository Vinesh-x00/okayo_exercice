package fr.okayo.exercice.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository represents the invoice line (details).
 */
@Repository
public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Integer> {

}
