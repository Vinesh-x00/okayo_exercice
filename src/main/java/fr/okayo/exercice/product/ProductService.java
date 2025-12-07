package fr.okayo.exercice.product;

import fr.okayo.exercice.exceptions.BadRequest;
import fr.okayo.exercice.exceptions.ResourceNotFoundException;
import fr.okayo.exercice.vat.VatCategoryRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private final @NotNull ProductRepository productRepository;
    private final @NotNull VatCategoryRepository vatCategoryRepository;

    public Product finById(UUID id) {
        Objects.requireNonNull(id);
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("produit non trouvé"));
    }

    @Transactional
    public Product createProduct(ProductDTO dto) {
        var product = new Product();
        var category = vatCategoryRepository
                .findById(dto.vtaCategory())
                .orElseThrow(() -> new BadRequest("categorie Tva not trouvé"));

        product.setDesignation(dto.designation());
        product.setPriceExclTax(dto.priceExclTax());
        product.setVatCategory(category);
        return product;
    }



}
