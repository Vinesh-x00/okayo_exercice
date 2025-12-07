package fr.okayo.exercice.vat;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository represents the VAT categories.
 */
public interface VatCategoryRepository extends JpaRepository<VatCategory, String> {
}
