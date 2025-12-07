package fr.okayo.exercice.product;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository represents the product.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Find product by name by ignoring case.
     * @param designation product name.
     * @return list of products contains the given name.
     */
    List<Product> findByDesignationContainingIgnoreCase(@NotNull String designation);
}
